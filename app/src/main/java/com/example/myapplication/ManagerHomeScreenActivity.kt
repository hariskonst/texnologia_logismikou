package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class ManagerHomeScreenActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.manager_home_scr)

        val secondButton = findViewById<ImageButton>(R.id.UploadReceiptButton)
        secondButton.setOnClickListener {
            val intent = Intent(this, ReceiptManagerScrActivity::class.java)
            startActivity(intent)

        }

        val tenButton = findViewById<Button>(R.id.tenapBtn)
        tenButton.setOnClickListener {
            val intent = Intent(this, TenapMngrScr::class.java)
            startActivity(intent)

        }
    }
}
