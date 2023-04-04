package gg.op.lol.data.mapper

import gg.op.lol.data.local.models.SummonerEntity
import gg.op.lol.domain.models.Summoner
import javax.inject.Inject

class SummonerEntityMapper @Inject constructor() : Mapper<SummonerEntity, Summoner> {
    override fun mapFromEntity(type: SummonerEntity): Summoner {
        return Summoner(
            summonerName = type.summonerName,
            summonerLevel = type.summonerLevel,
            profileIconId = type.profileIconId,
            histories = listOf(
                Summoner.TierHistory(
                    tier = type.tier,
                    rank = type.rank
                )
            ),
            isFavorite = type.isFavorite,
            mySummoner = type.mySummoner
        )
    }

    override fun mapToEntity(type: Summoner): SummonerEntity {
        return SummonerEntity(
            summonerName = type.summonerName,
            summonerLevel = type.summonerLevel,
            profileIconId = type.profileIconId,
            tier = type.histories.first().tier,
            rank = type.histories.first().rank,
            isFavorite = type.isFavorite,
            mySummoner = type.mySummoner
        )
    }
}
