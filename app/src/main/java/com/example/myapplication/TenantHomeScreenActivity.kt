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

        // Settings button
        //findViewById<ImageButton>(R.id.settingsButton).setOnClickListener {
            //startActivity(Intent(this, SettingsActivity::class.java))
        //}

        // Top pills
        //findViewById<MaterialButton>(R.id.btnReservations).setOnClickListener {
            //startActivity(Intent(this, ReservationsActivity::class.java))
       // }
        //findViewById<MaterialButton>(R.id.btnLostFound).setOnClickListener {
            //startActivity(Intent(this, LostFoundActivity::class.java))
        //}

        // Maintenances card
        //findViewById<CardView>(R.id.cardMaintenances).setOnClickListener {
           // startActivity(Intent(this, MaintenanceListActivity::class.java))
        //}

        // Latest results card
        //findViewById<CardView>(R.id.cardLatestResults).setOnClickListener {
            //startActivity(Intent(this, ResultsActivity::class.java))
        //}

        // File a complaint
        findViewById<MaterialButton>(R.id.btnFileComplaint).setOnClickListener {
            startActivity(Intent(this, ComplaintActivity::class.java))
        }

        // Chat icon
        //findViewById<ImageButton>(R.id.btnChat).setOnClickListener {
            //startActivity(Intent(this, ChatActivity::class.java))
        //}

        // Bottom navigation
        //findViewById<ImageButton>(R.id.nav_stats).setOnClickListener {
            //startActivity(Intent(this, StatsActivity::class.java))
        //}
        //findViewById<ImageButton>(R.id.nav_payments).setOnClickListener {
           // startActivity(Intent(this, PaymentsActivity::class.java))
        //}
        //findViewById<ImageButton>(R.id.nav_upload).setOnClickListener {
           // startActivity(Intent(this, UploadReceiptActivity::class.java))
        //
        //findViewById<ImageButton>(R.id.nav_notifications).setOnClickListener {
            //startActivity(Intent(this, NotificationsActivity::class.java))
        //}

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
    }
}