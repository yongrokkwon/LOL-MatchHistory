package gg.op.lol.domain.interactor

import gg.op.lol.domain.models.Item
import gg.op.lol.domain.repository.DDragonRepository
import javax.inject.Inject

interface GetItemBaseUseCase {
    suspend operator fun invoke(params: Pair<String, String>): List<Item>
}

class GetItemUseCase @Inject constructor(
    private val ddragonRepository: DDragonRepository
) : GetItemBaseUseCase {

    override suspend operator fun invoke(params: Pair<String, String>): List<Item> {
        val currentVersion = params.first
        val latestVersion = params.second
        if (currentVersion != latestVersion) {
            return ddragonRepository.getRemoteItems(latestVersion)
        }
        return emptyList()
    }
}
