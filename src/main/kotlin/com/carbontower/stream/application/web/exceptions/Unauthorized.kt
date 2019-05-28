package com.carbontower.stream.application.web.exceptions

import com.carbontower.stream.application.web.CarbonTowerStreamException


abstract class Unauthorized : CarbonTowerStreamException(){
    abstract override var message: String
    override var statusCode: Int = 401
    abstract override var messageLog: String
}