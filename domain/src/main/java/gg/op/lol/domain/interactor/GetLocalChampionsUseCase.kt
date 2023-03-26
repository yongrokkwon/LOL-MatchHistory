package gg.op.lol.domain.interactor

import gg.op.lol.domain.models.Champion
import gg.op.lol.domain.repository.DDragonRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

typealias GetLocalChampionsBaseUseCase = BaseUseCase<Unit, Flow<List<Champion>>>

class GetLocalChampionsUseCase @Inject constructor(
    private val dDragonRepository: DDragonRepository
) : GetLocalChampionsBaseUseCase {

    override suspend operator fun invoke(params: Unit): Flow<List<Champion>> =
        dDragonRepository.getLocalChampions()
}
