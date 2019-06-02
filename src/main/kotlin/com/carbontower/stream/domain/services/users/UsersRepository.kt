package com.carbontower.stream.domain.services.users

import com.carbontower.stream.domain.entities.databsae.T_STREAM
import com.carbontower.stream.domain.entities.databsae.T_USER_STREAM
import com.carbontower.stream.domain.entities.httpRequest.DataStream
import com.carbontower.stream.domain.entities.httpRequest.DataUser
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class UsersRepository : IUsersRepository {
    override fun insertUsersStream(dataUser: DataUser, idUserRole: Int) {
        transaction {
            T_USER_STREAM.insert {
                it[T_USER_STREAM.idUserStream] = dataUser.id
                it[T_USER_STREAM.login] = dataUser.login
                it[T_USER_STREAM.displayName] = dataUser.displayName
                it[T_USER_STREAM.viewCount] = dataUser.viewCount
                it[T_USER_STREAM.idUserRole_fk] = idUserRole
            }
        }
    }

    override fun insertStream(dataUser: DataUser, dataStream: DataStream) {
        transaction {
            T_STREAM.insert {
                it[T_STREAM.idStream] = dataStream.id
                it[T_STREAM.language] = dataStream.language
                it[T_STREAM.title] = dataStream.title.toString()
                it[T_STREAM.idUserStream_fk] = dataUser.id
                it[T_STREAM.viewCount] = dataStream.viewerCount
            }
        }
    }

    override fun existUserStream(dataUser: DataUser): Boolean {
        var exist = false

        transaction {
            exist = T_USER_STREAM.select { T_USER_STREAM.idUserStream.eq(dataUser.id) }.count() != 0
        }

        return exist
    }

    override fun streamUserStream(dataUser: DataUser): List<String> {
        val listIds = mutableListOf<String>()

        transaction {
            val listStreams = (T_USER_STREAM innerJoin T_STREAM).select {
                T_STREAM.idUserStream_fk.eq(T_USER_STREAM.idUserStream)
            }

            listStreams.forEach {
                listIds.add(it[T_STREAM.idStream])
            }
        }

        return listIds
    }
}