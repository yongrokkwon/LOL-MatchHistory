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
                participants = type.info.participants.map { participant ->
                    MatchHistory.Info.Participant(
                        puuid = participant.puuid,
                        kills = participant.kills,
                        assists = participant.assists,
                        deaths = participant.deaths,
                        item0 = participant.item0,
                        item1 = participant.item1,
                        item2 = participant.item2,
                        item3 = participant.item3,
                        item4 = participant.item4,
                        item5 = participant.item5,
                        item6 = participant.item6,
                        perks = MatchHistory.Info.Participant.Perks(
                            styles = participant.perks.styles.map { perkStyle ->
                                MatchHistory.Info.Participant.Perks.Style(
                                    description = perkStyle.description,
                                    selections = perkStyle.selections.map { selection ->
                                        MatchHistory.Info.Participant.Perks.Style.Selection(
                                            perk = selection.perk,
                                            var1 = selection.var1,
                                            var2 = selection.var2,
                                            var3 = selection.var3
                                        )
                                    },
                                    style = perkStyle.style
                                )
                            }
                        ),
                        win = participant.win,
                        championId = participant.championId,
                        summoner1Id = participant.summoner1Id,
                        summoner2Id = participant.summoner2Id,
                        teamId = participant.teamId
                    )
                },
                platformId = type.info.platformId,
                queueId = type.info.queueId,
//                type.info.teams,
                tournamentCode = type.info.tournamentCode,
                teams = type.info.teams.map {
                    MatchHistory.Info.Team(
                        objectives = MatchHistory.Info.Team.Objectives(
                            champion = MatchHistory.Info.Team.Objectives.Champion(
                                first = it.objectives.champion.first,
                                kills = it.objectives.champion.kills
                            )
                        ),
                        teamId = it.teamId,
                        win = it.win
                    )
                }
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
