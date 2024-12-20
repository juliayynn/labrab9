package database

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date
import java.util.UUID

class FilmTypeConverters {

    @TypeConverter
    fun fromLocalDate(date: LocalDate?): Long? {
        return date?.atStartOfDay(ZoneId.systemDefault())?.toInstant()?.toEpochMilli()
    }

    @TypeConverter
    fun toLocalDate(millisSinceEpoch: Long?): LocalDate? {
        return millisSinceEpoch?.let {
            Date(it).toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        }
    }

    @TypeConverter
    fun toUUID(uuid: String?): UUID? {
        return UUID.fromString(uuid)
    }

    @TypeConverter
    fun fromUUID(uuid: UUID?): String? {
        return uuid?.toString()
    }

}