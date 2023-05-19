package com.yangsooplus.snapkarlo.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import java.time.LocalDateTime

@ProvidedTypeConverter
class LocalDateTimeConverter {
    @TypeConverter
    fun localDateTime2String(localDateTime: LocalDateTime): String {
        return localDateTime.toString()
    }

    @TypeConverter
    fun string2LocalDateTime(string: String): LocalDateTime {
        return LocalDateTime.parse(string)
    }
}