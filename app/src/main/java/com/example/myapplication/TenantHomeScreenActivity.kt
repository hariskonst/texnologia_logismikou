package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageButton


class TenantHomeScreenActivity : AppCompatActivity() {
    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tenant_home_scr)

        val lostFoundBtn = findViewById<Button>(R.id.LostFound)
        lostFoundBtn.setOnClickListener {
            val intent = Intent(this, Tnt_LostFound_ScrActivity::class.java)
            startActivity(intent)
        }

        val RsvtBtn = findViewById<Button>(R.id.buttonReservations)
        RsvtBtn.setOnClickListener {
            val intent = Intent(this, Rsvt_create_scrActivity::class.java)
            startActivity(intent)
        }

        val reserve = findViewById<Button>(R.id.LostFound)
        reserve.setOnClickListener {
            val intent = Intent(this, Tnt_LostFound_ScrActivity::class.java)
            startActivity(intent)
        }

        val pollBtn = findViewById<ImageButton>(R.id.PollButton)
        pollBtn.setOnClickListener {
            val intent = Intent(this, Tenant_Poll_ScrActivity::class.java)
            startActivity(intent)
        }

        val paymentButton = findViewById<ImageButton>(R.id.imageButton11)
        paymentButton.setOnClickListener {
            val intent = Intent(this, Tnt_Payment_ScrActivity::class.java)
            startActivity(intent)
        }
    }
}