package gg.op.lol.data.local.mapper

import gg.op.lol.data.local.models.SummonerLocalEntity
import gg.op.lol.data.models.SummonerEntity
import javax.inject.Inject

class SummonerLocalMapper @Inject constructor() : LocalMapper<SummonerLocalEntity, SummonerEntity> {
    override fun mapFromLocal(type: SummonerLocalEntity): SummonerEntity {
        return SummonerEntity(
            nickName = type.nickName,
            isFavorite = type.isFavorite,
            mySummoner = type.mySummoner
        )
    }

    override fun mapToLocal(type: SummonerEntity): SummonerLocalEntity {
        return SummonerLocalEntity(
            nickName = type.nickName,
            isFavorite = type.isFavorite,
            mySummoner = type.mySummoner
        )
    }
}
