package com.carbontower.stream.application.web.controllers

import com.carbontower.stream.resources.api.ApiController
import io.javalin.apibuilder.ApiBuilder.get
import io.javalin.apibuilder.ApiBuilder.path

class StreamsController(private val apiController: ApiController) {
    fun routes() {
        path("/streams") {
            get(":quantity") {ctx ->
                ctx.json(apiController.getStreamsQuantity(ctx.pathParam("quantity").toInt()))
            }
            get("game/:game_id") {ctx ->
                ctx.json(apiController.getStreamsGameId(ctx.pathParam("game_id").toInt()))
            }
            get("lang/:language") {ctx ->
                ctx.json(apiController.getStreamsLanguage(ctx.pathParam("language")))
            }
            get("/user-id/:user_id") {ctx ->
                ctx.json(apiController.getStreamsUserId(ctx.pathParam("user_id").toInt()))
            }
            get("/user-login/:user_login") {ctx ->
                ctx.json(apiController.getStreamsUserLogin(ctx.pathParam("user_login")))
            }
        }
    }
}