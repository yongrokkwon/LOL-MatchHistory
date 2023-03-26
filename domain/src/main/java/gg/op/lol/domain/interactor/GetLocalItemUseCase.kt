package gg.op.lol.domain.interactor

import gg.op.lol.domain.models.Item
import gg.op.lol.domain.repository.DDragonRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

typealias GetLocalItemBaseUseCase = BaseUseCase<Unit, Flow<List<Item>>>

class GetLocalItemUseCase @Inject constructor(
    private val ddragonRepository: DDragonRepository
) : GetLocalItemBaseUseCase {

    override suspend operator fun invoke(params: Unit): Flow<List<Item>> =
        ddragonRepository.getLocalItems()
}
