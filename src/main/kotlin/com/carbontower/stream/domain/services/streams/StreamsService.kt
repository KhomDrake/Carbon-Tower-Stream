package com.carbontower.stream.domain.services.streams

import com.carbontower.stream.domain.entities.application.Stream
import com.carbontower.stream.domain.services.exceptions.*

class StreamsService(private val streamsRepository: IStreamsRepository) {
    fun linkStreamWithChampionship(idChampionship: Int, idStream: Int) {
        if(streamsRepository.existChampionship(idChampionship).not()) throw ChampionshipNotExist(idChampionship)
        if(streamsRepository.existStream(idStream).not()) throw StreamNotExist(idStream)
        if(streamsRepository.existLinkChampionshipWithStream(idStream, idChampionship).not())
            throw LinkChampionshipWithStreamAlreadyExist(idChampionship, idStream)

        streamsRepository.insertLinkChampionshipWithStream(idChampionship, idStream)
    }

    fun streamsByIdUserRole(idUserRole: Int): List<Stream> {

        if(streamsRepository.existIdUserRole(idUserRole).not()) throw UserNotExist(idUserRole)

        if(streamsRepository.existTwitchAccount(idUserRole).not()) throw NotExistTwitchAccount(idUserRole)

        val idsUserStream = streamsRepository.getIdsUserStreams(idUserRole)

        return streamsRepository.streamsByIdsStream(idsUserStream)
    }

    fun streamsByIdUserRoleAndLogin(idUserRole: Int, login: String): List<Stream> {

        if(streamsRepository.existIdUserRole(idUserRole).not()) throw UserNotExist(idUserRole)

        if(streamsRepository.existTwitchAccount(idUserRole).not()) throw NotExistTwitchAccount(idUserRole)

        if(streamsRepository.existAccountWithLogin(idUserRole, login).not()) throw NotExistTwitchAccountLogin(idUserRole, login)

        val idUserStream = streamsRepository.getIdUserStreams(idUserRole, login)

        return streamsRepository.streamsByIdStream(idUserStream)
    }
}