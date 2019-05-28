package com.carbontower.stream.domain.services.streams

import com.carbontower.stream.domain.entities.databsae.T_CHAMPIONSHIP
import com.carbontower.stream.domain.entities.databsae.T_STREAM
import com.carbontower.stream.domain.entities.databsae.T_STREAM_OF_CHAMPION
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class StreamsRepository : IStreamsRepository {
    override fun existChampionship(idChampionship: Int): Boolean {
        var exist = false

        transaction {
            exist = T_CHAMPIONSHIP.select { T_CHAMPIONSHIP.idChampionship.eq(idChampionship) }.count() != 0
        }

        return exist
    }

    override fun existStream(idStream: Int): Boolean {
        var exist = false

        transaction {
            exist = T_STREAM.select { T_STREAM.idStream.eq(idStream) }.count() != 0
        }

        return exist
    }

    override fun existLinkChampionshipWithStream(idStream: Int, idChampionship: Int): Boolean {
        var exist = false

        transaction {
            exist = T_STREAM_OF_CHAMPION.select {
                T_STREAM_OF_CHAMPION.idStream_fk.eq(idStream)
                    .and(T_STREAM_OF_CHAMPION.idChampionship_fk.eq(idChampionship))
            }.count() != 0
        }

        return exist
    }

    override fun insertLinkChampionshipWithStream(idChampionship: Int, idStream: Int) {
        transaction {
            T_STREAM_OF_CHAMPION.insert {
                it[T_STREAM_OF_CHAMPION.idChampionship_fk] = idChampionship
                it[T_STREAM_OF_CHAMPION.idStream_fk] = idStream
            }
        }
    }
}