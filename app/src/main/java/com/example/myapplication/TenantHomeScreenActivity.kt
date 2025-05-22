package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import android.widget.Button
import android.widget.ImageButton


class TenantHomeScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tenant_home_scr)

        val lostFoundBtn = findViewById<Button>(R.id.LostFound)
        lostFoundBtn.setOnClickListener {
            val intent = Intent(this, Tnt_LostFound_ScrActivity::class.java)
            startActivity(intent)
        }

        val paymentBtn = findViewById<ImageButton>(R.id.imageButton11)
        paymentBtn.setOnClickListener {
            val intent = Intent(this, Tnt_Payment_ScrActivity::class.java)
            startActivity(intent)
        }

        val pollBtn = findViewById<ImageButton>(R.id.PollButton)
        pollBtn.setOnClickListener {
            val intent = Intent(this, Tenant_Poll_ScrActivity::class.java)
            startActivity(intent)
        }


    }
}