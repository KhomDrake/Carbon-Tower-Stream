package com.carbontower.stream.resources.exception

import com.carbontower.stream.application.web.exceptions.BadGateway

class ApiError(val errorMessage: String) : BadGateway() {
    override var message: String = "Não tem permissão para acessar"
    override var messageLog: String = "Não tem o cookie e por isso não pode acessar essa rota. $errorMessage"
}