package gg.op.lol.domain.interactor

import gg.op.lol.domain.repository.DDragonRepository
import javax.inject.Inject

interface DeleteGameDataBaseUseCase {
    suspend fun invoke(): Unit
}

class DeleteGameDataUseCase @Inject constructor(
    private val ddragonRepository: DDragonRepository
) : DeleteGameDataBaseUseCase {

    override suspend fun invoke() {
        ddragonRepository.delete()
    }
}
