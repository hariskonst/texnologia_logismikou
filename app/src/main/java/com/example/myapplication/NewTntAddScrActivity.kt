package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.yourapp.manager.AddTntManage
import com.yourapp.model.Tenant

class NewTntAddScrActivity : AppCompatActivity(){

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.new_tnt_add_scr)

        val etName = findViewById<EditText>(R.id.etTenantName)
        findViewById<Button>(R.id.btnConfirm).setOnClickListener {
            val name = etName.text.toString().trim()
            if (name.isEmpty()) {
                etName.error = "Απαιτείται όνομα"
                return@setOnClickListener
            }

            // Δημιούργησε νέο ID
            val current = AddTntManage.queryTenant(this)
            val newId = (current.maxByOrNull { it.tenantId }?.tenantId ?: 0) + 1
            val t = Tenant(newId, name)

            AddTntManage.addTenant(this, t)
            Toast.makeText(this, "Tenant προστέθηκε", Toast.LENGTH_SHORT).show()
            finish()
        }

    }
}