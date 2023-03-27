package gg.op.lol.data.mapper

import gg.op.lol.data.remote.models.SummonerHistoryResponse
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
            queueType = type.queueType,
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
            queueType = type.queueType,
            rank = type.rank,
            summonerId = type.summonerId,
            summonerName = type.summonerName,
            tier = type.tier,
            veteran = type.veteran,
            wins = type.wins
        )
    }
}
