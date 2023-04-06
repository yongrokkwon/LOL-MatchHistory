package gg.op.lol.domain.models

data class MatchHistory(
    val info: Info,
    val metadata: Metadata
) {
    data class Info(
        val gameCreation: Long,
        val gameDuration: Int,
        val gameEndTimestamp: Long,
        val gameId: Long,
        val gameMode: String,
        val gameName: String,
        val gameStartTimestamp: Long,
        val gameType: String,
        val gameVersion: String,
        val mapId: Int,
        val participants: List<Participant> = emptyList(),
        val platformId: String,
        val queueId: Int,
        val teams: List<Team>,
        val tournamentCode: String
    ) {
        data class Participant(
            val allInPings: Int = 0,
            val assistMePings: Int = 0,
            val assists: Int,
            val baitPings: Int = 0,
            val baronKills: Int = 0,
            val basicPings: Int = 0,
            val bountyLevel: Int = 0,
            val challenges: Challenges = Challenges(),
            val champExperience: Int = 0,
            val champLevel: Int = 0,
            val championId: Int,
            val championName: String = "",
            val championTransform: Int = 0,
            val commandPings: Int = 0,
            val consumablesPurchased: Int = 0,
            val damageDealtToBuildings: Int = 0,
            val damageDealtToObjectives: Int = 0,
            val damageDealtToTurrets: Int = 0,
            val damageSelfMitigated: Int = 0,
            val dangerPings: Int = 0,
            val deaths: Int,
            val detectorWardsPlaced: Int = 0,
            val doubleKills: Int,
            val dragonKills: Int = 0,
            val eligibleForProgression: Boolean = false,
            val enemyMissingPings: Int = 0,
            val enemyVisionPings: Int = 0,
            val firstBloodAssist: Boolean = false,
            val firstBloodKill: Boolean = false,
            val firstTowerAssist: Boolean = false,
            val firstTowerKill: Boolean = false,
            val gameEndedInEarlySurrender: Boolean = false,
            val gameEndedInSurrender: Boolean = false,
            val getBackPings: Int = 0,
            val goldEarned: Int = 0,
            val goldSpent: Int = 0,
            val holdPings: Int = 0,
            val individualPosition: String = "",
            val inhibitorKills: Int = 0,
            val inhibitorTakedowns: Int = 0,
            val inhibitorsLost: Int = 0,
            val item0: Int,
            val item1: Int,
            val item2: Int,
            val item3: Int,
            val item4: Int,
            val item5: Int,
            val item6: Int,
            val itemsPurchased: Int = 0,
            val killingSprees: Int = 0,
            val kills: Int,
            val lane: String = "",
            val largestCriticalStrike: Int = 0,
            val largestKillingSpree: Int = 0,
            val largestMultiKill: Int = 0,
            val longestTimeSpentLiving: Int = 0,
            val magicDamageDealt: Int = 0,
            val magicDamageDealtToChampions: Int = 0,
            val magicDamageTaken: Int = 0,
            val needVisionPings: Int = 0,
            val neutralMinionsKilled: Int = 0,
            val nexusKills: Int = 0,
            val nexusLost: Int = 0,
            val nexusTakedowns: Int = 0,
            val objectivesStolen: Int = 0,
            val objectivesStolenAssists: Int = 0,
            val onMyWayPings: Int = 0,
            val participantId: Int = 0,
            val pentaKills: Int,
            val perks: Perks,
            val physicalDamageDealt: Int = 0,
            val physicalDamageDealtToChampions: Int = 0,
            val physicalDamageTaken: Int = 0,
            val profileIcon: Int = 0,
            val pushPings: Int = 0,
            val puuid: String,
            val quadraKills: Int,
            val riotIdName: String = "",
            val riotIdTagline: String = "",
            val role: String = "",
            val sightWardsBoughtInGame: Int = 0,
            val spell1Casts: Int = 0,
            val spell2Casts: Int = 0,
            val spell3Casts: Int = 0,
            val spell4Casts: Int = 0,
            val summoner1Casts: Int = 0,
            val summoner1Id: Int, // 스펠 1
            val summoner2Casts: Int = 0,
            val summoner2Id: Int, // 스펠 2
            val summonerId: String = "",
            val summonerLevel: Int = 0,
            val summonerName: String = "",
            val teamEarlySurrendered: Boolean = false,
            val teamId: Int,
            val teamPosition: String = "",
            val timeCCingOthers: Int = 0,
            val timePlayed: Int = 0,
            val totalDamageDealt: Int = 0,
            val totalDamageDealtToChampions: Int = 0,
            val totalDamageShieldedOnTeammates: Int = 0,
            val totalDamageTaken: Int = 0,
            val totalHeal: Int = 0,
            val totalHealsOnTeammates: Int = 0,
            val totalMinionsKilled: Int = 0,
            val totalTimeCCDealt: Int = 0,
            val totalTimeSpentDead: Int = 0,
            val totalUnitsHealed: Int = 0,
            val tripleKills: Int,
            val trueDamageDealt: Int = 0,
            val trueDamageDealtToChampions: Int = 0,
            val trueDamageTaken: Int = 0,
            val turretKills: Int = 0,
            val turretTakedowns: Int = 0,
            val turretsLost: Int = 0,
            val unrealKills: Int = 0,
            val visionClearedPings: Int = 0,
            val visionScore: Int = 0,
            val visionWardsBoughtInGame: Int = 0,
            val wardsKilled: Int = 0,
            val wardsPlaced: Int = 0,
            val win: Boolean
        ) {
            data class Challenges(
                val `12AssistStreakCount`: Int = 0,
                val abilityUses: Int = 0,
                val acesBefore15Minutes: Int = 0,
                val alliedJungleMonsterKills: Int = 0,
                val baronTakedowns: Int = 0,
                val blastConeOppositeOpponentCount: Int = 0,
                val bountyGold: Int = 0,
                val buffsStolen: Int = 0,
                val completeSupportQuestInTime: Int = 0,
                val controlWardTimeCoverageInRiverOrEnemyHalf: Double = 0.0,
                val controlWardsPlaced: Int = 0,
                val damagePerMinute: Double = 0.0,
                val damageTakenOnTeamPercentage: Double = 0.0,
                val dancedWithRiftHerald: Int = 0,
                val deathsByEnemyChamps: Int = 0,
                val dodgeSkillShotsSmallWindow: Int = 0,
                val doubleAces: Int = 0,
                val dragonTakedowns: Int = 0,
                val earlyLaningPhaseGoldExpAdvantage: Double = 0.0,
                val effectiveHealAndShielding: Double = 0.0,
                val elderDragonKillsWithOpposingSoul: Int = 0,
                val elderDragonMultikills: Int = 0,
                val enemyChampionImmobilizations: Int = 0,
                val enemyJungleMonsterKills: Int = 0,
                val epicMonsterKillsNearEnemyJungler: Int = 0,
                val epicMonsterKillsWithin30SecondsOfSpawn: Int = 0,
                val epicMonsterSteals: Int = 0,
                val epicMonsterStolenWithoutSmite: Int = 0,
                val firstTurretKilledTime: Double = 0.0,
                val flawlessAces: Int = 0,
                val fullTeamTakedown: Int = 0,
                val gameLength: Double = 0.0,
                val getTakedownsInAllLanesEarlyJungleAsLaner: Int = 0,
                val goldPerMinute: Double = 0.0,
                val hadOpenNexus: Int = 0,
                val immobilizeAndKillWithAlly: Int = 0,
                val initialBuffCount: Int = 0,
                val initialCrabCount: Int = 0,
                val jungleCsBefore10Minutes: Int = 0,
                val junglerTakedownsNearDamagedEpicMonster: Int = 0,
                val kTurretsDestroyedBeforePlatesFall: Int = 0,
                val kda: Int = 0,
                val killAfterHiddenWithAlly: Int = 0,
                val killParticipation: Double = 0.0,
                val killedChampTookFullTeamDamageSurvived: Int = 0,
                val killsNearEnemyTurret: Int = 0,
                val killsOnOtherLanesEarlyJungleAsLaner: Int = 0,
                val killsOnRecentlyHealedByAramPack: Int = 0,
                val killsUnderOwnTurret: Int = 0,
                val killsWithHelpFromEpicMonster: Int = 0,
                val knockEnemyIntoTeamAndKill: Int = 0,
                val landSkillShotsEarlyGame: Int = 0,
                val laneMinionsFirst10Minutes: Int = 0,
                val laningPhaseGoldExpAdvantage: Int = 0,
                val legendaryCount: Int = 0,
                val lostAnInhibitor: Int = 0,
                val maxCsAdvantageOnLaneOpponent: Int = 0,
                val maxKillDeficit: Int = 0,
                val maxLevelLeadLaneOpponent: Int = 0,
                val moreEnemyJungleThanOpponent: Int = 0,
                val multiKillOneSpell: Int = 0,
                val multiTurretRiftHeraldCount: Int = 0,
                val multikills: Int = 0,
                val multikillsAfterAggressiveFlash: Int = 0,
                val outerTurretExecutesBefore10Minutes: Int = 0,
                val outnumberedKills: Int = 0,
                val outnumberedNexusKill: Int = 0,
                val perfectDragonSoulsTaken: Int = 0,
                val perfectGame: Int = 0,
                val pickKillWithAlly: Int = 0,
                val playedChampSelectPosition: Int = 0,
                val poroExplosions: Int = 0,
                val quickCleanse: Int = 0,
                val quickFirstTurret: Int = 0,
                val quickSoloKills: Int = 0,
                val riftHeraldTakedowns: Int = 0,
                val saveAllyFromDeath: Int = 0,
                val scuttleCrabKills: Int = 0,
                val skillshotsDodged: Int = 0,
                val skillshotsHit: Int = 0,
                val snowballsHit: Int = 0,
                val soloBaronKills: Int = 0,
                val soloKills: Int = 0,
                val stealthWardsPlaced: Int = 0,
                val survivedSingleDigitHpCount: Int = 0,
                val survivedThreeImmobilizesInFight: Int = 0,
                val takedownOnFirstTurret: Int = 0,
                val takedowns: Int = 0,
                val takedownsAfterGainingLevelAdvantage: Int = 0,
                val takedownsBeforeJungleMinionSpawn: Int = 0,
                val takedownsFirstXMinutes: Int = 0,
                val takedownsInAlcove: Int = 0,
                val takedownsInEnemyFountain: Int = 0,
                val teamBaronKills: Int = 0,
                val teamDamagePercentage: Double = 0.0,
                val teamElderDragonKills: Int = 0,
                val teamRiftHeraldKills: Int = 0,
                val threeWardsOneSweeperCount: Int = 0,
                val tookLargeDamageSurvived: Int = 0,
                val turretPlatesTaken: Int = 0,
                val turretTakedowns: Int = 0,
                val turretsTakenWithRiftHerald: Int = 0,
                val twentyMinionsIn3SecondsCount: Int = 0,
                val unseenRecalls: Int = 0,
                val visionScoreAdvantageLaneOpponent: Double = 0.0,
                val visionScorePerMinute: Double = 0.0,
                val wardTakedowns: Int = 0,
                val wardTakedownsBefore20M: Int = 0,
                val wardsGuarded: Int = 0
            )

            data class Perks(
                val statPerks: StatPerks = StatPerks(),
                val styles: List<Style>
            ) {
                data class StatPerks(
                    val defense: Int = 0,
                    val flex: Int = 0,
                    val offense: Int = 0
                )

                data class Style(
                    val description: String,
                    val selections: List<Selection>,
                    val style: Int
                ) {
                    data class Selection(
                        val perk: Int,
                        val var1: Int,
                        val var2: Int,
                        val var3: Int
                    )
                }
            }
        }

        data class Team(
            val bans: List<Ban> = emptyList(),
            val objectives: Objectives,
            val teamId: Int,
            val win: Boolean
        ) {
            data class Ban(
                val championId: Int,
                val pickTurn: Int
            )

            data class Objectives(
                val baron: Baron = Baron(),
                val champion: Champion,
                val dragon: Dragon = Dragon(),
                val inhibitor: Inhibitor = Inhibitor(),
                val riftHerald: RiftHerald = RiftHerald(),
                val tower: Tower = Tower()
            ) {
                data class Baron(
                    val first: Boolean = false,
                    val kills: Int = 0
                )

                data class Champion(
                    val first: Boolean,
                    val kills: Int
                )

                data class Dragon(
                    val first: Boolean = false,
                    val kills: Int = 0
                )

                data class Inhibitor(
                    val first: Boolean = false,
                    val kills: Int = 0
                )

                data class RiftHerald(
                    val first: Boolean = false,
                    val kills: Int = 0
                )

                data class Tower(
                    val first: Boolean = false,
                    val kills: Int = 0
                )
            }
        }
    }

    data class Metadata(
        val dataVersion: String,
        val matchId: String,
        val participants: List<String>
    )
}
