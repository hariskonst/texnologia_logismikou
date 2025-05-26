package com.example.myapplication

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.data.DataManager
import com.example.myapplication.model.TaskDetail
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DetailActivity : AppCompatActivity() {

    private lateinit var tvTitle: TextView
    private lateinit var tvDescription: TextView
    private lateinit var fabFavorite: FloatingActionButton
    private lateinit var taskName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // 1) Βρες τα Views
        tvTitle       = findViewById(R.id.tvDetailTitle)
        tvDescription = findViewById(R.id.tvDetailDescription)
        fabFavorite   = findViewById(R.id.fabFavorite)

        // 2) Πάρε το όνομα από το Intent
        taskName = intent.getStringExtra("TASK_NAME") ?: return

        // 3) Φέρε τα details από τον DataManager
        val detail: TaskDetail = DataManager.getTaskDetail(taskName)

        // 4) Γέμισε UI
        tvTitle.text       = detail.name
        tvDescription.text = detail.description

        // 5) Set αρχικό icon
        updateFabIcon()

        // 6) Listener για το FAB
        fabFavorite.setOnClickListener {
            val added = DataManager.addFavorite(taskName)
            val msg = if (added) "Προστέθηκε στα Favorites!"
            else     "Ήταν ήδη στα Favorites."
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
            updateFabIcon()
        }
    }

    // Eνημερώνει το icon ανάλογα με το αν είναι favorite
    private fun updateFabIcon() {
        val isFav = DataManager.getFavorites().contains(taskName)
        val iconRes = if (isFav)
            R.drawable.ic_favorite_24         // γεμάτη καρδιά
        else
            R.drawable.ic_favorite_border_24  // outline καρδιά
        fabFavorite.setImageResource(iconRes)
    }
}
