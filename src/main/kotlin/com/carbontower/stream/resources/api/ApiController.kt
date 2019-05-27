package com.carbontower.stream.resources.api

import com.carbontower.stream.domain.entities.httpRequest.Game
import com.carbontower.stream.domain.entities.httpRequest.Streams
import com.carbontower.stream.domain.entities.httpRequest.TopGames
import com.carbontower.stream.domain.entities.httpRequest.Users
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.ResponseResultOf
import com.github.kittinunf.fuel.gson.responseObject

class ApiController {
    private val clientID = "h8dui5jedtu6y4milo86gsrih87o8x"
    private val errorGame = Game(listOf())
    private val errorTopGames = TopGames(listOf(), null)
    private val errorStreams = Streams(listOf(), null)
    private val errorUsers = Users(listOf())

    fun getGameById(gameId: Int) = httpRequestFuel(
        "https://api.twitch.tv/helix/games?id=$gameId",
        error = errorGame
    )

    fun getTopGames() = httpRequestFuel("https://api.twitch.tv/helix/games/top", error = errorTopGames)

    fun getTopGamesQuantity(quantity: Int) = httpRequestFuel(
        url = "https://api.twitch.tv/helix/games/top?first=$quantity",
        error = errorTopGames
    )

    fun getGameByName(name: String) = httpRequestFuel(
        "https://api.twitch.tv/helix/games?name=$name",
        error = errorGame
    )

    fun getStreamsQuantity(quantity: Int) = httpRequestFuel(
        "https://api.twitch.tv/helix/streams?first=$quantity",
        error = errorStreams
    )

    fun getStreamsGameId(gameId: Int) = httpRequestFuel(
        "https://api.twitch.tv/helix/streams?game_id=$gameId",
        error = errorStreams
    )

    fun getStreamsLanguage(language: String) = httpRequestFuel(
        "https://api.twitch.tv/helix/streams?language=$language",
        error = errorStreams
    )

    fun getStreamsUserLogin(login: String) = httpRequestFuel(
        "https://api.twitch.tv/helix/streams?user_id=$login",
        error = errorStreams
    )

    fun getStreamsUserId(userId: Int) = httpRequestFuel(
        "https://api.twitch.tv/helix/streams?user_login=$userId",
        error = errorStreams
    )

    fun getUsersByLogin(login: String) = httpRequestFuel(
        "https://api.twitch.tv/helix/users?login=$login",
        error = errorUsers
    )

    fun getUsersById(userId: Int) = httpRequestFuel(
        "https://api.twitch.tv/helix/users?id=$userId",
        error = errorUsers
    )

    private inline fun <reified T : Any> httpRequestFuel(
        url: String,
        clientID: String = "h8dui5jedtu6y4milo86gsrih87o8x", error: T
    ): Any {
        val result = Fuel.get(url)
            .header("Client-ID", clientID)
            .responseObject<T>()

        return if (result.third.component2() == null) {
            result.third.get()
        } else {
            error
        }
    }
}