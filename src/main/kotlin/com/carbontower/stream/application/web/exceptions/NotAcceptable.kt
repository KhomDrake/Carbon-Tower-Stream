package com.carbontower.stream.application.web.exceptions

import com.carbontower.stream.application.web.CarbonTowerStreamException


abstract class NotAcceptable : CarbonTowerStreamException(){
    abstract override var message: String
    override var statusCode: Int = 406
    abstract override var messageLog: String
}