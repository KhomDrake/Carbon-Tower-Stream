package com.carbontower.stream.resources.api

import com.carbontower.stream.application.web.CarbonTowerStreamException
import com.carbontower.stream.domain.entities.httpRequest.Game
import com.carbontower.stream.domain.entities.httpRequest.Streams
import com.carbontower.stream.domain.entities.httpRequest.TopGames
import com.carbontower.stream.domain.entities.httpRequest.Users
import com.carbontower.stream.resources.exception.ApiError
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.ResponseResultOf
import com.github.kittinunf.fuel.gson.responseObject

class ApiStream {
    private val clientID = "h8dui5jedtu6y4milo86gsrih87o8x"
    private val errorGame = Game(listOf())
    private val errorTopGames = TopGames(listOf())
    private val errorStreams = Streams(listOf())
    private val errorUsers = Users(listOf())

    fun getGameById(gameId: Int) = httpRequestFuel(
        "https://api.twitch.tv/helix/games?id=$gameId",
        error = errorGame
    ) as Game

    fun getTopGames() = httpRequestFuel("https://api.twitch.tv/helix/games/top", error = errorTopGames) as TopGames

    fun getTopGamesQuantity(quantity: Int) = httpRequestFuel(
        url = "https://api.twitch.tv/helix/games/top?first=$quantity",
        error = errorTopGames
    ) as TopGames

    fun getGameByName(name: String) : Game = httpRequestFuel(
        "https://api.twitch.tv/helix/games?name=$name",
        error = errorGame
    ) as Game

    fun getStreamsQuantity(quantity: Int) = httpRequestFuel(
        "https://api.twitch.tv/helix/streams?first=$quantity",
        error = errorStreams
    ) as Streams

    fun getStreamsGameId(gameId: String) = httpRequestFuel(
        "https://api.twitch.tv/helix/streams?game_id=$gameId",
        error = errorStreams
    ) as Streams

    fun getStreamsLanguage(language: String) = httpRequestFuel(
        "https://api.twitch.tv/helix/streams?language=$language",
        error = errorStreams
    ) as Streams

    fun getStreamsUserLogin(login: String) = httpRequestFuel(
        "https://api.twitch.tv/helix/streams?user_id=$login",
        error = errorStreams
    ) as Streams

    fun getStreamsUserId(userId: String) = httpRequestFuel(
        "https://api.twitch.tv/helix/streams?user_login=$userId",
        error = errorStreams
    ) as Streams

    fun getUsersByLogin(login: String) = httpRequestFuel(
        "https://api.twitch.tv/helix/users?login=$login",
        error = errorUsers
    ) as Users

    fun getUsersById(userId: String) = httpRequestFuel(
        "https://api.twitch.tv/helix/users?id=$userId",
        error = errorUsers
    ) as Users

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
            throw ApiError(result.third.component2()?.message.toString())
        }
    }
}