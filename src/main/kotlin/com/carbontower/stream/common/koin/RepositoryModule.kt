package com.carbontower.stream.common.koin

import com.carbontower.stream.domain.services.games.GamesRepository
import com.carbontower.stream.domain.services.games.IGamesRepository
import com.carbontower.stream.domain.services.streams.IStreamsRepository
import com.carbontower.stream.domain.services.streams.StreamsRepository
import com.carbontower.stream.domain.services.users.IUsersRepository
import com.carbontower.stream.domain.services.users.UsersRepository
import org.koin.dsl.module.module

val repositoryModule = module {
    single { GamesRepository() as IGamesRepository}
    single { StreamsRepository() as IStreamsRepository }
    single { UsersRepository() as IUsersRepository}
}