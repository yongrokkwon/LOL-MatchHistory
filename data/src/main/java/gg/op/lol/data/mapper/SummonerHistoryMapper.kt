package gg.op.lol.data.mapper

import gg.op.lol.data.models.SummonerHistoryEntity
import gg.op.lol.domain.models.SummonerHistory
import javax.inject.Inject

class SummonerHistoryMapper @Inject constructor() : Mapper<SummonerHistoryEntity, SummonerHistory> {
    override fun mapFromEntity(type: SummonerHistoryEntity): SummonerHistory {
        return SummonerHistory(
            summonerLevel = type.summonerLevel,
            item = type.item.map {
                SummonerHistory.Item(
                    freshBlood = it.freshBlood,
                    hotStreak = it.hotStreak,
                    inactive = it.inactive,
                    leagueId = it.leagueId,
                    leaguePoints = it.leaguePoints,
                    losses = it.losses,
                    queueType = it.queueType,
                    rank = it.rank,
                    summonerId = it.summonerId,
                    summonerName = it.summonerName,
                    tier = it.tier,
                    veteran = it.veteran,
                    wins = it.wins
                )
            }
        )
    }

    override fun mapToEntity(type: SummonerHistory): SummonerHistoryEntity {
        return SummonerHistoryEntity(
            type.summonerLevel,
            item = type.item.map {
                SummonerHistoryEntity.Item(
                    freshBlood = it.freshBlood,
                    hotStreak = it.hotStreak,
                    inactive = it.inactive,
                    leagueId = it.leagueId,
                    leaguePoints = it.leaguePoints,
                    losses = it.losses,
                    queueType = it.queueType,
                    rank = it.rank,
                    summonerId = it.summonerId,
                    summonerName = it.summonerName,
                    tier = it.tier,
                    veteran = it.veteran,
                    wins = it.wins
                )
            }
        )
    }
}
