package com.example.myapplication

import android.content.Context
import android.content.SharedPreferences
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONException

/**
 * Κλάση δεδομένων που αναπαριστά μία καταγγελία με θέμα και κείμενο.
 */
data class Complaint(
    val subject: String,
    val body: String
)

/**
 * Singleton για τη διαχείριση καταγγελιών, απαντήσεων και ειδοποιήσεων tenant.
 * Αποθηκεύει δεδομένα σε SharedPreferences σε μορφή JSON.
 */
object ComplaintManager {
    private const val PREFS_NAME                    = "complaints_prefs"
    private const val KEY_COMPLAINTS                = "key_complaints"
    private const val KEY_REPLIES                   = "key_replies"
    private const val KEY_TENANT_NOTIFICATIONS      = "key_tenant_notifications"
    private const val KEY_TENANT_NOTIFICATION_MAPS  = "key_tenant_notification_maps"

    private lateinit var prefs: SharedPreferences

    // Δομές δεδομένων σε μνήμη
    private val complaints             = mutableListOf<Complaint>()
    private val repliesMap             = mutableMapOf<Int, String>()
    private val tenantNotifications    = mutableListOf<String>()
    private val tenantNotificationMaps = mutableListOf<Map<String, String>>()

    /**
     * Αρχικοποίηση manager και φόρτωση δεδομένων από SharedPreferences.
     * Κλήση μία φορά στην Application ή την πρώτη Activity.
     */
    fun init(context: Context) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        loadComplaints()
        loadReplies()
        loadTenantNotifications()
        loadTenantNotificationMaps()
    }

    // --- Διαχείριση καταγγελιών ---

    /**
     * Φόρτωση καταγγελιών από SharedPreferences.
     */
    private fun loadComplaints() {
        prefs.getString(KEY_COMPLAINTS, null)?.let { json ->
            val arr = try {
                JSONArray(json)
            } catch (e: JSONException) {
                // αν δεν είναι JSON array, επανεγγράψτε έναν κενό πίνακα
                prefs.edit().remove(KEY_COMPLAINTS).apply()
                JSONArray()
            }
            complaints.clear()
            for (i in 0 until arr.length()) {
                val obj = arr.optJSONObject(i) ?: continue
                val subject = obj.optString("subject", "")
                val body    = obj.optString("body", "")
                complaints += Complaint(subject, body)
            }
        }
    }


    /**
     * Αποθήκευση καταγγελιών σε SharedPreferences.
     */
    private fun saveComplaints() {
        val arr = JSONArray().apply {
            complaints.forEach { c ->
                put(JSONObject().apply {
                    put("subject", c.subject)
                    put("body",    c.body)
                })
            }
        }
        prefs.edit().putString(KEY_COMPLAINTS, arr.toString()).apply()
    }

    /**
     * Προσθήκη νέας καταγγελίας με θέμα και κείμενο.
     */
    fun addComplaint(subject: String, body: String) {
        complaints += Complaint(subject, body)
        saveComplaints()
    }

    /**
     * Επιστρέφει αντίγραφο όλων των καταγγελιών.
     */
    fun getAllComplaints(): List<Complaint> = complaints.toList()

    // --- Διαχείριση απαντήσεων ---

    /**
     * Φόρτωση απαντήσεων από SharedPreferences.
     */
    private fun loadReplies() {
        prefs.getString(KEY_REPLIES, null)?.let { json ->
            val arr = JSONArray(json)
            repliesMap.clear()
            for (i in 0 until arr.length()) {
                val obj = arr.getJSONObject(i)
                repliesMap[obj.getInt("index")] = obj.getString("reply")
            }
        }
    }

    /**
     * Αποθήκευση απαντήσεων σε SharedPreferences.
     */
    private fun saveReplies() {
        val arr = JSONArray().apply {
            repliesMap.forEach { (index, reply) ->
                put(JSONObject().apply {
                    put("index", index)
                    put("reply", reply)
                })
            }
        }
        prefs.edit().putString(KEY_REPLIES, arr.toString()).apply()
    }

    /**
     * Προσθήκη απάντησης για την καταγγελία στο [index].
     * Δημιουργεί επίσης ειδοποιήσεις tenant για την απάντηση.
     */
    fun addReply(index: Int, text: String) {
        repliesMap[index] = text
        saveReplies()

        // Δημιουργία εγγραφών ειδοποίησης tenant
        val title    = "Complaint Answer"
        val preview  = text.take(30).trimEnd().let { if (text.length > 30) "$it…" else it }
        val subject  = complaints.getOrNull(index)?.subject ?: ""
        val fullText = "Απάντηση στο \"$subject\": $text"

        addTenantNotificationMap(mapOf(
            "title"   to title,
            "preview" to preview
        ))
        addTenantNotification(fullText)
    }

    /**
     * Επιστρέφει την απάντηση για την καταγγελία στο [index], ή null αν δεν υπάρχει.
     */
    fun getReplyFor(index: Int): String? = repliesMap[index]

    /**
     * Επιστρέφει όλες τις απαντήσεις ως map <index, reply>.
     */
    fun getAllReplies(): Map<Int, String> = repliesMap.toMap()

    // --- Ειδοποιήσεις tenant (πλήρες κείμενο) ---

    /**
     * Φόρτωση ειδοποιήσεων πλήρους κειμένου από SharedPreferences.
     */
    private fun loadTenantNotifications() {
        prefs.getString(KEY_TENANT_NOTIFICATIONS, null)?.let { json ->
            val arr = JSONArray(json)
            tenantNotifications.clear()
            for (i in 0 until arr.length()) {
                tenantNotifications += arr.getString(i)
            }
        }
    }

    /**
     * Αποθήκευση ειδοποιήσεων πλήρους κειμένου σε SharedPreferences.
     */
    private fun saveTenantNotifications() {
        val arr = JSONArray().apply {
            tenantNotifications.forEach { put(it) }
        }
        prefs.edit().putString(KEY_TENANT_NOTIFICATIONS, arr.toString()).apply()
    }

    /**
     * Προσθήκη εγγραφής πλήρους κειμένου ειδοποίησης για tenant.
     */
    private fun addTenantNotification(message: String) {
        tenantNotifications += message
        saveTenantNotifications()
    }

    /**
     * Επιστρέφει όλες τις ειδοποιήσεις πλήρους κειμένου.
     */
    fun getAllTenantNotifications(): List<String> = tenantNotifications.toList()

    // --- Ειδοποιήσεις tenant (δύο-γραμμή map) ---

    /**
     * Φόρτωση δύο-γραμμων ειδοποιήσεων από SharedPreferences.
     */
    private fun loadTenantNotificationMaps() {
        prefs.getString(KEY_TENANT_NOTIFICATION_MAPS, null)?.let { json ->
            val arr = JSONArray(json)
            tenantNotificationMaps.clear()
            for (i in 0 until arr.length()) {
                val obj = arr.getJSONObject(i)
                val title   = obj.optString("title", "")
                val preview = obj.optString("preview", "")
                tenantNotificationMaps += mapOf(
                    "title"   to title,
                    "preview" to preview
                )
            }
        }
    }

    /**
     * Αποθήκευση δύο-γραμμων ειδοποιήσεων σε SharedPreferences.
     */
    private fun saveTenantNotificationMaps() {
        val arr = JSONArray().apply {
            tenantNotificationMaps.forEach { map ->
                put(JSONObject().apply {
                    map["title"]?.let   { put("title", it) }
                    map["preview"]?.let { put("preview", it) }
                })
            }
        }
        prefs.edit().putString(KEY_TENANT_NOTIFICATION_MAPS, arr.toString()).apply()
    }

    /**
     * Προσθήκη δύο-γραμμου εγγραφής ειδοποίησης (map με "title" και "preview").
     */
    fun addTenantNotificationMap(item: Map<String, String>) {
        tenantNotificationMaps += item
        saveTenantNotificationMaps()
    }

    /**
     * Επιστρέφει όλες τις ειδοποιήσεις tenant για δύο-γραμμη εμφάνιση.
     */
    fun getAllTenantNotificationMaps(): List<Map<String, String>> = tenantNotificationMaps.toList()

    fun removeTenantNotificationMap(i: Int) {
        if (i in tenantNotificationMaps.indices) {
            tenantNotificationMaps.removeAt(i)
            saveTenantNotificationMaps()
        }
    }

    /** Διαγράφει το full-text notification με index [i] και αποθηκεύει. */
    fun removeTenantNotification(i: Int) {
        if (i in tenantNotifications.indices) {
            tenantNotifications.removeAt(i)
            saveTenantNotifications()
        }
    }

    fun removeComplaint(index: Int) {
        if (index in complaints.indices) {
            complaints.removeAt(index)
            saveComplaints()
        }
    }

    fun clearAllData() {
        prefs.edit().clear().apply()

        // Καθάρισμα και των δομών στη μνήμη
        complaints.clear()
        repliesMap.clear()
        tenantNotifications.clear()
        tenantNotificationMaps.clear()
    }

}
