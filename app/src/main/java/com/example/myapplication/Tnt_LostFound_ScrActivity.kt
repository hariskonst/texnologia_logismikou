package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Tnt_LostFound_ScrActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private val lostItemsList = mutableListOf(
        "Phone - Black Samsung - 01/05/2025",
        "Wallet - Red Leather - 20/04/2025",
        "Keys - Yellow keychain - 10/04/2025"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lost_found_scr)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.managerlostfound)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        listView = findViewById(R.id.lostItemsListView)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, lostItemsList)
        listView.adapter = adapter

        val newEntryButton = findViewById<Button>(R.id.newEntryButton)
        newEntryButton.setOnClickListener {
            val intent = Intent(this, Tnt_LostFound_Entry_ScrActivity::class.java)
            startActivity(intent)
        }
    }
}


