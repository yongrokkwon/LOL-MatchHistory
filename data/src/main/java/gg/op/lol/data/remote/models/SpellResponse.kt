package gg.op.lol.data.remote.models

data class SpellResponse(
    val type: String = "",
    val version: String = "",
    val data: Map<String, Body>
) {
    data class Body(
        val cooldown: List<Int> = listOf(),
        val cooldownBurn: String = "",
        val cost: List<Int> = listOf(),
        val costBurn: String = "",
        val costType: String = "",
        val description: String = "",
        val effect: List<List<Double>> = listOf(),
        val effectBurn: List<String> = listOf(),
        val id: String = "",
        val image: Image = Image(),
        val key: Int,
        val maxammo: String = "",
        val maxrank: Int = 0,
        val modes: List<String> = listOf(),
        val name: String = "",
        val range: List<Int> = listOf(),
        val rangeBurn: String = "",
        val resource: String = "",
        val summonerLevel: Int = 0,
        val tooltip: String = "",
        val vars: List<Any> = listOf()
    ) {
        data class Image(
            val full: String = "",
            val group: String = "",
            val h: Int = 0,
            val sprite: String = "",
            val w: Int = 0,
            val x: Int = 0,
            val y: Int = 0
        )
    }
}
