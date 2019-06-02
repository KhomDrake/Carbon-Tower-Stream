package com.carbontower.stream.domain.services.exceptions

import com.carbontower.stream.application.web.exceptions.NotAcceptable

class UserStreamAlreadyExist(private val idUser: String, private val login: String) : NotAcceptable() {
    override var message: String = "Não foi possível cadastrar usuário de stream."
    override var messageLog: String = "Usuário de Stream de id $idUser e login $login já existe"
}