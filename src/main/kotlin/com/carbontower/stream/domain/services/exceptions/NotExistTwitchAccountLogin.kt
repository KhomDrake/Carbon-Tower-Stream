package com.carbontower.stream.domain.services.exceptions

import com.carbontower.stream.application.web.exceptions.NotAcceptable

class NotExistTwitchAccountLogin(private val idUserRole: Int, private val login: String) : NotAcceptable() {
    override var message: String = "Dados inválidos"
    override var messageLog: String = "Usuário de idUserRole $idUserRole e tentou pegar as suas Streams," +
            " mas esse idUserRole possui conta da Twitch cadastrado no nosso banco de login $login."
}