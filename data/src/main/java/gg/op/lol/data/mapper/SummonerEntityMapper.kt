package gg.op.lol.data.mapper

import gg.op.lol.data.local.models.SummonerEntity
import gg.op.lol.domain.models.SummonerLocal
import javax.inject.Inject

class SummonerEntityMapper @Inject constructor() : Mapper<SummonerEntity, SummonerLocal> {
    override fun mapFromEntity(type: SummonerEntity): SummonerLocal {
        return SummonerLocal(
            nickName = type.nickName,
            isFavorite = type.isFavorite,
            mySummoner = type.mySummoner
        )
    }

    override fun mapToEntity(type: SummonerLocal): SummonerEntity {
        return SummonerEntity(
            nickName = type.nickName,
            isFavorite = type.isFavorite,
            mySummoner = type.mySummoner
        )
    }
}
