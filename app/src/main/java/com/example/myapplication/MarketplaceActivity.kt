package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MarketplaceActivity : AppCompatActivity() {
    // 1) Εδώ ορίζεις από πριν όσα Tasks θέλεις
    private val tasks = mutableListOf(
        "Προετοιμασία αναφοράς",
        "Ενημέρωση βάσης δεδομένων",
        "Έλεγχος logs",
        "Αποστολή email",
        "Δημιουργία back-up",
        "Αναλυτική έκθεση KPI",
        "Επισκόπηση σφαλμάτων",
        "Συνεδρία brainstorming"
    )

    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marketplace)

        // 2) RecyclerView setup
        val rv = findViewById<RecyclerView>(R.id.rvTasks)
        rv.layoutManager = LinearLayoutManager(this)
        adapter = TaskAdapter(tasks) { selectedTask ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("TASK_NAME", selectedTask)
            startActivity(intent)
        }
        rv.adapter = adapter


    }
}

