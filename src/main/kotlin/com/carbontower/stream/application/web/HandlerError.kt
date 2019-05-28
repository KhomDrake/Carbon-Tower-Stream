package com.carbontower.stream.application.web

import com.carbontower.stream.domain.entities.application.LogApplication
import io.javalin.Context
import java.lang.Exception

object HandlerError{
    fun handleCarbonTowerException(ctx: Context, exception: CarbonTowerStreamException) {
        ctx.insertLogError(LogApplication(ctx.path(), exception.statusCode, exception.messageLog))
        ctx.status(exception.statusCode)
        ctx.json(exception.message)
    }

    fun anyOtherError(ctx: Context, exception: Exception) {
        ctx.insertLogError(LogApplication(ctx.path(), 500, exception.message.toString()))
        ctx.status(statusCode = 500)
        ctx.json("Não foi possível processar esse pedido.")
    }
}