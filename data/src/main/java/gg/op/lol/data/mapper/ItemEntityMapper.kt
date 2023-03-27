package gg.op.lol.data.mapper

import gg.op.lol.data.local.models.ItemEntity
import gg.op.lol.domain.models.Item
import javax.inject.Inject

class ItemEntityMapper @Inject constructor() : Mapper<ItemEntity, Item> {
    override fun mapFromEntity(type: ItemEntity): Item {
        return Item(
            id = type.id,
            full = type.full,
            group = type.group
        )
    }

    override fun mapToEntity(type: Item): ItemEntity {
        return ItemEntity(
            id = type.id,
            full = type.full,
            group = type.group
        )
    }
}
