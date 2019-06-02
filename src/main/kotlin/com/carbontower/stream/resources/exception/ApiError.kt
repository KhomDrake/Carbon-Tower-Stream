package com.carbontower.stream.resources.exception

import com.carbontower.stream.application.web.exceptions.BadGateway

class ApiError(val errorMessage: String) : BadGateway() {
    override var message: String = "Falha de comunicação com a API"
    override var messageLog: String = "Não tem o cookie e por isso não pode acessar essa rota. $errorMessage"
}