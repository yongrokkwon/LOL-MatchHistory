package gg.op.lol.domain.interactor

import gg.op.lol.domain.models.ChampionRuneItemSpell
import gg.op.lol.domain.repository.DDragonRepository
import javax.inject.Inject

typealias InsertGameDataBaseUseCase = BaseUseCase<ChampionRuneItemSpell, Unit>

class InsertGameDataUseCase @Inject constructor(
    private val ddragonRepository: DDragonRepository
) : InsertGameDataBaseUseCase {

    override suspend fun invoke(params: ChampionRuneItemSpell) {
        ddragonRepository.insert(params)
    }
}
