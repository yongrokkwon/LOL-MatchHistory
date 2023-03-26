package gg.op.lol.domain.interactor

import gg.op.lol.domain.models.Item
import gg.op.lol.domain.repository.DDragonRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

typealias GetItemBaseUseCase = BaseUseCase<Unit, Flow<List<Item?>>>

class GetItemUseCase @Inject constructor(
    private val ddragonRepository: DDragonRepository
) : GetItemBaseUseCase {

    override suspend operator fun invoke(params: Unit): Flow<List<Item?>> =
        ddragonRepository.getRemoteItems()
}
