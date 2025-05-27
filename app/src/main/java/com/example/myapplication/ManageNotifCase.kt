import android.content.Context

class ManageNotifCase(private val context: Context) {
    private var notifications = NotificationStorage.loadNotifications(context).toMutableList()

    fun loadNotificationList(): List<Notification> {
        return notifications
    }

    fun createNotification(notification: Notification) {
        notifications.add(notification)
        NotificationStorage.saveNotifications(context, notifications)
    }

    fun deleteNotification(notification: Notification) {
        notifications.remove(notification)
        NotificationStorage.saveNotifications(context, notifications)
    }
}
