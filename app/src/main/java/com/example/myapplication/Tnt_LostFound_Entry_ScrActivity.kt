package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Tnt_LostFound_Entry_ScrActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lost_found_entry_scr)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.managerentryscreen)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val title = findViewById<EditText>(R.id.editTitle)
        val desc = findViewById<EditText>(R.id.editDescription)
        val date = findViewById<EditText>(R.id.editDate)
        val submit = findViewById<Button>(R.id.submitButton)

        submit.setOnClickListener {
            if (title.text.isNotEmpty() && desc.text.isNotEmpty() && date.text.isNotEmpty()) {
                Toast.makeText(this, "Entry submitted!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
