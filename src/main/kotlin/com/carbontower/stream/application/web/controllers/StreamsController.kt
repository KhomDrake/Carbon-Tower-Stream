package com.carbontower.stream.application.web.controllers

import com.carbontower.stream.domain.entities.httpRequest.Game
import com.carbontower.stream.domain.entities.httpRequest.Streams
import com.carbontower.stream.resources.api.ApiController
import io.javalin.apibuilder.ApiBuilder.get
import io.javalin.apibuilder.ApiBuilder.path

class StreamsController(private val apiController: ApiController) {
    fun routes() {
        path("/streams") {
            get(":quantity") { ctx ->
                ctx.json(apiController.getStreamsQuantity(ctx.pathParam("quantity").toInt()))
            }
            get("game/:game_id") { ctx ->
                ctx.json(apiController.getStreamsGameId(ctx.pathParam("game_id").toInt()))
            }
            get("lang/:language") { ctx ->
                ctx.json(apiController.getStreamsLanguage(ctx.pathParam("language")))
            }
            get("/user-id/:user_id") { ctx ->
                ctx.json(apiController.getStreamsUserId(ctx.pathParam("user_id").toInt()))
            }
            get("/user-login/:user_login") { ctx ->
                ctx.json(apiController.getStreamsUserLogin(ctx.pathParam("user_login")))
            }
            get("/games/db") { ctx ->
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
                    val game: Game = apiController.getGameByName(it) as Game
                    game.data?.first().apply {
                        val dataGame = this ?: return@apply
                        val streams: Streams = apiController.getStreamsGameId(dataGame.id.toInt()) as Streams
                        streams.data?.forEach {
                            if(it.language == "en" || it.language == "pt") {
                                println(
                                    "insert into T_STREAM values ('${it.id}', " +
                                            "'${it.language?.trim()}'," +
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
                    ctx.json("asdasda")
                }

            }
        }
    }
}