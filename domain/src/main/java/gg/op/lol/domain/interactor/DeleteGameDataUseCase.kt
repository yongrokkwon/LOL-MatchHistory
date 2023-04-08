package gg.op.lol.domain.interactor

import gg.op.lol.domain.repository.DDragonRepository
import javax.inject.Inject

typealias DeleteGameDataBaseUseCase = BaseUseCase<Unit, Unit>

class DeleteGameDataUseCase @Inject constructor(
    private val ddragonRepository: DDragonRepository
) : DeleteGameDataBaseUseCase {

    override suspend fun invoke(params: Unit) {
        ddragonRepository.delete()
    }
}
