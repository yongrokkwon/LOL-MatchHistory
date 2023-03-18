package gg.op.lol.data.mapper

import gg.op.lol.data.models.SummonerEntity
import gg.op.lol.domain.models.Summoner
import javax.inject.Inject

class SummonerEntityMapper @Inject constructor() : Mapper<SummonerEntity, Summoner> {
    override fun mapFromEntity(type: SummonerEntity): Summoner {
        return Summoner(
            nickName = type.nickName,
            isFavorite = type.isFavorite,
            mySummoner = type.mySummoner
        )
    }

    override fun mapToEntity(type: Summoner): SummonerEntity {
        return SummonerEntity(
            nickName = type.nickName,
            isFavorite = type.isFavorite,
            mySummoner = type.mySummoner
        )
    }
}
