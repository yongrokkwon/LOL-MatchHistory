package gg.op.lol.domain.interactor

import gg.op.lol.domain.models.Spell
import gg.op.lol.domain.repository.DDragonRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

typealias GetLocalSpellsBaseUseCase = BaseUseCase<Unit, Flow<List<Spell>>>

class GetLocalSpellsUseCase @Inject constructor(
    private val ddragonRepository: DDragonRepository
) : GetLocalSpellsBaseUseCase {

    override suspend operator fun invoke(params: Unit): Flow<List<Spell>> =
        ddragonRepository.getLocalSpells()
}
