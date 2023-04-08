package gg.op.lol.data.mapper

import gg.op.lol.data.remote.models.SpellResponse
import gg.op.lol.domain.models.Spell
import javax.inject.Inject

class SpellResponseMapper @Inject constructor() : Mapper<SpellResponse.Body, Spell> {
    override fun mapFromEntity(type: SpellResponse.Body): Spell {
        return Spell(
            id = type.key,
            imagePath = type.image.full
        )
    }

    override fun mapToEntity(type: Spell): SpellResponse.Body {
        return SpellResponse.Body(
            key = type.id,
            image = SpellResponse.Body.Image(
                full = type.imagePath
            )
        )
    }
}
