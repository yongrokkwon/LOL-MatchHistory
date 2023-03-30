package gg.op.lol.data.mapper

import gg.op.lol.data.local.models.SearchHistoryEntity
import gg.op.lol.domain.models.SearchHistory
import gg.op.lol.domain.models.Tier
import javax.inject.Inject

class SearchHistoryEntityMapper @Inject constructor() : Mapper<SearchHistoryEntity, SearchHistory> {
    override fun mapFromEntity(type: SearchHistoryEntity): SearchHistory {
        return SearchHistory(
            nickname = type.nickname,
            icon = type.icon,
            tier = Tier.valueOf(type.tier, type.rank)
        )
    }

    override fun mapToEntity(type: SearchHistory): SearchHistoryEntity {
        return SearchHistoryEntity(
            nickname = type.nickname,
            icon = type.icon,
            tier = type.tier.javaClass.simpleName,
            rank = type.tier.rank
        )
    }
}
