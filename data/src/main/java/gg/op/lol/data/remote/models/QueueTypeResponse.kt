package gg.op.lol.data.remote.models

class QueueTypeResponse : ArrayList<QueueTypeResponse.QueueTypeItem>() {
    data class QueueTypeItem(
        val description: String = "",
        val map: String = "",
        val notes: String = "",
        val queueId: Int = 0
    )
}
