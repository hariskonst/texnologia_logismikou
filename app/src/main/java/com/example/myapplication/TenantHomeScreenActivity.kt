package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.google.android.material.button.MaterialButton
import androidx.appcompat.app.AppCompatActivity

/**
 * Tenant home screen with navigation to various features including complaints and notifications.
 */
class TenantHomeScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tenant_home_scr)

        // Lost & Found screen
        findViewById<Button>(R.id.LostFound).setOnClickListener {
            startActivity(Intent(this, Tnt_LostFound_ScrActivity::class.java))
        }

        // Reservations screen
        findViewById<Button>(R.id.buttonReservations).setOnClickListener {
            startActivity(Intent(this, Rsvt_create_scrActivity::class.java))
        }

        // Polls screen
        findViewById<ImageButton>(R.id.PollsButton).setOnClickListener {
            startActivity(Intent(this, Tenant_Poll_ScrActivity::class.java))
        }

        // Payments screen
        findViewById<ImageButton>(R.id.PaymentButton).setOnClickListener {
            startActivity(Intent(this, Tnt_Payment_ScrActivity::class.java))
        }

        // File a new complaint
        findViewById<MaterialButton>(R.id.FileButton).setOnClickListener {
            startActivity(Intent(this, ComplaintActivity::class.java))
        }

        // View tenant notifications
        findViewById<ImageButton>(R.id.notifButton).setOnClickListener {
            startActivity(Intent(this, TenantNotifScrActivity::class.java))
        }
    }
}
