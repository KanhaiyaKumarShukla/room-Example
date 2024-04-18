package com.example.roomexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.room.Room
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.Date

/*
  To use room database , we need to add some dependency see this website:
  https://developer.android.com/training/data-storage/room

  In case you don't understand, what this series of 3 (10, 11, 12) videos : https://youtu.be/yPL13Iwy6oM?si=CHrfs5aQQo-1AAax
 */


/* update-1:
class MainActivity : AppCompatActivity() {
    private lateinit var database:ContactDatabase
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        database= Room.databaseBuilder(applicationContext,
            ContactDatabase::class.java,
            "contactDB").build()
        database= ContactDatabase.getDatabase(this)
        GlobalScope.launch {
            database.contactDao().insertContact(Contact(0, "John", "000000", Date())
        }
        val btn=findViewById<TextView>(R.id.btwn)
        btn.setOnClickListener {
            database.contactDao().getContact().observe(this) {
                Log.d("databaseEntry: ", it.toString())
            }
        }
        /*
        * you can see in the "App Inspection" near Terminal or LogCat that the entry is inserted in Database Inspection
        * you can also see all the data in logcat after click on textview.After click on textview , in LogCat search the Log tag "databaseEntry: " as you use in filter bar, you will see the rows.
        */
    }
}
*/
// update -2:
class MainActivity : AppCompatActivity() {
    private lateinit var database:ContactDatabase
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        database= ContactDatabase.getDatabase(this)
        GlobalScope.launch {
            database.contactDao().insertContact(Contact(0, "John", "000000", Date(), 1))   //here Date() is for update-3 and last param (1) is for update-4
        }
        val btn=findViewById<TextView>(R.id.btwn)
        btn.setOnClickListener {
            database.contactDao().getContact().observe(this) {
                Log.d("databaseEntry: ", it.toString())
            }
        }
        /*
        * you can see in the "App Inspection" near Terminal or LogCat that the entry is inserted in Database Inspection
        * you can also see all the data in logcat after click on textview.After click on textview , in LogCat search the Log tag "databaseEntry: " as you use in filter bar, you will see the rows.
        */
    }
}