package com.example.myapplication

import ManageNotifCase
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SimpleAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Οθόνη που εμφανίζει τις ειδοποιήσεις του tenant σε two-line λίστα.
 * Ο τίτλος κάθε item είναι το θέμα της καταγγελίας (subject).
 * Σε tap ανοίγει διάλογος με το πλήρες κείμενο της απάντησης,
 * σε long-tap διαγράφεται η ειδοποίηση.
 */
class TenantNotifScrActivity : AppCompatActivity() {

    private lateinit var manageNotifCase: ManageNotifCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.tenant_notif_scr)

        // Edge-to-edge insets handling
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.tenant_notif_scr)) { v, insets ->
            val sys = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(sys.left, sys.top, sys.right, sys.bottom)
            insets
        }

        // Initialize manager data
        ComplaintManager.init(this)

        // Two-line data list (mutable)
        val mapData = ComplaintManager.getAllTenantNotificationMaps().toMutableList()
        // Full-text data list (mutable)
        val fullData = ComplaintManager.getAllTenantNotifications().toMutableList()

        // Adapter for two-line display
        val adapter = SimpleAdapter(
            this,
            mapData,
            android.R.layout.simple_list_item_2,
            arrayOf("title", "preview"),
            intArrayOf(android.R.id.text1, android.R.id.text2)
        )

        // Bind adapter
        var listView = findViewById<ListView>(R.id.lvTenantNotifs)
        listView.adapter = adapter

        // Tap: show full message dialog
        listView.setOnItemClickListener { _, _, pos, _ ->
            val title = mapData[pos]["title"] ?: ""
            val message = fullData.getOrNull(pos) ?: "— δεν βρέθηκε απάντηση —"
            AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show()
        }

        // Long-tap: remove notification
        listView.setOnItemLongClickListener { _, _, pos, _ ->
            // Remove from manager
            ComplaintManager.removeTenantNotificationMap(pos)
            ComplaintManager.removeTenantNotification(pos)
            // Update local lists
            mapData.removeAt(pos)
            fullData.removeAt(pos)
            // Notify adapter
            adapter.notifyDataSetChanged()
            true
        }

        // Clear button action
        findViewById<Button>(R.id.btnClearTenantData).setOnClickListener {
            ComplaintManager.clearAllData()
            mapData.clear()
            fullData.clear()
            adapter.notifyDataSetChanged()

            AlertDialog.Builder(this)
                .setTitle("Ολοκλήρωση")
                .setMessage("Όλες οι ειδοποιήσεις διαγράφηκαν.")
                .setPositiveButton("OK", null)
                .show()
        }

        //Notifications
        manageNotifCase = ManageNotifCase(this)
        listView = findViewById(R.id.Listview1)

        val notifications = manageNotifCase.loadNotificationList()
        val titles = notifications.map { "${it.title}: ${it.message}" }
        val adapter1 = ArrayAdapter(this, android.R.layout.simple_list_item_1, titles)
        listView.adapter = adapter1

    }
}
