package gg.op.lol.data.mapper

import gg.op.lol.data.local.models.SearchHistorySummonerJoinEntity
import gg.op.lol.domain.models.SearchHistorySummonerJoin
import gg.op.lol.domain.models.Tier
import javax.inject.Inject

class SearchSummonerMapper @Inject constructor() :
    Mapper<SearchHistorySummonerJoinEntity, SearchHistorySummonerJoin> {
    override fun mapFromEntity(type: SearchHistorySummonerJoinEntity): SearchHistorySummonerJoin {
        return SearchHistorySummonerJoin(
            summonerName = type.summonerName,
            profileIconId = type.profileIconId,
            tier = Tier.valueOf(type.tier, type.rank),
            lastSearchedAt = type.lastSearchedAt,
            isFavorite = type.isFavorite,
            mySummoner = type.mySummoner
        )
    }

    override fun mapToEntity(type: SearchHistorySummonerJoin): SearchHistorySummonerJoinEntity {
        return SearchHistorySummonerJoinEntity(
            summonerName = type.summonerName,
            profileIconId = type.profileIconId,
            tier = type.tier.javaClass.simpleName,
            rank = type.tier.rank,
            lastSearchedAt = type.lastSearchedAt,
            isFavorite = type.isFavorite,
            mySummoner = type.mySummoner
        )
    }
}
