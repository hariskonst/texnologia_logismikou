package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Com_Charges_ScrActivity : AppCompatActivity(){

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.com_charges_scr)


        val publicCommonChargesButtonNext = findViewById<Button>(R.id.public_comm_charges_BTN_next)
        publicCommonChargesButtonNext.setOnClickListener {
            val intent = Intent(this, QuickPrevScrActivity::class.java)
            startActivity(intent)

        }


    }


}