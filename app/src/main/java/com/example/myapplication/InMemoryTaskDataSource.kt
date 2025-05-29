package com.example.myapplication.data

import com.example.myapplication.model.TaskDetail
import android.content.Context


class InMemoryTaskDataSource(context: Context) : TaskDataSource {

    private val prefs = context.getSharedPreferences("tasks_prefs", Context.MODE_PRIVATE)
    private val KEY_FAVORITES = "favorites_set"

    // 1. Βασική λίστα tasks
    private val allTasks = listOf(
        "Report Preparation",
        "Database Update",
        "Log Review",
        "Email Sending",
        "Backup Creation",
        "Detailed KPI Report",
        "Error Review",
        "Brainstorming Session"
    )

    // 2. Favorites σε in-memory set
    private val favorites: MutableSet<String>
        get() = prefs.getStringSet(KEY_FAVORITES, emptySet())!!.toMutableSet()

    override fun getAllTasks(): List<String> = allTasks

    override fun searchTasks(query: String): List<String> =
        allTasks.filter { it.contains(query, ignoreCase = true) }

    override fun getTaskDetail(name: String): TaskDetail {
        // Εδώ «σπρώχνουμε» την κατάλληλη περιγραφή
        val description = when (name) {
            "Προετοιμασία αναφοράς" ->
                "Συλλογή και ανάλυση όλων των απαραίτητων δεδομένων, σύνταξη και μορφοποίηση του τελικού εγγράφου αναφοράς."

            "Ενημέρωση βάσης δεδομένων" ->
                "Εισαγωγή ή τροποποίηση εγγραφών στη βάση δεδομένων ώστε να αντικατοπτρίζουν τις πιο πρόσφατες πληροφορίες."

            "Έλεγχος logs" ->
                "Ανασκόπηση των αρχείων καταγραφής για εντοπισμό σφαλμάτων, προειδοποιήσεων και ανωμαλιών στη λειτουργία του συστήματος."

            "Αποστολή email" ->
                "Σύνταξη και αποστολή ενημερωτικών ή ειδοποιητικών μηνυμάτων μέσω ηλεκτρονικού ταχυδρομείου σε συναδέλφους ή πελάτες."

            "Δημιουργία back-up" ->
                "Δημιουργία αντιγράφων ασφαλείας των κρίσιμων αρχείων και βάσεων δεδομένων για προστασία έναντι απωλειών."

            "Αναλυτική έκθεση KPI" ->
                "Συλλογή και επεξεργασία βασικών δεικτών απόδοσης (KPIs) για αξιολόγηση της αποτελεσματικότητας των στόχων."

            "Επισκόπηση σφαλμάτων" ->
                "Καταγραφή και ανάλυση των σφαλμάτων που έχουν παρουσιαστεί, με στόχο την πρόταση διορθωτικών ενεργειών."

            "Συνεδρία brainstorming" ->
                "Οργάνωση και διεξαγωγή ομαδικής δημιουργικής συνεδρίασης για ανάπτυξη νέων ιδεών και λύσεων."

            else ->
                "Δεν υπάρχουν διαθέσιμες λεπτομέρειες για αυτή την επιλογή."
        }
        return TaskDetail(name, description)
    }

    override fun addFavorite(name: String): Boolean {
        val updated = favorites
        val added = updated.add(name)
        if (added) saveFavorites(updated)
        return added
    }

    override fun getFavorites(): List<String> = favorites.toList()


    private fun saveFavorites(set: Set<String>) {
        prefs.edit().putStringSet(KEY_FAVORITES, set).apply()
    }

    override fun removeFavorite(name: String): Boolean {
        val updated = favorites
        val removed = updated.remove(name)
        if (removed) saveFavorites(updated)
        return removed
    }

}
