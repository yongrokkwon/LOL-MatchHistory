package gg.op.lol.data.repository.ddragon

import gg.op.lol.data.local.models.ChampionEntity

interface DDragonLocal {
    fun getChampion(key: String): ChampionEntity?
    fun getChampions(): List<ChampionEntity>
    fun insertChampion(championEntity: ChampionEntity)
}
