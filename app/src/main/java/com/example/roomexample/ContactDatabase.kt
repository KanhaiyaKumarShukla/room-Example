package com.example.roomexample

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/*
update=1:

@Database(entities = [Contact::class], version = 1)
abstract class ContactDatabase :RoomDatabase(){

    abstract fun contactDao():ContactDAO
}
*/


/*       update-2 and 3:
@Database(entities = [Contact::class], version = 1)
@TypeConverters(Convertor::class)
abstract class ContactDatabase :RoomDatabase(){

    abstract fun contactDao():ContactDAO

    //we are adding companion object to use singleton object:
    companion object{
        @Volatile
        private var INSTANCE : ContactDatabase?=null
        fun getDatabase(context: Context):ContactDatabase{

            if (INSTANCE==null){
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        ContactDatabase::class.java,
                        "contactDB"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }
}
*/

//update-4
//here you can see we have updated the version from 1 to 2
@Database(entities = [Contact::class], version = 2)
@TypeConverters(Convertor::class)
abstract class ContactDatabase :RoomDatabase(){

    abstract fun contactDao():ContactDAO

    //we are adding companion object to use singleton object:
    companion object{

        private val migration_1_2=object : Migration(1, 2){
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE contact ADD COLUMN isActive NOT NULL DEFAULT(1)")
            }
        }

        @Volatile
        private var INSTANCE : ContactDatabase?=null
        fun getDatabase(context: Context):ContactDatabase{

            if (INSTANCE==null){
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        ContactDatabase::class.java,
                        "contactDB"
                    ).addMigrations(migration_1_2).build()
                }
            }
            return INSTANCE!!
        }
    }
}