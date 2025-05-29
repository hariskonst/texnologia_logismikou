package com.example.myapplication

import ManageNotifCase
import Notification
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Tnt_PaymentComp_ScrActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_comp_scr)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.paymentLayout)) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val name = findViewById<EditText>(R.id.nameOnCard)
        val number = findViewById<EditText>(R.id.cardNumber)
        val expiry = findViewById<EditText>(R.id.expiryDate)
        val cvc = findViewById<EditText>(R.id.cardCVC)
        val submit = findViewById<Button>(R.id.submitPaymentButton)

        submit.setOnClickListener {
            if (name.text.isNotEmpty() && number.text.isNotEmpty() && expiry.text.isNotEmpty() && cvc.text.isNotEmpty()) {
                val paid = "${name.text} paid with card ****${number.text.takeLast(4)} - Done"
                val result = Intent().apply {
                    putExtra("newPayment", paid)
                }
                setResult(Activity.RESULT_OK, result)

                val manageNotifCase = ManageNotifCase(this)
                val title = "Payment Completed"
                val message = "Your payment was successful!"
                val notification = Notification(title = title, message = message)
                manageNotifCase.createNotification(notification)

                finish()
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
