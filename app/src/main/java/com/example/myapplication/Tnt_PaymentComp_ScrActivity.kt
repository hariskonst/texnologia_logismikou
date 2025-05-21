package com.example.myapplication

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

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.paymentLayout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        SubmitButton()
    }

    fun FillForm(): Boolean {
        val name = findViewById<EditText>(R.id.nameOnCard).text.toString()
        val number = findViewById<EditText>(R.id.cardNumber).text.toString()
        val expiry = findViewById<EditText>(R.id.expiryDate).text.toString()
        val cvc = findViewById<EditText>(R.id.cardCVC).text.toString()
        return name.isNotEmpty() && number.isNotEmpty() && expiry.isNotEmpty() && cvc.isNotEmpty()
    }

    fun SubmitButton() {
        val submit = findViewById<Button>(R.id.submitPaymentButton)
        submit.setOnClickListener {
            if (CheckForm()) {
                AddData()
                ConfMessage()
                finish()
            } else {
                ErrorMessage()
            }
        }
    }

    fun CheckForm(): Boolean {
        return FillForm()
    }

    fun AddData() {
        // Θα μπορούσε να αποθηκεύσει στοιχεία
    }

    fun ConfMessage() {
        Toast.makeText(this, "Payment submitted successfully!", Toast.LENGTH_SHORT).show()
    }

    fun ErrorMessage() {
        Toast.makeText(this, "Please fill all fields!", Toast.LENGTH_SHORT).show()
    }
}
