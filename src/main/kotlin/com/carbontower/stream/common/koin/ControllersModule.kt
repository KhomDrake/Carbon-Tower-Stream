package com.carbontower.stream.common.koin

import com.carbontower.stream.application.web.controllers.GamesController
import com.carbontower.stream.application.web.controllers.StreamsController
import com.carbontower.stream.application.web.controllers.UsersController
import com.carbontower.stream.resources.api.ApiStream
import org.koin.dsl.module.module

val controllerModule = module {
    single { ApiStream() }
    single { GamesController(get(), get()) }
    single { UsersController(get(), get()) }
    single { StreamsController(get(), get()) }
}