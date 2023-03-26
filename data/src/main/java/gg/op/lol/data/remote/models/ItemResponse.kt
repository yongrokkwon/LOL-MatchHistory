package gg.op.lol.data.remote.models

data class ItemResponse(
    val type: String,
    val version: String,
    val basic: Basic,
    val data: Map<String, Item>
) {
    data class Basic(
        val gold: Gold,
        val group: String,
        val hideFromAll: Boolean,
        val inStore: Boolean,
        val name: String,
        val requiredChampion: String?,
        val specialRecipe: Int?,
        val stacks: Int?,
        val consumed: Boolean,
        val depth: Int,
        val from: List<String>?,
        val into: List<String>?,
        val tags: List<String>,
        val maps: Map<String, Boolean>
    )

    data class Item(
        val name: String = "",
        val description: String = "",
        val colloq: String = "",
        val plaintext: String = "",
        val into: List<String>? = listOf(),
        val image: Image,
        val gold: Gold = Gold(),
        val tags: List<String> = listOf(),
        val maps: Map<String, Boolean> = mapOf(),
        val stats: Map<String, Double>? = mapOf()
    )

    data class Image(
        val full: String,
        val sprite: String = "",
        val group: String,
        val x: Int = 0,
        val y: Int = 0,
        val w: Int = 0,
        val h: Int = 0
    )

    data class Gold(
        val base: Int = 0,
        val purchasable: Boolean = false,
        val total: Int = 0,
        val sell: Int = 0,
        val from: List<String>? = listOf()
    )
}
