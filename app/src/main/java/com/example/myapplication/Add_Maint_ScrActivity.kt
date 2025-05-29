package com.example.myapplication

import ManageNotifCase
import Notification
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.yourapp.manager.ManageMaint
import com.yourapp.model.Maintenance

class Add_Maint_ScrActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.add_maint_scr)

        // 1) Ορθός τύπος View
        val etDesc = findViewById<EditText>(R.id.etMaintDesc)
        val btnConfirm = findViewById<Button>(R.id.addButton)

        btnConfirm.setOnClickListener {
            val desc = etDesc.text.toString().trim()
            if (desc.isEmpty()) {
                etDesc.error = "Απαιτείται περιγραφή"
                return@setOnClickListener
            }

            // υπολόγισε νέο ID (max+1)
            val current = ManageMaint.queryMaintenance(this)
            val newId = (current.maxByOrNull { it.id }?.id ?: 0) + 1
            val m = Maintenance(newId, desc)

            val manageNotifCase = ManageNotifCase(this)
            val title = "New maintenance"
            val message = etDesc.text.toString().trim()
            val notification = Notification(title = title, message = message)
            manageNotifCase.createNotification(notification)

            ManageMaint.addMaintenance(this, m)
            Toast.makeText(this, "Maintenance προστέθηκε", Toast.LENGTH_SHORT).show()
            finish()  // → θα φρεσκαριστεί στη onResume() της λίστας
        }
    }
}
