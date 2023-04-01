package gg.op.lol.data

inline fun <reified T : Enum<*>> enumValueOrNull(name: String): T? {
    return T::class.java.enumConstants?.firstOrNull { it.name == name }
}
