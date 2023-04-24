package gg.op.lol.domain.interactor

import gg.op.lol.domain.models.Champion
import gg.op.lol.domain.repository.DDragonRepository
import javax.inject.Inject

interface GetChampionsBaseUseCase {
    suspend fun invoke(params: Pair<String, String>): List<Champion>
}

class GetChampionsUseCase @Inject constructor(
    private val ddragonRepository: DDragonRepository
) : GetChampionsBaseUseCase {

    override suspend fun invoke(params: Pair<String, String>): List<Champion> {
        val currentVersion = params.first
        val latestVersion = params.second
        if (currentVersion != latestVersion) {
            return ddragonRepository.getRemoteChampions(latestVersion)
        }
        return emptyList()
    }
}
