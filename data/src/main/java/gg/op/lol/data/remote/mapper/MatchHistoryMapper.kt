package gg.op.lol.data.remote.mapper

import gg.op.lol.data.remote.models.MatchHistoryResponse
import gg.op.lol.domain.models.MatchHistory
import javax.inject.Inject

class MatchHistoryMapper @Inject constructor() :
    RemoteMapper<MatchHistoryResponse, MatchHistory> {
    override fun mapFromLocal(type: MatchHistoryResponse): MatchHistory {
        return MatchHistory(
            info = MatchHistory.Info(
                type.info.gameCreation,
                type.info.gameDuration,
                type.info.gameEndTimestamp,
                type.info.gameId,
                type.info.gameMode,
                type.info.gameName,
                type.info.gameStartTimestamp,
                type.info.gameType,
                type.info.gameVersion,
                type.info.mapId,
//                type.info.participants,
                platformId = type.info.platformId,
                queueId = type.info.queueId,
//                type.info.teams,
                tournamentCode = type.info.tournamentCode
            ),
            metadata = MatchHistory.Metadata(
                type.metadata.dataVersion,
                type.metadata.matchId,
                type.metadata.participants
            )
        )
    }

    override fun mapToLocal(type: MatchHistory): MatchHistoryResponse {
        return MatchHistoryResponse(
            info = MatchHistoryResponse.Info(
                type.info.gameCreation,
                type.info.gameDuration,
                type.info.gameEndTimestamp,
                type.info.gameId,
                type.info.gameMode,
                type.info.gameName,
                type.info.gameStartTimestamp,
                type.info.gameType,
                type.info.gameVersion,
                type.info.mapId,
//                type.info.participants,
                platformId = type.info.platformId,
                queueId = type.info.queueId,
//                type.info.teams,
                tournamentCode = type.info.tournamentCode
            ),
            metadata = MatchHistoryResponse.Metadata(
                type.metadata.dataVersion,
                type.metadata.matchId,
                type.metadata.participants
            )
        )
    }
}
