package gg.op.lol.data.remote.mapper

import gg.op.lol.data.models.SummonerInfoModel
import gg.op.lol.data.remote.models.SummonerInfoResponse
import javax.inject.Inject

class SummonerInfoMapper @Inject constructor() :
    RemoteMapper<SummonerInfoResponse, SummonerInfoModel> {
    override fun mapFromLocal(type: SummonerInfoResponse): SummonerInfoModel {
        return SummonerInfoModel(
            accountId = type.accountId,
            id = type.id,
            name = type.name,
            profileIconId = type.profileIconId,
            puuid = type.puuid,
            revisionDate = type.revisionDate,
            summonerLevel = type.summonerLevel
        )
    }

    override fun mapToLocal(type: SummonerInfoModel): SummonerInfoResponse {
        return SummonerInfoResponse(
            accountId = type.accountId,
            id = type.id,
            name = type.name,
            profileIconId = type.profileIconId,
            puuid = type.puuid,
            revisionDate = type.revisionDate,
            summonerLevel = type.summonerLevel
        )
    }
}
