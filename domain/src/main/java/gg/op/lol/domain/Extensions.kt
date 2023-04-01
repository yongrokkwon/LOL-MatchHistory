package gg.op.lol.domain

fun String.romanToArabic(): String {
    val map = mapOf(
        'I' to 1,
        'V' to 5,
        'X' to 10,
        'L' to 50,
        'C' to 100,
        'D' to 500,
        'M' to 1000
    )

    var result = 0
    var prev = 0

    for (i in this.length - 1 downTo 0) {
        val value = map[this[i]] ?: error("Invalid roman numeral")
        if (value < prev) {
            result -= value
        } else {
            result += value
        }
        prev = value
    }

    return if (result == 0) "" else "$result"
}
