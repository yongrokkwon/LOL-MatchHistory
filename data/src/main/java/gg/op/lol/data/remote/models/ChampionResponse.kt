package gg.op.lol.data.remote.models

data class ChampionResponse(
    val data: Map<String, Body>,
    val type: String,
    val format: String,
    val version: String
) {

    data class Body(
        val blurb: String = "",
        val id: String = "",
        val image: Image,
        val info: Info? = null,
        val key: String,
        val name: String,
        val partype: String = "",
        val stats: Stats? = null,
        val tags: List<String> = emptyList(),
        val title: String = "",
        val version: String = ""
    ) {
        data class Image(
            val full: String,
            val group: String = "",
            val sprite: String = "",
            val w: Int = 0,
            val h: Int = 0,
            val x: Int = 0,
            val y: Int = 0
        )

        data class Info(
            val attack: Int,
            val defense: Int,
            val difficulty: Int,
            val magic: Int
        )

        data class Stats(
            val armor: Double,
            val armorperlevel: Double,
            val attackdamage: Double,
            val attackdamageperlevel: Double,
            val attackrange: Double,
            val attackspeed: Double,
            val attackspeedperlevel: Double,
            val crit: Double,
            val critperlevel: Double,
            val hp: Double,
            val hpperlevel: Double,
            val hpregen: Double,
            val hpregenperlevel: Double,
            val movespeed: Double,
            val mp: Double,
            val mpperlevel: Double,
            val mpregen: Double,
            val mpregenperlevel: Double,
            val spellblock: Double,
            val spellblockperlevel: Double
        )
    }
}
