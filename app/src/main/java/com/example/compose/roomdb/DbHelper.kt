package com.example.compose.roomdb

class DbHelper(private val myDb: NoteDatabase) : NoteDao  {
    override fun insertNote(note: Note) {
       myDb.noteDao().insertNote(note)
    }
    override fun deleteDB() {
        myDb.noteDao().deleteDB()
    }


    override fun readDb(): List<Note> {
        return  myDb.noteDao().readDb()
    }
    override fun readDbForScope(value : String): List<Note> {
        return  myDb.noteDao().readDbForScope(value)
    }


    override fun readMaxId(): Int {
        return  myDb.noteDao().readMaxId()
    }
}