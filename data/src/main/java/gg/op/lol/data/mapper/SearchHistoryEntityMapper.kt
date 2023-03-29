package gg.op.lol.data.mapper

import gg.op.lol.data.local.models.SearchHistoryEntity
import gg.op.lol.domain.models.SearchHistory
import javax.inject.Inject

class SearchHistoryEntityMapper @Inject constructor() : Mapper<SearchHistoryEntity, SearchHistory> {
    override fun mapFromEntity(type: SearchHistoryEntity): SearchHistory {
        return SearchHistory(
            nickname = type.nickname,
            icon = type.icon
        )
    }

    override fun mapToEntity(type: SearchHistory): SearchHistoryEntity {
        return SearchHistoryEntity(
            nickname = type.nickname,
            icon = type.icon
        )
    }
}
