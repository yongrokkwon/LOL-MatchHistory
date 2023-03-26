package gg.op.lol.domain.models

data class Rune(
    val icon: String,
    val id: Int,
    val bodies: List<Body>
) {
    data class Body(
        val icon: String,
        val id: Int
    )
}
