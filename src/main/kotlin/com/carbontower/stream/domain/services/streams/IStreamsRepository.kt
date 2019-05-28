package com.carbontower.stream.domain.services.streams

interface IStreamsRepository {
    fun existChampionship(idChampionship: Int): Boolean
    fun existStream(idStream: Int): Boolean
    fun existLinkChampionshipWithStream(idStream: Int, idChampionship: Int): Boolean
    fun insertLinkChampionshipWithStream(idChampionship: Int, idStream: Int)
}