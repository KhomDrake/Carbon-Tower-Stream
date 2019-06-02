package com.carbontower.stream.domain.entities.databsae

import org.jetbrains.exposed.sql.Table

object T_USER_STREAM : Table() {
    val idUserStream = varchar("idUserStream", length = 20).primaryKey()
    val login = varchar("login", length = 30)
    val displayName = varchar("displayName", length = 30)
    val viewCount = integer("viewCount")
    val idUserRole_fk = integer("idUserRole_fk") references T_USER_ROLE.idUserRole
}