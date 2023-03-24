package gg.op.lol.domain.interactor

import gg.op.lol.domain.models.Champion
import gg.op.lol.domain.repository.DDragonRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

typealias GetChampionsBaseUseCase = BaseUseCase<Unit, Flow<List<Champion?>>>

class GetChampionsUseCase @Inject constructor(
    private val dDragonRepository: DDragonRepository
) : GetChampionsBaseUseCase {

    override suspend operator fun invoke(params: Unit): Flow<List<Champion?>> =
        dDragonRepository.getChampions()
}
