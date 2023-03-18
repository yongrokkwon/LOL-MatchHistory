package gg.op.lol.data.mapper

import gg.op.lol.data.models.SummonerModel
import gg.op.lol.domain.models.Summoner
import javax.inject.Inject

class SummonerResponseMapper @Inject constructor() : Mapper<SummonerModel, Summoner> {
    override fun mapFromEntity(type: SummonerModel): Summoner {
        return Summoner(
            nickName = type.nickName,
            profileIconId = type.profileIconId,
//            rankInfo = Summoner(
//                type.rankInfo
//            ),
            summonerLevel = type.summonerLevel
        )
    }

    override fun mapToEntity(type: Summoner): SummonerModel {
        return SummonerModel(
            nickName = type.nickName,
            profileIconId = type.profileIconId,
//            rankInfo = type.rankInfo,
            summonerLevel = type.summonerLevel
        )
    }
}
