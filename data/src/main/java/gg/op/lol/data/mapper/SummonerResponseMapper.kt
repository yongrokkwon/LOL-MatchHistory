package gg.op.lol.data.mapper

import gg.op.lol.data.models.SummonerHistoryModel
import gg.op.lol.domain.models.Summoner
import javax.inject.Inject

class SummonerResponseMapper @Inject constructor() : Mapper<SummonerHistoryModel, Summoner> {
    override fun mapFromEntity(type: SummonerHistoryModel): Summoner {
        return Summoner().apply {
            addAll(
                type.map {
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
    }

    override fun mapToEntity(type: Summoner): SummonerHistoryModel {
        return SummonerHistoryModel().apply {
            addAll(
                type.map {
                    SummonerHistoryModel.Item(
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
}
