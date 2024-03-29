package gg.op.lol.data.local.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import gg.op.lol.data.local.models.RuneEntity

class RuneConvert {

    @TypeConverter
    fun runeBodyToJson(value: MutableList<RuneEntity.Body>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToRuneBody(value: String): MutableList<RuneEntity.Body> {
        return Gson().fromJson(value, Array<RuneEntity.Body>::class.java).toMutableList()
    }
}
