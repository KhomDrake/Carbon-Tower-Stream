package com.carbontower.stream.application.web.exceptions

import com.carbontower.stream.application.web.CarbonTowerStreamException

abstract class UnprocessableEntity : CarbonTowerStreamException(){
    abstract override var message: String
    override var statusCode: Int = 422
    abstract override var messageLog: String
}