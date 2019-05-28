package com.carbontower.stream.domain.entities.databsae

import org.jetbrains.exposed.sql.Table

object T_STREAM_OF_CHAMPION : Table() {
    val idStream_fk = integer("idPlayer_fk").primaryKey(0) references T_STREAM.idStream
    val idChampionship_fk = integer("idChampionship_fk").primaryKey(1) references T_CHAMPIONSHIP.idChampionship
    init {
        index(true,
            idStream_fk,
            idChampionship_fk
        )
    }
}