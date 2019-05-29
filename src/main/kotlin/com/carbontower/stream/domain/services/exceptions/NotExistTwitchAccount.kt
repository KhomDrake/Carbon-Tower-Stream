package com.carbontower.stream.domain.services.exceptions

import com.carbontower.stream.application.web.exceptions.NotAcceptable

class NotExistTwitchAccount(private val idUserRole: Int) : NotAcceptable() {
    override var message: String = "Dados inválidos"
    override var messageLog: String = "Usuário de idUserRole $idUserRole tentou pegar as suas Streams," +
            " mas esse idUserRole possui conta da Twitch cadastrado no nosso banco."
}