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
    private val lostItemsList = listOf(
        "Phone - Black Samsung - 01/05/2025",
        "Wallet - Red Leather - 20/04/2025",
        "Keys - Yellow keychain - 10/04/2025",
        "Dog Toy - Blue rubber bone - 03/04/2025",
        "Backpack - Grey Nike - 30/03/2025",
        "Sunglasses - Rayban black - 25/03/2025",
        "Laptop Charger - Dell - 22/03/2025",
        "Notebook - Math notes - 18/03/2025",
        "Water Bottle - Blue steel - 15/03/2025",
        "Jacket - Black leather - 12/03/2025",
        "Hat - Red beanie - 09/03/2025",
        "Watch - Silver analog - 06/03/2025",
        "Earbuds - White wireless - 02/03/2025",
        "Keys - Single car key - 28/02/2025",
        "Umbrella - Foldable black - 25/02/2025"
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


