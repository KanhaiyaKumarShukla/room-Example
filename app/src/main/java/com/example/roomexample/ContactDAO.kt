package com.example.roomexample

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

// here we are creating DAO interface
@Dao
interface ContactDAO {
    // Room supports coroutine-based database operations through its suspend functions, which are extension functions on instances of Room database or DAO interfaces.
    // These suspend functions allow you to perform database operations asynchronously without blocking the main thread.
    //Room automatically handles the execution of suspend functions in a coroutine context suitable for database operations.
    @Insert
    suspend fun insertContact(contact: Contact)

    @Update
    suspend fun updateContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)

    @Query("SELECT * FROM contact")
    fun getContact():LiveData<List<Contact>>
}