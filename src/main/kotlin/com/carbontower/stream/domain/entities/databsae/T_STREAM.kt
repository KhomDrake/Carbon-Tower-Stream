package com.carbontower.stream.domain.entities.databsae

import org.jetbrains.exposed.sql.Table

object T_STREAM : Table() {
    val idStream = varchar("idStream", length = 30).primaryKey()
    val language = varchar("language", length = 50)
    val title = varchar("title", length = 200)
    val idUserStream_fk = varchar("idUserStream_fk", length = 20) references T_USER_STREAM.idUserStream
    val viewCount = integer("viewCount")
}