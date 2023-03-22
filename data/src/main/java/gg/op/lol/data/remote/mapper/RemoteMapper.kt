package gg.op.lol.data.remote.mapper

interface RemoteMapper<T, V> {

    fun mapFromLocal(type: T): V

    fun mapToLocal(type: V): T
}
