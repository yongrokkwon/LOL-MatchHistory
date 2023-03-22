package gg.op.lol.data.mapper

import gg.op.lol.data.models.SummonerHistoryEntity
import gg.op.lol.domain.models.Summoner
import javax.inject.Inject

class SummonerHistoryEntityMapper @Inject constructor() : Mapper<SummonerHistoryEntity, Summoner> {
    override fun mapFromEntity(type: SummonerHistoryEntity): Summoner {
        return Summoner(
            histories = type.item.map {
                Summoner.Item(
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

    override fun mapToEntity(type: Summoner): SummonerHistoryEntity {
        return SummonerHistoryEntity(
            item = type.histories.map {
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
