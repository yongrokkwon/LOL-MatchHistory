package gg.op.lol.domain.interactor

import gg.op.lol.domain.repository.DDragonRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

interface GetLatestVersionBaseUseCase {
    suspend operator fun invoke(): Flow<String>
}

class GetLatestVersionUseCase @Inject constructor(
    private val ddragonRepository: DDragonRepository
) : GetLatestVersionBaseUseCase {

    override suspend operator fun invoke() = ddragonRepository.getVersion()
}
