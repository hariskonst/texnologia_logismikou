package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.yourapp.manager.ManageMaint
import com.yourapp.model.Maintenance
import com.yourapp.ui.MaintenanceAdapter

class Maint_ScrActivity : AppCompatActivity() {
    private lateinit var adapter: MaintenanceAdapter
    private val items = mutableListOf<Maintenance>()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.maint_scr)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.maintenancescr)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // σπείρισε defaults αν χρειάζεται
        ManageMaint.seedIfNeeded(this)

        // κουμπί Add
        findViewById<Button>(R.id.newMaintenance).setOnClickListener {
            startActivity(Intent(this, Add_Maint_ScrActivity::class.java))
        }

        // adapter + delete callback
        adapter = MaintenanceAdapter(this, items) { m ->
            ManageMaint.deleteMaintenance(this, m)
            items.remove(m)
            adapter.notifyDataSetChanged()
            Toast.makeText(this, "Maintenance διαγράφηκε", Toast.LENGTH_SHORT).show()
        }

        // bind ListView
        findViewById<ListView>(R.id.listViewMaintenance).adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        // φρεσκάρισμα κάθε φορά που επιστρέφεις από Add screen
        items.clear()
        items.addAll(ManageMaint.queryMaintenance(this))
        adapter.notifyDataSetChanged()
    }
}