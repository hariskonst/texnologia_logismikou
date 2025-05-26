package com.example.myapplication

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class ComplaintActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complaint)

        // Αρχικοποίηση persistence του manager
        ComplaintManager.init(this)

        // Βρες τα πεδία subject και body
        val etSubject = findViewById<EditText>(R.id.etSubject)
        val etBody    = findViewById<EditText>(R.id.etComplaintBody)
        val btnSubmit = findViewById<MaterialButton>(R.id.btnSubmitComplaint)

        btnSubmit.setOnClickListener {
            val subject = etSubject.text.toString().trim()
            val body    = etBody.text.toString().trim()
            if (subject.isEmpty() || body.isEmpty()) {
                Toast.makeText(
                    this,
                    "Συμπλήρωσε και το θέμα και το κείμενο πριν υποβάλεις.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                // Προσθήκη καταγγελίας με θέμα και κείμενο
                ComplaintManager.addComplaint(subject, body)
                Toast.makeText(
                    this,
                    "Η καταγγελία υποβλήθηκε!",
                    Toast.LENGTH_SHORT
                ).show()
                // Καθαρισμός πεδίων
                etSubject.text.clear()
                etBody.text.clear()
            }
        }
    }
}
