package com.example.roomexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.room.Room
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var database:ContactDatabase
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        database= Room.databaseBuilder(applicationContext,
            ContactDatabase::class.java,
            "contactDB").build()
        GlobalScope.launch {
            database.contactDao().insertContact(Contact(0, "John", "000000"))
        }
        val btn=findViewById<TextView>(R.id.btwn)
        btn.setOnClickListener {
            database.contactDao().getContact().observe(this) {
                Log.d("databaseEntry: ", it.toString())
            }
        }
    }
}