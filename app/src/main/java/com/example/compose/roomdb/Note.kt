package com.example.compose.roomdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "note_table")
data class Note(
    @PrimaryKey(autoGenerate = false) var noteID: Int?,
    @ColumnInfo(name = "noteName")
    val name: String?

)