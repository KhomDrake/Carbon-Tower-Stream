package com.carbontower.stream.domain.services.exceptions

import com.carbontower.stream.application.web.exceptions.NotAcceptable

class StreamNotExist(private val idStream: Int) : NotAcceptable() {
    override var message: String = "Dados para link de campeonato e stream inválidos"
    override var messageLog: String = "Tentativa de cadastro de link de campeonato e stream, mas " +
            "stream não existe, id $idStream"
}