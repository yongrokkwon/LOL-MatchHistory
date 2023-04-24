package gg.op.lol.domain.interactor

import gg.op.lol.domain.models.Rune
import gg.op.lol.domain.repository.DDragonRepository
import javax.inject.Inject

interface GetRuneBaseUseCase {
    suspend operator fun invoke(params: Pair<String, String>): List<Rune>
}

class GetRuneUseCase @Inject constructor(
    private val ddragonRepository: DDragonRepository
) : GetRuneBaseUseCase {

    override suspend operator fun invoke(params: Pair<String, String>): List<Rune> {
        val currentVersion = params.first
        val latestVersion = params.second
        if (currentVersion != latestVersion) {
            return ddragonRepository.getRemoteRunes(latestVersion)
        }
        return emptyList()
    }
}
