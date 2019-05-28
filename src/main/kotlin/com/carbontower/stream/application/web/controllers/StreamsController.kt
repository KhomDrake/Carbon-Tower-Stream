package com.carbontower.stream.application.web.controllers

import com.carbontower.stream.application.web.insertLogSuccess
import com.carbontower.stream.application.web.toJson
import com.carbontower.stream.domain.entities.httpRequest.Game
import com.carbontower.stream.domain.entities.httpRequest.Streams
import com.carbontower.stream.domain.services.streams.StreamsService
import com.carbontower.stream.resources.api.ApiStream
import io.javalin.Context
import io.javalin.apibuilder.ApiBuilder.get
import io.javalin.apibuilder.ApiBuilder.path

class StreamsController(private val apiController: ApiStream, private val streamsService: StreamsService) {
    fun routes() {
        path("/streams") {
            get(":quantity", toJson { streamsTopQuantity(it) })
            get("game/:game_id", toJson { streamsByIdGame(it) })
            get("lang/:language", toJson { streamsByLanguage(it) })
            get("/user-id/:user_id", toJson { streamsByIdUser(it) })
            get("/user-login/:user_login", toJson { streamsByUserLogin(it) })
            get("/games/db", toJson { streamsByGamesDatabase(it) })
        }
    }

    private fun streamsTopQuantity(ctx: Context) : Streams {
        val streamsTopQuantity = apiController.getStreamsQuantity(ctx.pathParam("quantity").toInt())
        ctx.insertLogSuccess("Quantidade de ${ctx.pathParam("quantity").toInt()} Top Streams pegos com sucesso")
        return streamsTopQuantity
    }

    private fun streamsByIdGame(ctx: Context) : Streams {
        val streamsByIdGame = apiController.getStreamsGameId(ctx.pathParam("game_id").toInt())
        ctx.insertLogSuccess("Streams pelo Id Game ${ctx.pathParam("game_id").toInt()} pego com sucesso")
        return streamsByIdGame
    }

    private fun streamsByLanguage(ctx: Context) : Streams {
        val streamsByLanguage = apiController.getStreamsLanguage(ctx.pathParam("language"))
        ctx.insertLogSuccess("Streams pela linguagem ${ctx.pathParam("language")} pegos com sucesso")
        return streamsByLanguage
    }

    private fun streamsByIdUser(ctx: Context) : Streams {
        val streamsByIdUser = apiController.getStreamsUserId(ctx.pathParam("user_id").toInt())
        ctx.insertLogSuccess("Streams pelo Id User ${ctx.pathParam("user_id").toInt()} pego com sucesso")
        return streamsByIdUser
    }

    private fun streamsByUserLogin(ctx: Context) : Streams {
        val streamsByUserLogin = apiController.getStreamsUserLogin(ctx.pathParam("user_login"))
        ctx.insertLogSuccess("Streams pelo User Login ${ctx.pathParam("user_login")} pego com sucesso")
        return streamsByUserLogin
    }

    private fun streamsByGamesDatabase(ctx: Context) : Boolean {
        val listOfGames = listOf(
            "Dota 2",
            "Fifa 19",
            "Fortnite",
            "Hearthstone",
            "League of Legends",
            "Overwatch",
            "PUBG",
            "Rainbow Six Siege",
            "Street Fighter V",
            "Starcraft 2"
        )

        listOfGames.forEach {
            val game: Game = apiController.getGameByName(it)
            game.data?.first().apply {
                val dataGame = this ?: return@apply
                val streams: Streams = apiController.getStreamsGameId(dataGame.id.toInt())
                streams.data?.forEach {
                    if(it.language == "en" || it.language == "pt") {
                        println(
                            "insert into T_STREAM values ('${it.id}', " +
                                    "'${it.language.trim()}'," +
                                    "'${it.title?.trim()}'," +
                                    "'${it.type?.trim()}'," +
                                    "'${it.userId?.trim()}'," +
                                    "'${it.gameId?.trim()}'," +
                                    "'${it.userName?.trim()}'," +
                                    "${it.viewerCount}," +
                                    "'${it.thumbnailUrl}'," +
                                    "3)"
                        )
                    }
                }
            }
        }
        ctx.insertLogSuccess("Streams pelos jogos no banco de dados, pego com sucesso")
        return true
    }
}