package gg.op.lol.data.remote.mapper

import gg.op.lol.data.models.SummonerInfoEntity
import gg.op.lol.data.remote.models.SummonerInfoResponse
import javax.inject.Inject

class SummonerInfoMapper @Inject constructor() :
    RemoteMapper<SummonerInfoResponse, SummonerInfoEntity> {
    override fun mapFromLocal(type: SummonerInfoResponse): SummonerInfoEntity {
        return SummonerInfoEntity(
            accountId = type.accountId,
            id = type.id,
            name = type.name,
            profileIconId = type.profileIconId,
            puuid = type.puuid,
            revisionDate = type.revisionDate,
            summonerLevel = type.summonerLevel
        )
    }

    override fun mapToLocal(type: SummonerInfoEntity): SummonerInfoResponse {
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
