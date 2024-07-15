package com.example.compose.viemodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compose.roomdb.DatabaseBuilder
import com.example.compose.TempCache.Cache
import com.example.compose.roomdb.DbHelper
import com.example.compose.roomdb.Note
import com.example.compose.roomdb.NoteDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LauncherViewModel : ViewModel() {
    var _finalMSG = MutableLiveData<List<Note>>()
    var finalMsg: LiveData<List<Note>> = _finalMSG
    fun dbFetch(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            var database: NoteDatabase = DatabaseBuilder.getInstance(context)

            var notes = DbHelper(database).readDb()
            Cache.dataNotes = notes



            withContext(Dispatchers.Main) {
                _finalMSG.value = notes
            }

        }

    }
    fun dbFetchForScope(context:Context, value: String){
        viewModelScope.launch(Dispatchers.IO) {
            var database : NoteDatabase = DatabaseBuilder.getInstance(context)

            var notes = DbHelper(database).readDbForScope(value)
            Cache.dataNotes=notes



            withContext(Dispatchers.Main) {
                _finalMSG.value=notes
            }

        }


    }



}