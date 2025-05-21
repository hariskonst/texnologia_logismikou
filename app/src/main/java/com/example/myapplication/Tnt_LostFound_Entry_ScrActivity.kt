package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Tnt_LostFound_Entry_ScrActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lost_found_entry_scr)

        // Εφαρμογή συστήματος insets (status/navigation bar space)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.managerentryscreen)) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Ανάκτηση αναφορών στα πεδία και το κουμπί
        val titleField = findViewById<EditText>(R.id.editTitle)
        val descField = findViewById<EditText>(R.id.editDescription)
        val dateField = findViewById<EditText>(R.id.editDate)
        val submitButton = findViewById<Button>(R.id.submitButton)

        submitButton.setOnClickListener {
            val title = titleField.text.toString().trim()
            val desc = descField.text.toString().trim()
            val date = dateField.text.toString().trim()

            if (title.isNotEmpty() && desc.isNotEmpty() && date.isNotEmpty()) {
                val newItem = "$title - $desc - $date"

                // Επιστροφή της καταχώρησης στο calling Activity
                val resultIntent = Intent().apply {
                    putExtra("newLostItem", newItem)
                }

                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}


