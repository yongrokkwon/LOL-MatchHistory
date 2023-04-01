package gg.op.lol.data.mapper

import gg.op.lol.data.enumValueOrNull
import gg.op.lol.data.remote.models.SummonerHistoryResponse
import gg.op.lol.domain.models.QueueType
import gg.op.lol.domain.models.Summoner
import javax.inject.Inject

class SummonerHistoryResponseMapper @Inject constructor() :
    Mapper<SummonerHistoryResponse, Summoner.Body> {
    override fun mapFromEntity(type: SummonerHistoryResponse): Summoner.Body {
        return Summoner.Body(
            freshBlood = type.freshBlood,
            hotStreak = type.hotStreak,
            inactive = type.inactive,
            leagueId = type.leagueId,
            leaguePoints = type.leaguePoints,
            losses = type.losses,
            queueType = enumValueOrNull(type.queueType) ?: QueueType.RANKED_SOLO_5X5,
            rank = type.rank,
            summonerId = type.summonerId,
            summonerName = type.summonerName,
            tier = type.tier,
            veteran = type.veteran,
            wins = type.wins
        )
    }

    override fun mapToEntity(type: Summoner.Body): SummonerHistoryResponse {
        return SummonerHistoryResponse(
            freshBlood = type.freshBlood,
            hotStreak = type.hotStreak,
            inactive = type.inactive,
            leagueId = type.leagueId,
            leaguePoints = type.leaguePoints,
            losses = type.losses,
            queueType = type.queueType.name,
            rank = type.rank,
            summonerId = type.summonerId,
            summonerName = type.summonerName,
            tier = type.tier,
            veteran = type.veteran,
            wins = type.wins
        )
    }
}
