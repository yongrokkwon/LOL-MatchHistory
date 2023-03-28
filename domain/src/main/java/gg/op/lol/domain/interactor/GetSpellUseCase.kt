package gg.op.lol.domain.interactor

import gg.op.lol.domain.models.Spell
import gg.op.lol.domain.repository.DDragonRepository
import javax.inject.Inject

typealias GetSpellBaseUseCase = BaseUseCase<Pair<String, String>, List<Spell>>

class GetSpellUseCase @Inject constructor(
    private val ddragonRepository: DDragonRepository
) : GetSpellBaseUseCase {

    override suspend operator fun invoke(params: Pair<String, String>): List<Spell> {
        val currentVersion = params.first
        val latestVersion = params.second
        if (currentVersion != latestVersion) {
            return ddragonRepository.getRemoteSpells(latestVersion)
        }
        return emptyList()
    }
}
