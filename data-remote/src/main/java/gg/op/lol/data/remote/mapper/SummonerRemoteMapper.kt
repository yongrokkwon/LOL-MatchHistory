package gg.op.lol.data.remote.mapper

import gg.op.lol.data.models.SummonerModel
import gg.op.lol.data.remote.models.SummonerResponseModel
import javax.inject.Inject

class SummonerRemoteMapper @Inject constructor(

) : RemoteMapper<SummonerResponseModel, SummonerModel> {
    override fun mapFromLocal(type: SummonerResponseModel): SummonerModel {
        return SummonerModel(
            nickName = type.nickName,
            profileIconId = type.profileIconId,
            rankInfo = type.rankInfo.map {
                SummonerModel.RankInfo(
                    leagueId = it.leagueId,
                    leaguePoints = it.leaguePoints,
                    losses = it.losses,
                    queueType = it.queueType,
                    queueTypeNm = it.queueTypeNm,
                    rank = it.rank,
                    tier = it.tier,
                    wins = it.wins
                )
            },
            summonerLevel = type.summonerLevel
        )
    }

    override fun mapToLocal(type: SummonerModel): SummonerResponseModel {
        return SummonerResponseModel(
            nickName = type.nickName,
            profileIconId = type.profileIconId,
            rankInfo = type.rankInfo.map {
                SummonerResponseModel.RankInfo(
                    leagueId = it.leagueId,
                    leaguePoints = it.leaguePoints,
                    losses = it.losses,
                    queueType = it.queueType,
                    queueTypeNm = it.queueTypeNm,
                    rank = it.rank,
                    tier = it.tier,
                    wins = it.wins
                )
            },
            summonerLevel = type.summonerLevel
        )
    }
}
