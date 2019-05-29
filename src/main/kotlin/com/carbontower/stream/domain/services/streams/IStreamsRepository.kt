package com.carbontower.stream.domain.services.streams

import com.carbontower.stream.domain.entities.application.Stream

interface IStreamsRepository {
    fun existChampionship(idChampionship: Int): Boolean
    fun existStream(idStream: Int): Boolean
    fun existLinkChampionshipWithStream(idStream: Int, idChampionship: Int): Boolean
    fun insertLinkChampionshipWithStream(idChampionship: Int, idStream: Int)
    fun existIdUserRole(idUserRole: Int): Boolean
    fun streamsByIdStream(idUserStream: Int): List<Stream>
    fun streamsByIdsStream(idsUserStream: List<Int>): List<Stream>
    fun existTwitchAccount(idUserRole: Int): Boolean
    fun getIdsUserStreams(idUserRole: Int): List<Int>
    fun existAccountWithLogin(idUserRole: Int, login: String): Boolean
    fun getIdUserStreams(idUserRole: Int, login: String): Int
}