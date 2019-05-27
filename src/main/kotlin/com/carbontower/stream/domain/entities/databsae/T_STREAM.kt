package com.carbontower.stream.domain.entities.databsae

import org.jetbrains.exposed.sql.Table

object T_STREAM : Table() {
    val idStream = integer("idStream").primaryKey()
    val language = varchar("language", length = 50)
    val title = varchar("title", length = 50)
    val idUserStream_fk = integer("idUserStream_fk") references T_USER_STREAM.idUserStream
    val idGameAPI = varchar("idGameAPI", length = 50)
    val userName = varchar("userName", length = 50)
    val viewCount = integer("viewCount")
    val thumbnailUrl = varchar("thumbnailUrl", length = 100)
}