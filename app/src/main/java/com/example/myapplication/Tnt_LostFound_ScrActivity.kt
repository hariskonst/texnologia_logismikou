package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button


class Tnt_LostFound_ScrActivity : AppCompatActivity() {

    private lateinit var lostItems: MutableList<String>
    private lateinit var adapter: SimpleAdapter
    private lateinit var listView: ListView

    private val newEntryLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val newItem = result.data?.getStringExtra("newLostItem")
            newItem?.let {
                lostItems.add(it)
                displayList()
                LostFoundStorage.saveList(this, lostItems)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lost_found_scr)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.managerlostfound)) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        lostItems = LostFoundStorage.loadList(this)
        listView = findViewById(R.id.lostItemsListView)

        displayList()

        findViewById<Button>(R.id.newEntryButton).setOnClickListener {
            val intent = Intent(this, Tnt_LostFound_Entry_ScrActivity::class.java)
            newEntryLauncher.launch(intent)
        }

        // Διαγραφή στοιχείου με long press
        listView.setOnItemLongClickListener { _, _, position, _ ->
            val item = lostItems[position]

            AlertDialog.Builder(this)
                .setTitle("Delete Entry")
                .setMessage("Are you sure you want to delete this item?\n$item")
                .setPositiveButton("Delete") { _, _ ->
                    lostItems.removeAt(position)
                    displayList()
                    LostFoundStorage.saveList(this, lostItems)
                    Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("Cancel", null)
                .show()

            true
        }
    }

    private fun displayList() {
        adapter = SimpleAdapter(
            this,
            lostItems.map {
                mapOf(
                    "title" to it.split(" - ").firstOrNull().orEmpty(),
                    "subtitle" to it.substringAfter(" - ", "")
                )
            },
            android.R.layout.simple_list_item_2,
            arrayOf("title", "subtitle"),
            intArrayOf(android.R.id.text1, android.R.id.text2)
        )
        listView.adapter = adapter
    }
}
