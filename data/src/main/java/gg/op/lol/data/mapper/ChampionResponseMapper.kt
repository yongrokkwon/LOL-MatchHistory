package gg.op.lol.data.mapper

import gg.op.lol.data.remote.models.ChampionResponse
import gg.op.lol.domain.models.Champion
import javax.inject.Inject

class ChampionResponseMapper @Inject constructor() : Mapper<ChampionResponse.Body, Champion> {
    override fun mapFromEntity(type: ChampionResponse.Body): Champion {
        return Champion(
            key = type.key,
            name = type.name,
            imagePath = type.image.full
        )
    }

    override fun mapToEntity(type: Champion): ChampionResponse.Body {
        return ChampionResponse.Body(
            key = type.key,
            name = type.name,
            image = ChampionResponse.Body.Image(
                full = type.imagePath
            )
        )
    }
}
