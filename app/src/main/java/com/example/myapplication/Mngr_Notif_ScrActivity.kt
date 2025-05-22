package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Mngr_Notif_ScrActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.mngr_notif_scr)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.managernotif)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val newnotif = findViewById<Button>(R.id.NewNotifButton)
        newnotif.setOnClickListener {
            val intent = Intent(this, Notif_Create_ScrActivity::class.java) // na valw thn epomenh othoni
            startActivity(intent)

        }

        val pollButton = findViewById<ImageButton>(R.id.PollsButton)
        pollButton.setOnClickListener {
            val intent = Intent(this, Mngr_Poll_ScrActivity::class.java)
            startActivity(intent)
        }

        val paymentButton = findViewById<ImageButton>(R.id.PaymentButton)
        paymentButton.setOnClickListener {
            val intent = Intent(this, Tnt_Payment_ScrActivity::class.java)
            startActivity(intent)
        }

        val billButton= findViewById<ImageButton>(R.id.FileButton)
        billButton.setOnClickListener {
            val intent = Intent(this, ReceiptManagerScrActivity::class.java)
            startActivity(intent)
        }

        val notifButton = findViewById<ImageButton>(R.id.notifButton)
        notifButton.setOnClickListener {
            val intent = Intent(this, Mngr_Notif_ScrActivity::class.java)
            startActivity(intent)
        }
    }
}