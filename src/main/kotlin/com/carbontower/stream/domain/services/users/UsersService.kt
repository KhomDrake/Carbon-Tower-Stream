package com.carbontower.stream.domain.services.users

import com.carbontower.stream.domain.entities.httpRequest.DataUser
import com.carbontower.stream.domain.entities.httpRequest.Streams
import com.carbontower.stream.domain.services.exceptions.UserStreamAlreadyExist

class UsersService(private val usersRepository: IUsersRepository) {
    fun insertUsersStream(dataUser: DataUser, idUserRole: Int) {
        usersRepository.insertUsersStream(dataUser, idUserRole)
    }

    fun insertStreamUser(dataUser: DataUser, streams: Streams) {
        streams.data?.forEach {
            val dataStream = it
            usersRepository.insertStream(dataUser, dataStream)
        }
    }

    fun alreadyExistUser(dataUser: DataUser) {
        if(usersRepository.existUserStream(dataUser)) throw UserStreamAlreadyExist(dataUser.id, dataUser.login)
    }

    fun insertNewStreamUser(dataUser: DataUser, streams: Streams) {
        val streamsIdDb = usersRepository.streamUserStream(dataUser)
        streams.data?.forEach {
            val dataStream = it
            if(streamsIdDb.contains(dataStream.id).not()) {
                usersRepository.insertStream(dataUser, dataStream)
            }
        }
    }
}