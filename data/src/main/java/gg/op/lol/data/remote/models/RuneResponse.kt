package gg.op.lol.data.remote.models

data class RuneResponse(
    val icon: String,
    val id: Int,
    val key: String,
    val name: String,
    val slots: List<Slot>
) {
    data class Slot(
        val runes: List<Rune>
    ) {
        data class Rune(
            val icon: String,
            val id: Int,
            val key: String,
            val longDesc: String,
            val name: String,
            val shortDesc: String
        )
    }
}
