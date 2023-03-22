package gg.op.lol.data.models

data class MatchHistoryEntity(
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
        val teams: List<Team> = emptyList(),
        val tournamentCode: String
    ) {
        data class Participant(
            val allInPings: Int,
            val assistMePings: Int,
            val assists: Int,
            val baitPings: Int,
            val baronKills: Int,
            val basicPings: Int,
            val bountyLevel: Int,
            val challenges: Challenges,
            val champExperience: Int,
            val champLevel: Int,
            val championId: Int,
            val championName: String,
            val championTransform: Int,
            val commandPings: Int,
            val consumablesPurchased: Int,
            val damageDealtToBuildings: Int,
            val damageDealtToObjectives: Int,
            val damageDealtToTurrets: Int,
            val damageSelfMitigated: Int,
            val dangerPings: Int,
            val deaths: Int,
            val detectorWardsPlaced: Int,
            val doubleKills: Int,
            val dragonKills: Int,
            val eligibleForProgression: Boolean,
            val enemyMissingPings: Int,
            val enemyVisionPings: Int,
            val firstBloodAssist: Boolean,
            val firstBloodKill: Boolean,
            val firstTowerAssist: Boolean,
            val firstTowerKill: Boolean,
            val gameEndedInEarlySurrender: Boolean,
            val gameEndedInSurrender: Boolean,
            val getBackPings: Int,
            val goldEarned: Int,
            val goldSpent: Int,
            val holdPings: Int,
            val individualPosition: String,
            val inhibitorKills: Int,
            val inhibitorTakedowns: Int,
            val inhibitorsLost: Int,
            val item0: Int,
            val item1: Int,
            val item2: Int,
            val item3: Int,
            val item4: Int,
            val item5: Int,
            val item6: Int,
            val itemsPurchased: Int,
            val killingSprees: Int,
            val kills: Int,
            val lane: String,
            val largestCriticalStrike: Int,
            val largestKillingSpree: Int,
            val largestMultiKill: Int,
            val longestTimeSpentLiving: Int,
            val magicDamageDealt: Int,
            val magicDamageDealtToChampions: Int,
            val magicDamageTaken: Int,
            val needVisionPings: Int,
            val neutralMinionsKilled: Int,
            val nexusKills: Int,
            val nexusLost: Int,
            val nexusTakedowns: Int,
            val objectivesStolen: Int,
            val objectivesStolenAssists: Int,
            val onMyWayPings: Int,
            val participantId: Int,
            val pentaKills: Int,
            val perks: Perks,
            val physicalDamageDealt: Int,
            val physicalDamageDealtToChampions: Int,
            val physicalDamageTaken: Int,
            val profileIcon: Int,
            val pushPings: Int,
            val puuid: String,
            val quadraKills: Int,
            val riotIdName: String,
            val riotIdTagline: String,
            val role: String,
            val sightWardsBoughtInGame: Int,
            val spell1Casts: Int,
            val spell2Casts: Int,
            val spell3Casts: Int,
            val spell4Casts: Int,
            val summoner1Casts: Int,
            val summoner1Id: Int,
            val summoner2Casts: Int,
            val summoner2Id: Int,
            val summonerId: String,
            val summonerLevel: Int,
            val summonerName: String,
            val teamEarlySurrendered: Boolean,
            val teamId: Int,
            val teamPosition: String,
            val timeCCingOthers: Int,
            val timePlayed: Int,
            val totalDamageDealt: Int,
            val totalDamageDealtToChampions: Int,
            val totalDamageShieldedOnTeammates: Int,
            val totalDamageTaken: Int,
            val totalHeal: Int,
            val totalHealsOnTeammates: Int,
            val totalMinionsKilled: Int,
            val totalTimeCCDealt: Int,
            val totalTimeSpentDead: Int,
            val totalUnitsHealed: Int,
            val tripleKills: Int,
            val trueDamageDealt: Int,
            val trueDamageDealtToChampions: Int,
            val trueDamageTaken: Int,
            val turretKills: Int,
            val turretTakedowns: Int,
            val turretsLost: Int,
            val unrealKills: Int,
            val visionClearedPings: Int,
            val visionScore: Int,
            val visionWardsBoughtInGame: Int,
            val wardsKilled: Int,
            val wardsPlaced: Int,
            val win: Boolean
        ) {
            data class Challenges(
                val `12AssistStreakCount`: Int,
                val abilityUses: Int,
                val acesBefore15Minutes: Int,
                val alliedJungleMonsterKills: Double,
                val baronBuffGoldAdvantageOverThreshold: Int,
                val baronTakedowns: Int,
                val blastConeOppositeOpponentCount: Int,
                val bountyGold: Int,
                val buffsStolen: Int,
                val completeSupportQuestInTime: Int,
                val controlWardTimeCoverageInRiverOrEnemyHalf: Double,
                val controlWardsPlaced: Int,
                val damagePerMinute: Double,
                val damageTakenOnTeamPercentage: Double,
                val dancedWithRiftHerald: Int,
                val deathsByEnemyChamps: Int,
                val dodgeSkillShotsSmallWindow: Int,
                val doubleAces: Int,
                val dragonTakedowns: Int,
                val earliestBaron: Double,
                val earliestDragonTakedown: Double,
                val earlyLaningPhaseGoldExpAdvantage: Int,
                val effectiveHealAndShielding: Double,
                val elderDragonKillsWithOpposingSoul: Int,
                val elderDragonMultikills: Int,
                val enemyChampionImmobilizations: Int,
                val enemyJungleMonsterKills: Double,
                val epicMonsterKillsNearEnemyJungler: Int,
                val epicMonsterKillsWithin30SecondsOfSpawn: Int,
                val epicMonsterSteals: Int,
                val epicMonsterStolenWithoutSmite: Int,
                val fasterSupportQuestCompletion: Int,
                val firstTurretKilledTime: Double,
                val flawlessAces: Int,
                val fullTeamTakedown: Int,
                val gameLength: Double,
                val getTakedownsInAllLanesEarlyJungleAsLaner: Int,
                val goldPerMinute: Double,
                val hadOpenNexus: Int,
                val highestChampionDamage: Int,
                val highestCrowdControlScore: Int,
                val highestWardKills: Int,
                val immobilizeAndKillWithAlly: Int,
                val initialBuffCount: Int,
                val initialCrabCount: Int,
                val jungleCsBefore10Minutes: Double,
                val junglerKillsEarlyJungle: Int,
                val junglerTakedownsNearDamagedEpicMonster: Int,
                val kTurretsDestroyedBeforePlatesFall: Int,
                val kda: Double,
                val killAfterHiddenWithAlly: Int,
                val killParticipation: Double,
                val killedChampTookFullTeamDamageSurvived: Int,
                val killingSprees: Int,
                val killsNearEnemyTurret: Int,
                val killsOnLanersEarlyJungleAsJungler: Int,
                val killsOnOtherLanesEarlyJungleAsLaner: Int,
                val killsOnRecentlyHealedByAramPack: Int,
                val killsUnderOwnTurret: Int,
                val killsWithHelpFromEpicMonster: Int,
                val knockEnemyIntoTeamAndKill: Int,
                val landSkillShotsEarlyGame: Int,
                val laneMinionsFirst10Minutes: Int,
                val laningPhaseGoldExpAdvantage: Int,
                val legendaryCount: Int,
                val lostAnInhibitor: Int,
                val maxCsAdvantageOnLaneOpponent: Double,
                val maxKillDeficit: Int,
                val maxLevelLeadLaneOpponent: Int,
                val moreEnemyJungleThanOpponent: Double,
                val multiKillOneSpell: Int,
                val multiTurretRiftHeraldCount: Int,
                val multikills: Int,
                val multikillsAfterAggressiveFlash: Int,
                val mythicItemUsed: Int,
                val outerTurretExecutesBefore10Minutes: Int,
                val outnumberedKills: Int,
                val outnumberedNexusKill: Int,
                val perfectDragonSoulsTaken: Int,
                val perfectGame: Int,
                val pickKillWithAlly: Int,
                val playedChampSelectPosition: Int,
                val poroExplosions: Int,
                val quickCleanse: Int,
                val quickFirstTurret: Int,
                val quickSoloKills: Int,
                val riftHeraldTakedowns: Int,
                val saveAllyFromDeath: Int,
                val scuttleCrabKills: Int,
                val skillshotsDodged: Int,
                val skillshotsHit: Int,
                val snowballsHit: Int,
                val soloBaronKills: Int,
                val soloKills: Int,
                val soloTurretsLategame: Int,
                val stealthWardsPlaced: Int,
                val survivedSingleDigitHpCount: Int,
                val survivedThreeImmobilizesInFight: Int,
                val takedownOnFirstTurret: Int,
                val takedowns: Int,
                val takedownsAfterGainingLevelAdvantage: Int,
                val takedownsBeforeJungleMinionSpawn: Int,
                val takedownsFirstXMinutes: Int,
                val takedownsInAlcove: Int,
                val takedownsInEnemyFountain: Int,
                val teamBaronKills: Int,
                val teamDamagePercentage: Double,
                val teamElderDragonKills: Int,
                val teamRiftHeraldKills: Int,
                val thirdInhibitorDestroyedTime: Double,
                val threeWardsOneSweeperCount: Int,
                val tookLargeDamageSurvived: Int,
                val turretPlatesTaken: Int,
                val turretTakedowns: Int,
                val turretsTakenWithRiftHerald: Int,
                val twentyMinionsIn3SecondsCount: Int,
                val unseenRecalls: Int,
                val visionScoreAdvantageLaneOpponent: Double,
                val visionScorePerMinute: Double,
                val wardTakedowns: Int,
                val wardTakedownsBefore20M: Int,
                val wardsGuarded: Int
            )

            data class Perks(
                val statPerks: StatPerks,
                val styles: List<Style>
            ) {
                data class StatPerks(
                    val defense: Int,
                    val flex: Int,
                    val offense: Int
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
            val bans: List<Ban>,
            val objectives: Objectives,
            val teamId: Int,
            val win: Boolean
        ) {
            data class Ban(
                val championId: Int,
                val pickTurn: Int
            )

            data class Objectives(
                val baron: Baron,
                val champion: Champion,
                val dragon: Dragon,
                val inhibitor: Inhibitor,
                val riftHerald: RiftHerald,
                val tower: Tower
            ) {
                data class Baron(
                    val first: Boolean,
                    val kills: Int
                )

                data class Champion(
                    val first: Boolean,
                    val kills: Int
                )

                data class Dragon(
                    val first: Boolean,
                    val kills: Int
                )

                data class Inhibitor(
                    val first: Boolean,
                    val kills: Int
                )

                data class RiftHerald(
                    val first: Boolean,
                    val kills: Int
                )

                data class Tower(
                    val first: Boolean,
                    val kills: Int
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
