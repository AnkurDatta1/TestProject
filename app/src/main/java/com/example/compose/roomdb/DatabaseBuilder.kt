package com.example.compose.roomdb

import android.content.Context
import androidx.room.Room

object DatabaseBuilder {

    private var INSTANCE: NoteDatabase? = null
    fun getInstance(context: Context): NoteDatabase {
        if (INSTANCE == null) {
            synchronized(NoteDatabase::class) {
                INSTANCE = buildRoomDB(context)
            }
        }
        return INSTANCE!!
    }
    private fun buildRoomDB(context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            NoteDatabase::class.java,
            "geeksforgeeks-example-coroutines"
        ).build()

}