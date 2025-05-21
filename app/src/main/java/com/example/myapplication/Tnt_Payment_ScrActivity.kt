package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Tnt_Payment_ScrActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private val payments = listOf(
        "1 April 2025 - Common Charges - 100$ - Pending",
        "1 March 2025 - Common Charges - 100$ - Pending",
        "1 February 2025 - Common Charges - 100$ - Done",
        "1 January 2025 - Common Charges - 45$ - Done",
        "1 December 2024 - Common Charges - 65$ - Done",
        "1 November 2024 - Common Charges - 120$ - Done"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_scr)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.paymentLayout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        display()
        SelectPayment()
    }

    fun display() {
        listView = findViewById(R.id.paymentListView)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, payments)
        listView.adapter = adapter
    }

    fun SelectPayment() {
        listView.setOnItemClickListener { _, _, position, _ ->
            if (payments[position].contains("Pending")) {
                val intent = Intent(this, Tnt_PaymentComp_ScrActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
