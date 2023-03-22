package gg.op.lol.data.local.mapper

interface LocalMapper<T, V> {

    fun mapFromLocal(type: T): V

    fun mapToLocal(type: V): T
}
