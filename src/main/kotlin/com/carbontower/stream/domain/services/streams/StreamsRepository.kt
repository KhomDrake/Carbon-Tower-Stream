package com.carbontower.stream.domain.services.streams

import com.carbontower.stream.domain.entities.application.Stream
import com.carbontower.stream.domain.entities.databsae.*
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class StreamsRepository : IStreamsRepository {
    override fun streamsByIdChampionship(idChampionship: Int): List<Stream> {
        val streams = mutableListOf<Stream>()

        transaction {
            val streamsDb = (T_STREAM_OF_CHAMPION innerJoin T_STREAM).select {
                T_STREAM_OF_CHAMPION.idChampionship_fk.eq(idChampionship)
            }

            streamsDb.forEach {
                streams.add(Stream(
                    it[T_STREAM.idStream],
                    it[T_STREAM.language],
                    it[T_STREAM.title],
                    it[T_STREAM.idUserStream_fk],
                    it[T_STREAM.viewCount]
                ))
            }
        }

        return streams
    }

    override fun streamsByIdsStream(idsUserStream: List<String>): List<Stream> {
        val streams = mutableListOf<Stream>()

        transaction {
            idsUserStream.forEach {
                val idUserStream = it
                val streamsDb = T_STREAM.select { T_STREAM.idUserStream_fk.eq(idUserStream) }
                streamsDb.forEach {
                    streams.add(
                        Stream(
                            it[T_STREAM.idStream],
                            it[T_STREAM.language],
                            it[T_STREAM.title],
                            it[T_STREAM.idUserStream_fk],
                            it[T_STREAM.viewCount]
                        )
                    )
                }
            }
        }

        return streams
    }

    override fun existTwitchAccount(idUserRole: Int): Boolean {
        var exist = false

        transaction {
            exist = T_USER_STREAM.select { T_USER_STREAM.idUserRole_fk.eq(idUserRole) }.count() != 0
        }

        return exist
    }

    override fun getIdsUserStreams(idUserRole: Int): List<String> {
        val ids = mutableListOf<String>()

        transaction {
            val idsDb = T_USER_STREAM.select { T_USER_STREAM.idUserRole_fk.eq(idUserRole) }

            idsDb.forEach {
                ids.add(it[T_USER_STREAM.idUserStream])
            }
        }

        return ids
    }

    override fun existAccountWithLogin(idUserRole: Int, login: String): Boolean {
        var exist = false

        transaction {
            exist = T_USER_STREAM.select {
                T_USER_STREAM.idUserRole_fk.eq(idUserRole).and(T_USER_STREAM.login.eq(login))
            }.count() != 0
        }

        return exist
    }

    override fun getIdUserStreams(idUserRole: Int, login: String): String {
        var id = ""

        transaction {
            val idsDb = T_USER_STREAM.select { T_USER_STREAM.idUserRole_fk.eq(idUserRole).and(T_USER_STREAM.login.eq(login)) }

            idsDb.forEach {
                id = it[T_USER_STREAM.idUserStream]
            }
        }

        return id
    }

    override fun existIdUserRole(idUserRole: Int): Boolean {
        var exist = false

        transaction {
            exist = T_USER_ROLE.select { T_USER_ROLE.idUserRole.eq(idUserRole) }.count() != 0
        }

        return exist
    }

    override fun streamsByIdStream(idUserStream: String): List<Stream> {
        val streams = mutableListOf<Stream>()

        transaction {
            val streamsDb = T_STREAM.select { T_STREAM.idUserStream_fk.eq(idUserStream) }
            streamsDb.forEach {
                streams.add(
                    Stream(
                        it[T_STREAM.idStream],
                        it[T_STREAM.language],
                        it[T_STREAM.title],
                        it[T_STREAM.idUserStream_fk],
                        it[T_STREAM.viewCount]
                    )
                )
            }
        }

        return streams.toList()
    }

    override fun existChampionship(idChampionship: Int): Boolean {
        var exist = false

        transaction {
            exist = T_CHAMPIONSHIP.select { T_CHAMPIONSHIP.idChampionship.eq(idChampionship) }.count() != 0
        }

        return exist
    }

    override fun existStream(idStream: String): Boolean {
        var exist = false

        transaction {
            exist = T_STREAM.select { T_STREAM.idStream.eq(idStream) }.count() != 0
        }

        return exist
    }

    override fun existLinkChampionshipWithStream(idStream: String, idChampionship: Int): Boolean {
        var exist = false

        transaction {
            exist = T_STREAM_OF_CHAMPION.select {
                T_STREAM_OF_CHAMPION.idStream_fk.eq(idStream)
                    .and(T_STREAM_OF_CHAMPION.idChampionship_fk.eq(idChampionship))
            }.count() != 0
        }

        return exist
    }

    override fun insertLinkChampionshipWithStream(idChampionship: Int, idStream: String) {
        transaction {
            T_STREAM_OF_CHAMPION.insert {
                it[T_STREAM_OF_CHAMPION.idChampionship_fk] = idChampionship
                it[T_STREAM_OF_CHAMPION.idStream_fk] = idStream
            }
        }
    }
}