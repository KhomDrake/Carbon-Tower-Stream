package com.carbontower.stream.application.web.controllers

import com.carbontower.stream.application.web.insertLogSuccess
import com.carbontower.stream.application.web.toJson
import com.carbontower.stream.domain.entities.application.Stream
import com.carbontower.stream.domain.entities.databsae.T_USER_ROLE
import com.carbontower.stream.domain.entities.httpRequest.*
import com.carbontower.stream.domain.services.streams.StreamsService
import com.carbontower.stream.resources.api.ApiStream
import io.javalin.Context
import io.javalin.apibuilder.ApiBuilder.*
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class StreamsController(private val apiController: ApiStream, private val streamsService: StreamsService) {
    fun routes() {
        path("/streams") {
            get(":quantity", toJson { streamsTopQuantity(it) })
            get("game/:game_id", toJson { streamsByIdGame(it) })
            get("lang/:language", toJson { streamsByLanguage(it) })
            get("/user-id/:user_id", toJson { streamsByIdUser(it) })
            get("/user-login/:user_login", toJson { streamsByUserLogin(it) })
            get("/games/db", toJson { streamsByGamesDatabase(it) })
            get("/championship/:idchampionship", toJson { streamsByIdChampionship(it) })
            post("/championship/:idchampionship/:idstream", toJson { linkStreamWithChampionship(it) })
            get("/by-id-user-role/:id-user-role", toJson { streamsByIdUserRole(it) })
            get("/by-id-user-role-and-login/:id-user-role/:login", toJson { streamsByIdUserRoleAndLogin(it) })
        }
    }

    private fun streamsByIdChampionship(ctx: Context) : List<Stream> {
        val idChampionship = ctx.pathParam("idchampionship").toInt()
        val streams = streamsService.streamsByIdChampionship(idChampionship)
        return streams
    }

    private fun linkStreamWithChampionship(ctx: Context) : Boolean {
        val idChampionship = ctx.pathParam("idchampionship").toInt()
        val idStream = ctx.pathParam("idstream")
        streamsService.linkStreamWithChampionship(idChampionship, idStream)
        ctx.insertLogSuccess("Cadastro de Link entre stream e campeonato com sucesso. Stream: $idStream," +
                " Campeonato: $idChampionship")
        return true
    }

    private fun streamsByIdUserRole(ctx: Context) : List<Stream> {
        val idUserRole = ctx.pathParam("id-user-role").toInt()
        val listOfStream: List<Stream> = streamsService.streamsByIdUserRole(idUserRole)
        ctx.insertLogSuccess("Todas as Streams do usuário de idUserRole $idUserRole pegos com sucesso.")
        return listOfStream
    }

    private fun streamsByIdUserRoleAndLogin(ctx: Context) : List<Stream> {
        val idUserRole = ctx.pathParam("id-user-role").toInt()
        val login = ctx.pathParam("login")
        val listOfStream: List<Stream> = streamsService.streamsByIdUserRoleAndLogin(idUserRole, login)
        ctx.insertLogSuccess("Streams do usuário de idUserRole $idUserRole e Login $login pegos com sucesso.")
        return listOfStream
    }

    private fun streamsTopQuantity(ctx: Context) : Streams {
        val streamsTopQuantity = apiController.getStreamsQuantity(ctx.pathParam("quantity").toInt())
        ctx.insertLogSuccess("Quantidade de ${ctx.pathParam("quantity")} Top Streams pegos com sucesso")
        return streamsTopQuantity
    }

    private fun streamsByIdGame(ctx: Context) : Streams {
        val streamsByIdGame = apiController.getStreamsGameId(ctx.pathParam("game_id"))
        ctx.insertLogSuccess("Streams pelo Id Game ${ctx.pathParam("game_id")} pego com sucesso")
        return streamsByIdGame
    }

    private fun streamsByLanguage(ctx: Context) : Streams {
        val streamsByLanguage = apiController.getStreamsLanguage(ctx.pathParam("language"))
        ctx.insertLogSuccess("Streams pela linguagem ${ctx.pathParam("language")} pegos com sucesso")
        return streamsByLanguage
    }

    private fun streamsByIdUser(ctx: Context) : Streams {
        val streamsByIdUser = apiController.getStreamsUserId(ctx.pathParam("user_id"))
        ctx.insertLogSuccess("Streams pelo Id User ${ctx.pathParam("user_id")} pego com sucesso")
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

        val streamsGeral = mutableListOf<DataStream>()
        val idUsers = mutableListOf<String>()
        val dataUsers = mutableListOf<DataUser>()

        listOfGames.forEach {
            val game: Game = apiController.getGameByName(it)

            if(game.data.isNullOrEmpty().not()) {
                game.data?.first().apply {
                    val dataGame = this ?: return@apply
                    val streams: Streams = apiController.getStreamsGameId(dataGame.id)


                    if (streams.data.isNullOrEmpty().not()) {
                        streams.data?.forEach {
                            if (it.language == "en" || it.language == "pt") {
                                streamsGeral.add(it)
                                idUsers.add(it.userId)
                                //                        println(
                                //                            "insert into T_STREAM values ('${it.id}', " +
                                //                                    "'${it.language.trim()}'," +
                                //                                    "'${it.title?.trim()}'," +
                                //                                    "'${it.type?.trim()}'," +
                                //                                    "'${it.userId?.trim()}'," +
                                //                                    "'${it.gameId?.trim()}'," +
                                //                                    "'${it.userName?.trim()}'," +
                                //                                    "${it.viewerCount}," +
                                //                                    "'${it.thumbnailUrl}'," +
                                //                                    "3)"
                                //                        )
                            }
                        }
                    }
                }
            }
        }
        println(idUsers.count())
        println(idUsers)
        idUsers.forEach {
            val users : Users = apiController.getUsersById(it)
            if(users.data.isNullOrEmpty().not()) {
                Thread.sleep(10000)
                users.data?.forEach {
                    val dataUser = it
                    dataUsers.add(dataUser)
                }
            }
        }

        val idEmpresas = mutableListOf<Int>()

        transaction {
            val users = T_USER_ROLE.select { T_USER_ROLE.idRole_fk.eq(1) }

            users.forEach {
                idEmpresas.add(it[T_USER_ROLE.idUserRole])
            }
        }

        val quantityLogins = dataUsers.count() / idEmpresas.count()
        var idEmpresa = 0
        for(i in 0 until idUsers.count()) {
            val dataUser = dataUsers[i]
            println("insert into T_USER_STREAM values (${dataUser.id}, '${dataUser.login}', '${dataUser.displayName}', ${dataUser.viewCount}, $idEmpresa)")
            if(i % quantityLogins == 0) {
                idEmpresa++
            }
        }

        var idDataUser = 0
        streamsGeral.forEach {
            println(
                    "insert into T_STREAM values ('${it.id}', " +
                            "'${it.language.trim()}'," +
                            "'${it.title?.trim()}'," +
                            "${dataUsers[idDataUser].id}," +
                            "${it.viewerCount})"
                )

            idDataUser++
            if(idDataUser == dataUsers.count())
                idDataUser = 0
        }

        ctx.insertLogSuccess("Streams pelos jogos no banco de dados, pego com sucesso")
        return true
    }
}