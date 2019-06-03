package com.carbontower.stream.domain.services.streams

import com.carbontower.stream.domain.entities.application.Stream

interface IStreamsRepository {
    fun existChampionship(idChampionship: Int): Boolean
    fun existStream(idStream: String): Boolean
    fun existLinkChampionshipWithStream(idStream: String, idChampionship: Int): Boolean
    fun insertLinkChampionshipWithStream(idChampionship: Int, idStream: String)
    fun existIdUserRole(idUserRole: Int): Boolean
    fun streamsByIdStream(idUserStream: String): List<Stream>
    fun streamsByIdsStream(idsUserStream: List<String>): List<Stream>
    fun existTwitchAccount(idUserRole: Int): Boolean
    fun getIdsUserStreams(idUserRole: Int): List<String>
    fun existAccountWithLogin(idUserRole: Int, login: String): Boolean
    fun getIdUserStreams(idUserRole: Int, login: String): String
    fun streamsByIdChampionship(idChampionship: Int): List<Stream>
}