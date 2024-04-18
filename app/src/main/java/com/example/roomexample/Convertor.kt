package com.example.roomexample

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.util.Date

// update-3
class Convertor {
    @TypeConverter
    fun fromDateToLong(value: Date):Long{
        return value.time
    }
    @TypeConverter
    fun fromLongToDate(value: Long):Date{
        return Date(value)
    }
}