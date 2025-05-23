package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class ManagerHomeScreenActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "WrongViewCast", "CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mngr_home_scr)

        val secondButton = findViewById<ImageButton>(R.id.UploadReceiptButton)
        secondButton.setOnClickListener {
            val intent = Intent(this, ReceiptManagerScrActivity::class.java)
            startActivity(intent)

        }

        val notifbutton = findViewById<ImageButton>(R.id.notifbutton)
        notifbutton.setOnClickListener {
            val intent = Intent(this, Mngr_Notif_ScrActivity::class.java)
            startActivity(intent)

        }

        val tenButton = findViewById<Button>(R.id.tenapBtn)
        tenButton.setOnClickListener {
            val intent = Intent(this, TenapMngrScrActivity::class.java)
            startActivity(intent)

        }

        val lostButton = findViewById<Button>(R.id.LostFound)
        lostButton.setOnClickListener {
            val intent = Intent(this, Tnt_LostFound_ScrActivity::class.java)
            startActivity(intent)

        }

        val paymentButton = findViewById<ImageButton>(R.id.imageButton11)
        paymentButton.setOnClickListener {
            val intent = Intent(this, Tnt_Payment_ScrActivity::class.java)
            startActivity(intent)
        }

        val pollButton = findViewById<ImageButton>(R.id.PollButton)
        pollButton.setOnClickListener {
            val intent = Intent(this, Mngr_Poll_ScrActivity::class.java)
            startActivity(intent)
        }

        val tenantHomeButton = findViewById<ImageButton>(R.id.settingsButton)
        tenantHomeButton.setOnClickListener {
            val intent = Intent(this, TenantHomeScreenActivity::class.java)
            startActivity(intent)
        }

        val gotomaintenance = findViewById<Button>(R.id.gotomaint)
        gotomaintenance.setOnClickListener {
            val intent = Intent(this, Maint_ScrActivity::class.java)
            startActivity(intent)
        }

        val marketplaceCard = findViewById<CardView>(R.id.marketplace_card)
        marketplaceCard.setOnClickListener {
            startActivity(Intent(this, MarketplaceActivity::class.java))
        }

        val tenantHomeButtonAlaMilt = findViewById<Button>(R.id.btnSwitchToTenant)
        tenantHomeButtonAlaMilt.setOnClickListener {
            val intent = Intent(this, TenantHomeScreenActivity::class.java)
            startActivity(intent)
        }
    }

}
