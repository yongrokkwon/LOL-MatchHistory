package gg.op.lol.domain.interactor

import gg.op.lol.domain.models.Spell
import gg.op.lol.domain.repository.DDragonRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

typealias GetSpellBaseUseCase = BaseUseCase<Unit, Flow<List<Spell?>>>

class GetSpellUseCase @Inject constructor(
    private val ddragonRepository: DDragonRepository
) : GetSpellBaseUseCase {

    override suspend operator fun invoke(params: Unit): Flow<List<Spell?>> =
        ddragonRepository.getRemoteSpells()
}
