package com.example.myapplication


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsCompat

class TenapMngrScrActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.tenap_mngr_scr)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.tenap_manager)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val removetnt = findViewById<Button>(R.id.remove_tnt)
        removetnt.setOnClickListener {
            val intent = Intent(this, TntListScrActivity::class.java)
            startActivity(intent)

        }

        val addtnt = findViewById<Button>(R.id.add_tnt)
        addtnt.setOnClickListener {
            val intent = Intent(this, NewTntAddScrActivity::class.java)
            startActivity(intent)

        }
    }
}