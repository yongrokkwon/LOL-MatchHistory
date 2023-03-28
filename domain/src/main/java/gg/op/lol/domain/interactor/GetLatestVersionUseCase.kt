package gg.op.lol.domain.interactor

import gg.op.lol.domain.repository.DDragonRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

typealias GetLatestVersionBaseUseCase = BaseUseCase<Unit, Flow<String>>

class GetLatestVersionUseCase @Inject constructor(
    private val ddragonRepository: DDragonRepository
) : GetLatestVersionBaseUseCase {

    override suspend operator fun invoke(params: Unit) = ddragonRepository.getVersion()
}
