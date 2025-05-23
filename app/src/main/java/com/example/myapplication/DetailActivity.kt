package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // 1) Πάρε το όνομα του Task από το Intent
        val taskName = intent.getStringExtra("TASK_NAME") ?: "–"

        // 2) Εμφάνιση του ονόματος στο TextView
        findViewById<TextView>(R.id.tvDetail).text = "Επέλεξες: $taskName"

    }
}
