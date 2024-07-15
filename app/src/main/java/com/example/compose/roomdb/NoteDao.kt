package com.example.compose.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NoteDao {

    @Insert
    fun insertNote(note: Note)

    @Query("DELETE FROM note_table")
    fun deleteDB()


    @Query ("select * from note_table")
    fun readDb () : List<Note>

    @Query ("select max(noteID) from note_table")
    fun readMaxId () : Int


    @Query ("select * from note_table  where noteName LIKE '%' || :value || '%'")
    fun readDbForScope (value : String) : List<Note>

}