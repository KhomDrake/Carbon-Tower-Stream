package com.carbontower.stream.application.web.controllers

import com.carbontower.stream.application.web.insertLogSuccess
import com.carbontower.stream.application.web.toJson
import com.carbontower.stream.domain.entities.httpRequest.Users
import com.carbontower.stream.domain.services.users.UsersService
import com.carbontower.stream.resources.api.ApiStream
import io.javalin.Context
import io.javalin.apibuilder.ApiBuilder.*

class UsersController(private val apiController: ApiStream, private val usersService: UsersService) {
    fun routes() {
        path("/users") {
            get("/login/:login", toJson { userByLogin(it) })
            get("/id/:id", toJson { userById(it) })
            post("/signup/:login/:id-user-role", toJson { signupUserStreamAndStreams(it) })
            post("/update/streams/:login/:id-user-role", toJson { updateUserStreams(it) })
        }
    }

    private fun userByLogin(ctx: Context) : Users {
        val userByLogin = apiController.getUsersByLogin(ctx.pathParam("login"))
        ctx.insertLogSuccess("User Pelo Login ${ctx.pathParam("login")}, pego com sucesso")
        return userByLogin
    }

    private fun userById(ctx: Context) : Users {
        val userById = apiController.getUsersById(ctx.pathParam("id").toInt())
        ctx.insertLogSuccess("User Pelo Id ${ctx.pathParam("id").toInt()}, pego com sucesso")
        return userById
    }

    private fun signupUserStreamAndStreams(ctx: Context) : Boolean {
        val dataUsers = apiController.getUsersByLogin(ctx.pathParam("login"))
        val idUserRole = ctx.pathParam("login").toInt()

        dataUsers.data?.forEach {
            val dataUser = it

            usersService.alreadyExistUser(dataUser)
            usersService.insertUsersStream(dataUser, idUserRole)

            val streams = apiController.getStreamsUserLogin(ctx.pathParam("login"))
            usersService.insertStreamUser(dataUser, streams)
        }
        ctx.insertLogSuccess("Cadastro do Usuário e das Streams do Usuário de login ${ctx.pathParam("login")}," +
                "feito com sucesso")

        return true
    }

    private fun updateUserStreams(ctx: Context) : Boolean {
        val idUserRole = ctx.pathParam("login").toInt()
        val dataUsers = apiController.getUsersByLogin(ctx.pathParam("login"))
        dataUsers.data?.forEach {
            val dataUser = it
            usersService.alreadyExistUser(dataUser)

            val streams = apiController.getStreamsUserLogin(ctx.pathParam("login"))
            usersService.insertNewStreamUser(dataUser, streams)
        }
        ctx.insertLogSuccess("Atualização das streams do Usuário de login ${ctx.pathParam("login")}, feito com sucesso")

        return true
    }
}