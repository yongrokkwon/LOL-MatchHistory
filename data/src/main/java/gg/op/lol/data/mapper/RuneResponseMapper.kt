package gg.op.lol.data.mapper

import gg.op.lol.data.remote.models.RuneResponse
import gg.op.lol.domain.models.Rune
import javax.inject.Inject

class RuneResponseMapper @Inject constructor() : Mapper<RuneResponse, Rune> {
    override fun mapFromEntity(type: RuneResponse): Rune {
        return Rune(
            icon = type.icon,
            id = type.id,
            bodies = type.slots.flatMap { it.runes }.map {
                Rune.Body(
                    icon = it.icon,
                    id = it.id
                )
            }
        )
    }

    override fun mapToEntity(type: Rune): RuneResponse {
        return RuneResponse(
            icon = type.icon,
            id = type.id,
            slots = convertToRuneResponseSlots(type),
            key = "",
            name = ""
        )
    }

    fun convertToRuneResponseSlots(rune: Rune): List<RuneResponse.Slot> {
        return rune.bodies.groupBy { it.id }.map { (id, bodies) ->
            RuneResponse.Slot(
                runes = bodies.map { body ->
                    RuneResponse.Slot.Rune(
                        icon = body.icon,
                        id = body.id,
                        key = "",
                        longDesc = "",
                        name = "",
                        shortDesc = ""
                    )
                }
            )
        }
    }
}
