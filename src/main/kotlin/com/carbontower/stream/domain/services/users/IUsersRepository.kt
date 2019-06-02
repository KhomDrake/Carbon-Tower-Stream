package com.carbontower.stream.domain.services.users

import com.carbontower.stream.domain.entities.httpRequest.DataStream
import com.carbontower.stream.domain.entities.httpRequest.DataUser

interface IUsersRepository {
    fun insertUsersStream(dataUser: DataUser, idUserRole: Int)
    fun insertStream(dataUser: DataUser, dataStream: DataStream)
    fun existUserStream(dataUser: DataUser): Boolean
    fun streamUserStream(dataUser: DataUser): List<String>
}