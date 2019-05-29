package com.carbontower.stream.application.web.controllers

import com.carbontower.stream.application.web.insertLogSuccess
import com.carbontower.stream.application.web.toJson
import com.carbontower.stream.domain.entities.httpRequest.Game
import com.carbontower.stream.domain.entities.httpRequest.TopGames
import com.carbontower.stream.domain.services.games.GamesService
import com.carbontower.stream.resources.api.ApiStream
import io.javalin.Context
import io.javalin.apibuilder.ApiBuilder.get
import io.javalin.apibuilder.ApiBuilder.path

class GamesController(private val apiController: ApiStream, private val gamesService: GamesService) {
    fun routes() {
        path("/games") {
            get("/name/:name", toJson { gamesByName(it) })
            get("/id/:id", toJson { gamesById(it) })
            get("top/:quantity", toJson { gamesTopQuantity(it) })
            get("top/", toJson { gamesTop(it) })
        }
    }

    private fun gamesByName(ctx: Context): Game {
        val gamesByName = apiController.getGameByName(ctx.pathParam("name"))
        ctx.insertLogSuccess("Game pelo nome ${ctx.pathParam("name")} pego com sucesso")
        return gamesByName
    }

    private fun gamesById(ctx: Context): Game {
        val gamesById = apiController.getGameById(ctx.pathParam("id").toInt())
        ctx.insertLogSuccess("Game pelo id ${ctx.pathParam("id").toInt()} pego com sucesso")
        return gamesById
    }

    private fun gamesTopQuantity(ctx: Context): TopGames {
        val gamesTopQuantity = apiController
            .getTopGamesQuantity(ctx.pathParam("quantity").toInt())
        ctx.insertLogSuccess("Quantidade de ${ctx.pathParam("quantity").toInt()} Top Games pegos com sucesso")
        return gamesTopQuantity
    }

    private fun gamesTop(ctx: Context): TopGames {
        val gamesTop = apiController.getTopGames()
        ctx.insertLogSuccess("Top Games pego com sucesso")
        return gamesTop
    }
}