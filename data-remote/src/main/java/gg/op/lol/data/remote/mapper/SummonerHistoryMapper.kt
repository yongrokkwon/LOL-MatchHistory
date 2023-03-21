package gg.op.lol.data.remote.mapper

import gg.op.lol.data.models.SummonerHistoryEntity
import gg.op.lol.data.remote.models.SummonerHistoryResponse
import javax.inject.Inject

class SummonerHistoryMapper @Inject constructor() :
    RemoteMapper<SummonerHistoryResponse, SummonerHistoryEntity> {
    override fun mapFromLocal(type: SummonerHistoryResponse): SummonerHistoryEntity {
        return SummonerHistoryEntity(
            item = type.map {
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

    override fun mapToLocal(type: SummonerHistoryEntity): SummonerHistoryResponse {
        return SummonerHistoryResponse().apply {
            type.item.map {
                SummonerHistoryResponse.Item(
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
        }
    }
}
