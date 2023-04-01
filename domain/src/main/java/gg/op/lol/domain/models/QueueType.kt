package gg.op.lol.domain.models

// https://static.developer.riotgames.com/docs/lol/queues.json
enum class QueueType(val queueId: Int) {
    NORMAL(400),
    RANKED_SOLO_5X5(420),
    RANKED_FLEX_SR(440),
    ARAM(450),
    CLASH(700),
    AI_01(820), AI_02(830), AI_03(840), AI_04(850),
    URF(900),
    PORO(920),
    OFA(1020),
    TUTORIAL_01(2000), TUTORIAL_02(2010), TUTORIAL_03(2020),
    ETC(0);

    companion object {
        private val map = QueueType.values().associateBy(QueueType::queueId)
        fun fromQueueId(queueId: Int) = map[queueId]
    }
//    fun valueOf(queueId: Int): QueueType {
//        return when (this.queueId) {
//            NORMAL.queueId -> NORMAL
//            RANKED_SOLO_5X5.queueId -> RANKED_SOLO_5X5
//            RANKED_FLEX_SR.queueId -> RANKED_FLEX_SR
//            ARAM.queueId -> ARAM
//            CLASH.queueId -> CLASH
//            in AI_01.queueId..AI_01.queueId -> AI_01
//            URF.queueId -> URF
//            PORO.queueId -> PORO
//            OFA.queueId -> OFA
//            in TUTORIAL_01.queueId..TUTORIAL_01.queueId -> TUTORIAL_01
//            else -> ETC
//        }
//    }
}
