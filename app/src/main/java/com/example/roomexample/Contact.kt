package com.example.roomexample

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

// here we are creating entity of database
@Entity(tableName="contact")
data class Contact (
    @PrimaryKey(autoGenerate = true)
    val id:Long,
    val name:String,
    val phone:String,
    //for update-3
    val date: Date,
    // for update-4: for migration concept we have added a new column "isActive" which will have value 0 or 1
    val isActive :Int
)
