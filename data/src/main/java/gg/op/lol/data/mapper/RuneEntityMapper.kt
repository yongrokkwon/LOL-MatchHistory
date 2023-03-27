package gg.op.lol.data.mapper

import gg.op.lol.data.local.models.RuneEntity
import gg.op.lol.domain.models.Rune
import javax.inject.Inject

class RuneEntityMapper @Inject constructor() : Mapper<RuneEntity, Rune> {
    override fun mapFromEntity(type: RuneEntity): Rune {
        return Rune(
            icon = type.icon,
            id = type.id,
            bodies = type.slots.map {
                Rune.Body(
                    icon = it.icon,
                    id = it.id
                )
            }
        )
    }

    override fun mapToEntity(type: Rune): RuneEntity {
        return RuneEntity(
            icon = type.icon,
            id = type.id,
            slots = type.bodies.map {
                RuneEntity.Body(
                    icon = it.icon,
                    id = it.id
                )
            }
        )
    }
}
