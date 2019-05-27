package com.carbontower.stream.common.koin

import com.carbontower.stream.domain.services.games.GamesService
import com.carbontower.stream.domain.services.streams.StreamsService
import com.carbontower.stream.domain.services.users.UsersService
import org.koin.dsl.module.module

val serviceModule = module {
    single { GamesService(get()) }
    single { UsersService(get()) }
    single { StreamsService(get()) }
}