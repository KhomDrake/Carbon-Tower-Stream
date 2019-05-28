package com.carbontower.stream.application.web

import com.carbontower.stream.domain.entities.application.LogApplication
import com.carbontower.stream.domain.entities.databsae.T_LOGS_SERVER
import io.javalin.Context
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDateTime

fun main(args: Array<String>){
    CarbonTowerStream().startServer()
//    Fuel.get("https://api.github.com/search/repositories?q=tetris+language:assembly&sort=stars&order=desc")
//        .responseObject<Test> { request, response, result ->
//            val resposta = result.component1()
//            println(resposta?.totalCount)
//            println(resposta?.items?.count())
//            for (item in resposta?.items!!) {
//                println(item)
//            }
//        }
//    "https://api.github.com/search/repositories?q=tetris+language:assembly&sort=stars&order=desc"
//        .httpGet()
//        .responseString { request, response, result ->
//            println(request.url)
//            println(request.headers)
//            println(request.body)
//            println(result.component1())
//        }

     /*
     Games
     https://api.twitch.tv/helix/games/top
     https://api.twitch.tv/helix/games/top?first=quantidade   maximo 100
     https://api.twitch.tv/helix/games?name=nome

     Streams
     https://api.twitch.tv/helix/streams?first=quantidade maximo 100
     https://api.twitch.tv/helix/streams?game_id=id
     https://api.twitch.tv/helix/streams?language=lang
     https://api.twitch.tv/helix/streams?user_id=id
     https://api.twitch.tv/helix/streams?user_login=login

     Users
     https://api.twitch.tv/helix/users?login=login
     https://api.twitch.tv/helix/users?id=id

      */
}

internal fun toJson(block: (Context) -> Any): (Context) -> Unit{
    return {ctx: Context -> ctx.json(block(ctx))}
}

fun Context.insertLogSuccess(message: String) {
    val ctx = this
    transaction {
        T_LOGS_SERVER.insert {
            it[T_LOGS_SERVER.message] = message
            it[T_LOGS_SERVER.router] = ctx.path()
            it[T_LOGS_SERVER.statusCode] = 200
            it[T_LOGS_SERVER.dateTime] = LocalDateTime.now().toString()
            it[T_LOGS_SERVER.method] = ctx.method()
            it[T_LOGS_SERVER.server] = "Carbon Tower Stream"
        }
    }
}

fun Context.insertLogError(logApplication: LogApplication) {
    val ctx = this
    transaction {
        T_LOGS_SERVER.insert {
            it[T_LOGS_SERVER.message] = logApplication.message
            it[T_LOGS_SERVER.router] = logApplication.router
            it[T_LOGS_SERVER.statusCode] = logApplication.statusCode
            it[T_LOGS_SERVER.dateTime] = LocalDateTime.now().toString()
            it[T_LOGS_SERVER.method] = ctx.method()
            it[T_LOGS_SERVER.server] = "Carbon Tower Stream"
        }
    }
}