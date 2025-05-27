package com.example.myapplication

import ManageNotifCase
import Notification
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Notif_Create_ScrActivity : AppCompatActivity(){

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.notif_create_scr)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.notifcreate)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
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

        val titleInput: EditText = findViewById(R.id.nameInput)
        val messageInput: EditText = findViewById(R.id.descriptionInput)
        val createButton: Button = findViewById(R.id.createButton)

        val manageNotifCase = ManageNotifCase(this)

        createButton.setOnClickListener {
            val title = titleInput.text.toString()
            val message = messageInput.text.toString()
            val notification = Notification(title = title, message = message)
            manageNotifCase.createNotification(notification)
            finish()
        }

    }
}