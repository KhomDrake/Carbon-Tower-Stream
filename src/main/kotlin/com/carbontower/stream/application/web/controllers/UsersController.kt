package com.carbontower.stream.application.web.controllers

import com.carbontower.stream.domain.entities.httpRequest.DataUser
import com.carbontower.stream.resources.api.ApiController
import io.javalin.apibuilder.ApiBuilder.*

class UsersController(private val apiController: ApiController) {
    fun routes() {
        path("/users") {
            get("/login/:login") {ctx ->
                ctx.json(apiController.getUsersByLogin(ctx.pathParam("login")))
            }
            get("/id/:id") {ctx ->
                ctx.json(apiController.getUsersById(ctx.pathParam("id").toInt()))
            }
            post("/signup/:login") {ctx ->
                val dataUser = apiController.getUsersByLogin(ctx.pathParam("login")) as DataUser
                dataUser.id
            }
        }
    }
}