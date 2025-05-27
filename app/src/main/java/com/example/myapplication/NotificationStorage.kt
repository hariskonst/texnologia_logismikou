import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object NotificationStorage {
    private const val PREF_NAME = "notifications_prefs"
    private const val KEY_NOTIFICATIONS = "notifications_key"

    private val gson = Gson()

    fun saveNotifications(context: Context, notifications: List<Notification>) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val json = gson.toJson(notifications)
        sharedPreferences.edit().putString(KEY_NOTIFICATIONS, json).apply()
    }

    fun loadNotifications(context: Context): List<Notification> {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val json = sharedPreferences.getString(KEY_NOTIFICATIONS, null)
        if (json != null) {
            val type = object : TypeToken<List<Notification>>() {}.type
            return gson.fromJson(json, type)
        }
        return emptyList()
    }
}
