package gg.op.lol.data.mapper

import gg.op.lol.data.models.MatchHistoryEntity
import gg.op.lol.domain.models.MatchHistory
import javax.inject.Inject

class MatchHistoryEntityMapper @Inject constructor() : Mapper<MatchHistoryEntity, MatchHistory> {
    override fun mapFromEntity(type: MatchHistoryEntity): MatchHistory {
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

    override fun mapToEntity(type: MatchHistory): MatchHistoryEntity {
        return MatchHistoryEntity(
            info = MatchHistoryEntity.Info(
                gameCreation = type.info.gameCreation,
                gameDuration = type.info.gameDuration,
                gameEndTimestamp = type.info.gameEndTimestamp,
                gameId = type.info.gameId,
                gameMode = type.info.gameMode,
                gameName = type.info.gameName,
                gameStartTimestamp = type.info.gameStartTimestamp,
                gameType = type.info.gameType,
                gameVersion = type.info.gameVersion,
                mapId = type.info.mapId,
//                type.info.participants,
                platformId = type.info.platformId,
                queueId = type.info.queueId,
//                type.info.teams,
                tournamentCode = type.info.tournamentCode
            ),
            metadata = MatchHistoryEntity.Metadata(
                type.metadata.dataVersion,
                type.metadata.matchId,
                type.metadata.participants
            )
        )
    }
}
