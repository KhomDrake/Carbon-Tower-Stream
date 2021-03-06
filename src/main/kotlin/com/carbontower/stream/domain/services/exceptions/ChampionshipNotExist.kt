package com.carbontower.stream.domain.services.exceptions

import com.carbontower.stream.application.web.exceptions.NotAcceptable

class ChampionshipNotExist(private val idChampionship: Int) : NotAcceptable() {
    override var message: String = "Dados para link de campeonato e stream inválidos"
    override var messageLog: String = "Tentativa de cadastro de link de campeonato e stream, mas " +
            "campeonato não existe, id $idChampionship"
}