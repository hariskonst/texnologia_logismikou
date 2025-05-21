package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Tnt_Payment_ScrActivity : AppCompatActivity() {

    private lateinit var paymentList: MutableList<String>
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var listView: ListView

    private val paymentResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val newPayment = result.data?.getStringExtra("newPayment")
            newPayment?.let {
                paymentList.add(it)
                adapter.notifyDataSetChanged()
                PaymentStorage.saveList(this, paymentList)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_scr)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.paymentLayout)) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        paymentList = PaymentStorage.loadList(this)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, paymentList)
        listView = findViewById(R.id.paymentListView)
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val selected = paymentList[position]
            if (selected.contains("Pending")) {
                val intent = Intent(this, Tnt_PaymentComp_ScrActivity::class.java)
                paymentResultLauncher.launch(intent)
            }
        }
    }
}
