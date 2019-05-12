package com.carbontower.stream.resources.api

import com.carbontower.stream.domain.entities.httpRequest.Game
import com.carbontower.stream.domain.entities.httpRequest.Streams
import com.carbontower.stream.domain.entities.httpRequest.TopGames
import com.carbontower.stream.domain.entities.httpRequest.Users
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.gson.responseObject

class ApiController {
    private val clientID = "h8dui5jedtu6y4milo86gsrih87o8x"

    fun getTopGames() = Fuel.get("https://api.twitch.tv/helix/games/top")
        .header("Client-ID", clientID)
        .responseObject<TopGames>().third.get()

    fun getTopGamesQuantity(quantity: Int) = Fuel.get("https://api.twitch.tv/helix/games/top?first=$quantity")
        .header("Client-ID", clientID)
        .responseObject<TopGames>().third.get()

    fun getGameById(gameId: Int) = Fuel.get("https://api.twitch.tv/helix/games?id=$gameId")
        .header("Client-ID", clientID)
        .responseObject<Game>().third.get()

    fun getGameByName(name: String) = Fuel.get("https://api.twitch.tv/helix/games?name=$name")
        .header("Client-ID", clientID)
        .responseObject<Game>().third.get()

    fun getStreamsQuantity(quantity: Int) = Fuel.get("https://api.twitch.tv/helix/streams?first=$quantity")
        .header("Client-ID", clientID)
        .responseObject<Streams>().third.get()

    fun getStreamsGameId(gameId: Int) = Fuel.get("https://api.twitch.tv/helix/streams?game_id=$gameId")
        .header("Client-ID", clientID)
        .responseObject<Streams>().third.get()

    fun getStreamsLanguage(language: String) = Fuel.get("https://api.twitch.tv/helix/streams?language=$language")
        .header("Client-ID", clientID)
        .responseObject<Streams>().third.get()

    fun getStreamsUserLogin(login: String) = Fuel.get("https://api.twitch.tv/helix/streams?user_id=$login")
        .header("Client-ID", clientID)
        .responseObject<Streams>().third.get()

    fun getStreamsUserId(userId: Int) = Fuel.get("https://api.twitch.tv/helix/streams?user_login=$userId")
        .header("Client-ID", clientID)
        .responseObject<Streams>().third.get()

    fun getUsersByLogin(login: String) = Fuel.get("https://api.twitch.tv/helix/users?login=$login")
        .header("Client-ID", clientID)
        .responseObject<Users>().third.get()

    fun getUsersById(userId: Int) = Fuel.get("https://api.twitch.tv/helix/users?id=$userId")
    .header("Client-ID", clientID)
    .responseObject<Users>().third.get()
}