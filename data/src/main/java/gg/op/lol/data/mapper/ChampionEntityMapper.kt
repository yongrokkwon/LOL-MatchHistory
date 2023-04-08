package gg.op.lol.data.mapper

import gg.op.lol.data.local.models.ChampionEntity
import gg.op.lol.domain.models.Champion
import javax.inject.Inject

class ChampionEntityMapper @Inject constructor() : Mapper<ChampionEntity, Champion> {
    override fun mapFromEntity(type: ChampionEntity): Champion {
        return Champion(
            imagePath = type.imagePath,
            id = type.id,
            name = type.name
        )
    }

    override fun mapToEntity(type: Champion): ChampionEntity {
        return ChampionEntity(
            imagePath = type.imagePath,
            id = type.id,
            name = type.name
        )
    }
}
