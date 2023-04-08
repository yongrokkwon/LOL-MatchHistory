package gg.op.lol.data.mapper

import gg.op.lol.data.local.models.SpellEntity
import gg.op.lol.domain.models.Spell
import javax.inject.Inject

class SpellEntityMapper @Inject constructor() : Mapper<SpellEntity, Spell> {
    override fun mapFromEntity(type: SpellEntity): Spell {
        return Spell(
            imagePath = type.imagePath,
            id = type.id
        )
    }

    override fun mapToEntity(type: Spell): SpellEntity {
        return SpellEntity(
            imagePath = type.imagePath,
            id = type.id
        )
    }
}
