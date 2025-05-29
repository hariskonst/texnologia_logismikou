package com.example.myapplication

import ManageNotifCase
import Notification
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ReceiptManagerScrActivity : AppCompatActivity() {

    private var selectedFileUri: Uri? = null
    private lateinit var receiptsListView: ListView
    private lateinit var adapter: ArrayAdapter<String>
    // Δήλωση της λίστας receipts
    private lateinit var receipts: MutableList<String>

    private val filePickerLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            selectedFileUri = it
            Toast.makeText(this, "File added!", Toast.LENGTH_SHORT).show()
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.receipt_manager_scr)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        receipts = Receipt.loadList(this)




        fun deleteReceipt(position: Int) {
            val receiptToDelete = receipts[position]

            // Διαγραφή από τη λίστα
            receipts.removeAt(position)

            // Αποθήκευση της ενημερωμένης λίστας
            Receipt.saveList(this, receipts)

            // Ενημέρωση του ListView
            adapter.notifyDataSetChanged()

            Toast.makeText(this, "Receipt deleted: $receiptToDelete", Toast.LENGTH_SHORT).show()
        }

        fun setupListView() {
            // Σύνδεση με το ListView από το XML
            receiptsListView = findViewById(R.id.ReceiptsListView)

            // Δημιουργία adapter για το ListView
            adapter = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                receipts
            )

            // Σύνδεση του adapter με το ListView
            receiptsListView.adapter = adapter

            // Προσθήκη click listener για τα items της λίστας
            receiptsListView.setOnItemClickListener { _, _, position, _ ->
                val selectedReceipt = receipts[position]
                Toast.makeText(this, "Selected: $selectedReceipt", Toast.LENGTH_SHORT).show()


            }

            // Προσθήκη long click listener για διαγραφή
            receiptsListView.setOnItemLongClickListener { _, _, position, _ ->
                deleteReceipt(position)
                true
            }
        }
        setupListView()
        fun updateListView()
        {
            // Αποθήκευση της ενημερωμένης λίστας
            Receipt.saveList(this, receipts)

            // Ενημέρωση του ListView
            adapter.notifyDataSetChanged()
        }


        // Ανάκτηση αναφορών στα πεδία και το κουμπί
        val serviceDescField = findViewById<EditText>(R.id.serviceDesc)
        val serviceAmountField = findViewById<EditText>(R.id.serviceAmount)
        val chooseFileButton = findViewById<Button>(R.id.chooseFileBtn)
        //choose file button!!



        chooseFileButton.setOnClickListener {
            filePickerLauncher.launch("*/*")
        }


        val publicCommonChargesButton = findViewById<Button>(R.id.public_comm_charges_BTN)
        publicCommonChargesButton.setOnClickListener {

            val desc = serviceDescField.text.toString().trim()
            val amount = serviceAmountField.text.toString().trim()
            val fileUri = selectedFileUri


            if (desc.isNotEmpty() && amount.isNotEmpty() && fileUri != null) {

                val newReceipt = "$desc - $amount"
                receipts.add(newReceipt)
                Receipt.saveList(this, receipts)

                serviceDescField.text.clear()
                serviceAmountField.text.clear()
                selectedFileUri = null

                val manageNotifCase = ManageNotifCase(this)
                val title = "New Receipt added"
                val message = "New Common charges receipt uploaded by manager"
                val notification = Notification(title = title, message = message)
                manageNotifCase.createNotification(notification)

                Toast.makeText(this, "Receipt added successfully!", Toast.LENGTH_SHORT).show()
                updateListView()


            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }


        }

        val nextScreen = findViewById<Button>(R.id.nextScreen)
        nextScreen.setOnClickListener {
            val intent = Intent(this, Com_Charges_ScrActivity::class.java)
            startActivity(intent)
        }



    }
}