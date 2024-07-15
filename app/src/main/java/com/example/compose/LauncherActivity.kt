package com.example.compose

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.compose.TempCache.Cache
import com.example.compose.databinding.ActivityLauncherBinding
import com.example.compose.roomdb.Note
import androidx.lifecycle.Observer
import com.example.compose.adaptors.CustomAdapter
import com.example.compose.viemodels.LauncherViewModel


class LauncherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityLauncherBinding = ActivityLauncherBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var launcherViewModel = ViewModelProvider(this).get(LauncherViewModel::class.java)
        launcherViewModel._finalMSG.observe(this, Observer { newVal ->
            val data : List<Note> =Cache.dataNotes
            val adapter = CustomAdapter(data)
            binding.recyclerview.layoutManager = LinearLayoutManager(this)
            binding.recyclerview.adapter = adapter
        })

        binding.btnFab.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, RoomActivity::class.java)
            startActivity(intent)
        })
        binding.edtSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

                    launcherViewModel.dbFetchForScope(this@LauncherActivity,binding.edtSearch.text.toString())

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
        launcherViewModel.dbFetch(this)


    }
}