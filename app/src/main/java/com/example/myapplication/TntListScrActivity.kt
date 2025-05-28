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
import com.yourapp.manager.AddTntManage
import com.yourapp.model.Tenant
import com.yourapp.ui.TenantAdapter

class TntListScrActivity : AppCompatActivity(){

    private lateinit var adapter: TenantAdapter
    private val tenants = mutableListOf<Tenant>()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.tnt_list_scr)


        // Φόρτωσε από SharedPreferences
        tenants.addAll(AddTntManage.queryTenant(this))

        // 2) Αν είναι άδεια, δημιούργησε μερικά δείγματα
        if (tenants.isEmpty()) {
            val sample = listOf(
                Tenant(1, "Dimitris Efthimakis"),
                Tenant(2, "Evelina Seni"),
                Tenant(3, "Loukas-Alexios Xristidis"),
                Tenant(4, "Miltiadis-Aggelos Kourakos"),
                Tenant(5, "Charalampos Konstantakopoulos")
            )
            sample.forEach { AddTntManage.addTenant(this, it) }
            tenants.addAll(sample)
        }

        // Adapter με callback διαγραφής
        adapter = TenantAdapter(this, tenants) { tenant ->
            // 1) Διαγραφή
            AddTntManage.deleteTenant(this, tenant)
            tenants.remove(tenant)
            adapter.notifyDataSetChanged()
            Toast.makeText(this, "Tenant διαγράφηκε", Toast.LENGTH_SHORT).show()

            // 2) Πήγαινε στην οθόνη προσθήκης
            val intent = Intent(this, NewTntAddScrActivity::class.java)
            startActivity(intent)
        }

        // Σύνδεσε με ListView
        findViewById<ListView>(R.id.listViewTenants).adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        // κάθε φορά που “εμφανίζεται” ξανά η Activity, φρεσκάρουμε τα δεδομένα
        tenants.clear()
        tenants.addAll(AddTntManage.queryTenant(this))
        adapter.notifyDataSetChanged()
    }
}

