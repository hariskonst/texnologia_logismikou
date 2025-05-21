package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Tnt_LostFound_ScrActivity : AppCompatActivity() {

    private lateinit var lostItemsList: MutableList<String>
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var listView: ListView

    // Εκκίνηση φόρμας και λήψη αποτελέσματος
    private val entryResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val newEntry = result.data?.getStringExtra("newLostItem")
            newEntry?.let {
                lostItemsList.add(it)
                adapter.notifyDataSetChanged()
                LostFoundStorage.saveList(this, lostItemsList) // Αποθήκευση!
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lost_found_scr)

        // Προσθήκη insets (π.χ. για status bar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.managerlostfound)) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Φόρτωση αποθηκευμένων δεδομένων
        lostItemsList = LostFoundStorage.loadList(this)

        // Σύνδεση adapter με ListView
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, lostItemsList)
        listView = findViewById(R.id.lostItemsListView)
        listView.adapter = adapter

        // Κουμπί για νέο αντικείμενο
        val newEntryButton = findViewById<Button>(R.id.newEntryButton)
        newEntryButton.setOnClickListener {
            val intent = Intent(this, Tnt_LostFound_Entry_ScrActivity::class.java)
            entryResultLauncher.launch(intent)
        }
    }
}




