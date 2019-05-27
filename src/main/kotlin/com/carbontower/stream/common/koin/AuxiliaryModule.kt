package com.carbontower.stream.common.koin

import com.carbontower.stream.resources.database.Connection
import org.koin.dsl.module.module

val auxiliaryModule = module {
    single { Connection() }
}