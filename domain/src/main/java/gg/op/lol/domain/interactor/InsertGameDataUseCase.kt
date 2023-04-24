package gg.op.lol.domain.interactor

import gg.op.lol.domain.models.ChampionRuneItemSpell
import gg.op.lol.domain.repository.DDragonRepository
import javax.inject.Inject

interface InsertGameDataBaseUseCase {
    suspend fun invoke(params: ChampionRuneItemSpell): Unit
}

class InsertGameDataUseCase @Inject constructor(
    private val ddragonRepository: DDragonRepository
) : InsertGameDataBaseUseCase {

    override suspend fun invoke(params: ChampionRuneItemSpell) {
        ddragonRepository.insert(params)
    }
}
