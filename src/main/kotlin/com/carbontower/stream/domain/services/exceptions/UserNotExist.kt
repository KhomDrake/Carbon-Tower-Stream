package com.carbontower.stream.domain.services.exceptions

import com.carbontower.stream.application.web.exceptions.NotAcceptable

class UserNotExist(private val idUserRole: Int) : NotAcceptable() {
    override var message: String = "Dados inválidos"
    override var messageLog: String = "Usuário de idUserRole $idUserRole tentou pegar as suas Streams," +
            " mas esse idUserRole não existe."
}