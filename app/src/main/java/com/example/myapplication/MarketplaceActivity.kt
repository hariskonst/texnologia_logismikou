package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.data.DataManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.widget.Toast

class MarketplaceActivity : AppCompatActivity() {

    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataManager.init(applicationContext)
        setContentView(R.layout.activity_marketplace)


        // 1) Βρες το RecyclerView
        val rv = findViewById<RecyclerView>(R.id.rvTasks)

        // 2) Ορισμός LayoutManager
        rv.layoutManager = LinearLayoutManager(this)

        // 3) Load των δεδομένων
        val tasksList = DataManager.getAllTasks()

        // 4) Δημιούργησε τον adapter
        adapter = TaskAdapter(tasksList) { selectedTask ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("TASK_NAME", selectedTask)
            startActivity(intent)
        }

        // 5) Ανάθεση adapter στο RecyclerView
        rv.adapter = adapter

        val fabFav = findViewById<FloatingActionButton>(R.id.fabShowFavorites)
        fabFav.setOnClickListener {
            // Φόρτωσε μόνο τα favorites
            val favs = DataManager.getFavorites()
            adapter.updateData(favs)
            // Προαιρετικό: ενημέρωση του χρήστη
            Toast.makeText(this, "Εμφάνιση Αγαπημένων", Toast.LENGTH_SHORT).show()
        }
    }
}