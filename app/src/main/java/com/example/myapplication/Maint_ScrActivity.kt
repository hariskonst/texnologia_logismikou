package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Maint_ScrActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.maint_scr)

        val new = findViewById<Button>(R.id.newMaintenance)
        new.setOnClickListener {
            val intent = Intent(this, Add_Maint_ScrActivity::class.java)
            startActivity(intent)
        }
    }
}