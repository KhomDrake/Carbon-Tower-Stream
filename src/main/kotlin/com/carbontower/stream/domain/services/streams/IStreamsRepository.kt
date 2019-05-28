package com.carbontower.stream.domain.services.streams

interface IStreamsRepository {
    fun existChampionship(idChampionship: Int): Boolean
    fun existStream(idStream: Int): Boolean
}