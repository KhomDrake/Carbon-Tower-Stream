package com.carbontower.stream.domain.services.exceptions

import com.carbontower.stream.application.web.exceptions.NotAcceptable

class LinkChampionshipWithStreamAlreadyExist(private val idChampionship: Int, private val idStream: Int) : NotAcceptable() {
    override var message: String = "Dados para link de campeonato e stream inválidos"
    override var messageLog: String = "Tentativa de cadastro de link de campeonato e stream, mas " +
            "stream de id $idStream e campeonato de id $idChampionship já existe."
}