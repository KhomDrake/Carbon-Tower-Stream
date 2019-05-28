package com.carbontower.stream.application.web.exceptions

import com.carbontower.stream.application.web.CarbonTowerStreamException


abstract class BadGateway : CarbonTowerStreamException(){
    abstract override var message: String
    override var statusCode: Int = 502
    abstract override var messageLog: String
}