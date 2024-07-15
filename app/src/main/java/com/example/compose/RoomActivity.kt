package com.example.compose

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.compose.databinding.ActivityMainBinding
import com.example.compose.viemodels.RoomViewModel

class RoomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.actionBar?.hide()?: println("Something")
        var roomViewModel = ViewModelProvider(this).get(RoomViewModel::class.java)

        //enableEdgeToEdge()
        //setContentView(R.layout.activity_main)
        val binding : ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var roomObserver =roomViewModel.finalMsg.observe(this, Observer { newVal ->
            binding.tvOutput.text=newVal
            val intent = Intent(this, LauncherActivity::class.java)
            this.finish()
            startActivity(intent)

        })
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

        binding.btnAdd.setOnClickListener(View.OnClickListener {
            if ( binding.tvCount.text.toString().isEmpty()) {
                Toast.makeText(this@RoomActivity, "Note cant be empty", Toast.LENGTH_SHORT).show()
            } else {
                roomViewModel.dbInsertion(MainActivity@ this, binding.tvCount.text.toString())
            }

        })

    }
}