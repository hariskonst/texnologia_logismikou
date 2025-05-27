package com.example.myapplication

import ManageNotifCase
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Οθόνη διαχείρισης καταγγελιών από τον manager.
 * Δύο-γραμμη λίστα με θέμα και preview, tap για απάντηση, long-tap για διαγραφή.
 */
class Mngr_Notif_ScrActivity : AppCompatActivity() {

    private lateinit var manageNotifCase: ManageNotifCase
    private lateinit var listView1: ListView
    private lateinit var adapter1: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.mngr_notif_scr)

        // Edge-to-edge insets handling
        val root = findViewById<ConstraintLayout>(R.id.managernotif)
        ViewCompat.setOnApplyWindowInsetsListener(root) { v, insets ->
            val sys = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(sys.left, sys.top, sys.right, sys.bottom)
            insets
        }

        // Initialize manager
        ComplaintManager.init(this)

        // Load all complaints
        val all = ComplaintManager.getAllComplaints()

        // Prepare data for two-line list: subject as title, first 30 chars of body as preview
        val data = all.map { complaint ->
            val preview = if (complaint.body.length > 30)
                complaint.body.substring(0, 30).trimEnd() + "…"
            else complaint.body
            mapOf(
                "title" to complaint.subject,
                "preview" to preview
            )
        }.toMutableList()

        // Create SimpleAdapter
        val adapter = SimpleAdapter(
            this,
            data,
            android.R.layout.simple_list_item_2,
            arrayOf("title", "preview"),
            intArrayOf(android.R.id.text1, android.R.id.text2)
        )

        // Bind to ListView
        val listView = findViewById<ListView>(R.id.lvComplaints)
        listView.adapter = adapter

        // 1) Tap: show reply dialog
        listView.setOnItemClickListener { _, _, pos, _ ->
            val complaint = all[pos]
            val subject   = complaint.subject
            val body      = complaint.body

            // Inflate custom dialog view
            val dialogView = layoutInflater.inflate(
                R.layout.dialog_reply_complaint, null
            )
            dialogView.findViewById<TextView>(R.id.tvOriginalComplaint).text = body
            val etReply = dialogView.findViewById<EditText>(R.id.etReply)

            AlertDialog.Builder(this)
                .setTitle("Απάντηση σε ‘$subject’")
                .setView(dialogView)
                .setPositiveButton("Υποβολή") { _, _ ->
                    val replyText = etReply.text.toString().trim()
                    if (replyText.isNotEmpty()) {
                        ComplaintManager.addReply(pos, replyText)
                        Toast.makeText(this, "Απάντηση αποθηκεύτηκε.", Toast.LENGTH_SHORT).show()
                    }
                }
                .setNegativeButton("Άκυρο", null)
                .show()
        }

        // 2) Long-tap: delete complaint
        listView.setOnItemLongClickListener { _, _, pos, _ ->
            // Remove complaint
            ComplaintManager.removeComplaint(pos)
            // Refresh in-memory list
            val updated = ComplaintManager.getAllComplaints()
            data.clear()
            updated.forEach { comp ->
                val preview = if (comp.body.length > 30)
                    comp.body.substring(0, 30).trimEnd() + "…"
                else comp.body
                data.add(mapOf("title" to comp.subject, "preview" to preview))
            }
            adapter.notifyDataSetChanged()
            true
        }

        //New notification
        manageNotifCase = ManageNotifCase(this)
        listView1 = findViewById(R.id.Listview1)


        // Bottom navigation buttons
        findViewById<Button>(R.id.NewNotifButton).setOnClickListener {
            startActivity(Intent(this, Notif_Create_ScrActivity::class.java))
        }
        findViewById<ImageButton>(R.id.PollsButton).setOnClickListener {
            startActivity(Intent(this, Mngr_Poll_ScrActivity::class.java))
        }
        findViewById<ImageButton>(R.id.PaymentButton).setOnClickListener {
            startActivity(Intent(this, Tnt_Payment_ScrActivity::class.java))
        }
        findViewById<ImageButton>(R.id.FileButton).setOnClickListener {
            startActivity(Intent(this, ReceiptManagerScrActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        val notifications = manageNotifCase.loadNotificationList()
        val titles = notifications.map {
            val formattedTime = formatTimestamp(it.timestamp)
            "${it.title}: ${it.message}\n[$formattedTime]"
        }

        adapter1 = ArrayAdapter(this, android.R.layout.simple_list_item_1, titles)
        listView1.adapter = adapter1

        // Long press to delete
        listView1.setOnItemLongClickListener { _, _, position, _ ->
            val notifToDelete = notifications[position]
            manageNotifCase.deleteNotification(notifToDelete)

            // Ανανέωση λίστας
            val updated = manageNotifCase.loadNotificationList()
            val updatedTitles = updated.map { "${it.title}: ${it.message}" }
            adapter1.clear()
            adapter1.addAll(updatedTitles)
            adapter1.notifyDataSetChanged()

            true // επιστρέφει ότι έγινε το long press
        }
    }

    private fun formatTimestamp(timestamp: Long): String {
        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        return sdf.format(Date(timestamp))
    }
}