package com.carbontower.stream.application.web.controllers

import com.carbontower.stream.resources.api.ApiController
import io.javalin.apibuilder.ApiBuilder.get
import io.javalin.apibuilder.ApiBuilder.path

class UsersController(private val apiController: ApiController) {
    fun routes() {
        path("/users") {
            get("/login/:login") {ctx ->
                apiController.getUsersByLogin(ctx.pathParam("login"))
            }
            get("/id/:id") {ctx ->
                apiController.getUsersById(ctx.pathParam("id").toInt())
            }
        }
    }
}