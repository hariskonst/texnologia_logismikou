import android.content.Context

class ManageNotifCase(private val context: Context) {

    fun loadNotificationList(): List<Notification> {
        return NotificationStorage.loadNotifications(context)
    }

    fun createNotification(notification: Notification) {
        val notifications = NotificationStorage.loadNotifications(context).toMutableList()
        notifications.add(notification)
        NotificationStorage.saveNotifications(context, notifications)
    }

    fun deleteNotification(notification: Notification) {
        val notifications = NotificationStorage.loadNotifications(context).toMutableList()
        notifications.remove(notification)
        NotificationStorage.saveNotifications(context, notifications)
    }
}
