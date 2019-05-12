package com.carbontower.stream.common.koin

import com.carbontower.stream.application.web.controllers.GamesController
import com.carbontower.stream.application.web.controllers.StreamsController
import com.carbontower.stream.application.web.controllers.UsersController
import com.carbontower.stream.resources.api.ApiController
import org.koin.dsl.module.module

val controllerModule = module {
    single { ApiController() }
    single { GamesController(get()) }
    single { UsersController(get()) }
    single { StreamsController(get()) }
}