package gg.op.lol.domain.models

data class ChampionRuneItemSpell(
    val champion: List<Champion>,
    val rune: List<Rune>,
    val item: List<Item>,
    val spell: List<Spell>
)
