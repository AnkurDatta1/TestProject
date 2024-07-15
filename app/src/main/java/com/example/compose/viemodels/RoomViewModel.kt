package com.example.compose.viemodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compose.roomdb.DatabaseBuilder
import com.example.compose.roomdb.DbHelper
import com.example.compose.roomdb.Note
import com.example.compose.roomdb.NoteDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RoomViewModel : ViewModel() {
    var _finalMSG = MutableLiveData<String>()
    var finalMsg : LiveData<String> =_finalMSG
    fun dbInsertion(context:Context,text:String){
        viewModelScope.launch(Dispatchers.IO) {
            var database : NoteDatabase = DatabaseBuilder.getInstance(context)
            var count : Int = DbHelper(database).readMaxId()
            count++
            Log.d("Ankur max id", ""+count)
            var note : Note = Note(count,text)
            DbHelper(database).insertNote(note)

            var notes = DbHelper(database).readDb()
            var temp :String=""
            for (note in notes) {
                temp=temp+note
                Log.d("Ankur", ""+note)
            }

           temp.let {
               withContext(Dispatchers.Main) {
                _finalMSG.value=it
               }
            }
        }

    }
}