package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class TntListScrActivity : AppCompatActivity(){

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.tnt_list_scr)

        val confirm = findViewById<Button>(R.id.gotoadd)
        confirm.setOnClickListener {
            val intent = Intent(this, NewTntAddScrActivity::class.java)
            startActivity(intent)
            }
        }
    }
