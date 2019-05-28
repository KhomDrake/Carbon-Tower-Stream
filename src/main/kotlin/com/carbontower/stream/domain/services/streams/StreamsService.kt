package com.carbontower.stream.domain.services.streams

import com.carbontower.stream.domain.services.exceptions.ChampionshipNotExist
import com.carbontower.stream.domain.services.exceptions.LinkChampionshipWithStreamAlreadyExist
import com.carbontower.stream.domain.services.exceptions.StreamNotExist

class StreamsService(private val streamsRepository: IStreamsRepository) {
    fun linkStreamWithChampionship(idChampionship: Int, idStream: Int) {
        if(streamsRepository.existChampionship(idChampionship).not()) throw ChampionshipNotExist(idChampionship)
        if(streamsRepository.existStream(idStream).not()) throw StreamNotExist(idStream)
        if(streamsRepository.existLinkChampionshipWithStream(idStream, idChampionship).not())
            throw LinkChampionshipWithStreamAlreadyExist(idChampionship, idStream)

        streamsRepository.insertLinkChampionshipWithStream(idChampionship, idStream)
    }
}