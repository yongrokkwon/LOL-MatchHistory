package gg.op.lol.domain.interactor

import gg.op.lol.domain.models.Rune
import gg.op.lol.domain.repository.DDragonRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

typealias GetRuneBaseUseCase = BaseUseCase<Unit, Flow<List<Rune?>>>

class GetRuneUseCase @Inject constructor(
    private val ddragonRepository: DDragonRepository
) : GetRuneBaseUseCase {

    override suspend operator fun invoke(params: Unit): Flow<List<Rune?>> =
        ddragonRepository.getRemoteRunes()
}
