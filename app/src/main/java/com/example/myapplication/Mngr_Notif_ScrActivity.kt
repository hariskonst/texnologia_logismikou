package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Mngr_Notif_ScrActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.mngr_notif_scr)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.managernotif)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val newnotif = findViewById<Button>(R.id.NewNotifButton)
        newnotif.setOnClickListener {
            val intent = Intent(this, TenapMngrScrActivity::class.java) // na valw thn epomenh othoni
            startActivity(intent)

        }
    }
}