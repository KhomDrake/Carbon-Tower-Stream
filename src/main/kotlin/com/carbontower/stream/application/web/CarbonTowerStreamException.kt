package com.carbontower.stream.application.web

import java.lang.Exception

abstract class CarbonTowerStreamException : Exception() {
    abstract override var message: String
    abstract var statusCode: Int
    abstract var messageLog: String
}