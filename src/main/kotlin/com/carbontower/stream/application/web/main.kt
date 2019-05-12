package com.carbontower.stream.application.web

import io.javalin.Context

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