package com.carbontower.stream.application.web.controllers

import com.carbontower.stream.domain.entities.httpRequest.DataUser
import com.carbontower.stream.domain.services.users.UsersService
import com.carbontower.stream.resources.api.ApiController
import io.javalin.apibuilder.ApiBuilder.*

class UsersController(private val apiController: ApiController, private val usersService: UsersService) {
    fun routes() {
        path("/users") {
            get("/login/:login") {ctx ->
                ctx.json(apiController.getUsersByLogin(ctx.pathParam("login")))
            }
            get("/id/:id") {ctx ->
                ctx.json(apiController.getUsersById(ctx.pathParam("id").toInt()))
            }
            post("/signup/:login") {ctx ->
                // pega o dado do usuario a partir do login
                val dataUser = apiController.getUsersByLogin(ctx.pathParam("login")) as DataUser
                // inserir usuario no banco
                // pega todas as streams dele
                // inserir as streams no banco

            }
            post("/update/streams/:login") {ctx ->
                // verificar se tem esse usuário cadastrado no banco
                // pega todas as streams a partir do login
                // inserir as novas, caso tiver.
            }

        }
    }
}