package gg.op.lol.data.mapper

import gg.op.lol.data.remote.models.ItemResponse
import gg.op.lol.domain.models.Item
import javax.inject.Inject

interface ItemMapper<E, D> {
    fun mapFromEntity(type: E, id: String): D
    fun mapToEntity(type: D): E
}

class ItemResponseMapper @Inject constructor() : ItemMapper<ItemResponse.Item, Item> {
    override fun mapFromEntity(type: ItemResponse.Item, id: String): Item {
        return Item(
            id = id,
            full = type.image.full,
            group = type.image.group
        )
    }

    override fun mapToEntity(type: Item): ItemResponse.Item {
        return ItemResponse.Item(
            image = ItemResponse.Image(
                full = type.full,
                group = type.group
            )
        )
    }
}
