package com.carbontower.stream.application.web.controllers

import com.carbontower.stream.domain.services.games.GamesService
import com.carbontower.stream.resources.api.ApiController
import io.javalin.apibuilder.ApiBuilder.get
import io.javalin.apibuilder.ApiBuilder.path

class GamesController(private val apiController: ApiController, private val gamesService: GamesService) {
    fun routes() {
        path("/games") {
            get("/name/:name") { ctx ->
                ctx.json(apiController.getGameByName(ctx.pathParam("name")))
            }
            get("/id/:id") { ctx ->
                ctx.json(apiController.getGameById(ctx.pathParam("id").toInt()))
            }
            get("top/:quantity") {ctx ->
                ctx.json(apiController.getTopGamesQuantity(ctx.pathParam("quantity").toInt()))
            }
            get("top/") { ctx ->
                ctx.json(apiController.getTopGames())
            }
        }
    }
}