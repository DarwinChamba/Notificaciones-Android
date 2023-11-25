package app.agenda.agendanotas.ui.view.notification

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import app.agenda.agendanotas.data.util.Constants.Companion.DESCRIPTION
import app.agenda.agendanotas.data.util.Constants.Companion.MY_CHANNEL_ID
import app.agenda.agendanotas.data.util.Constants.Companion.TITLE
import app.agenda.agendanotas.data.util.Constants.Companion.requestCodeRandom
import app.agenda.agendanotas.ui.view.MainActivity


class MyNotification : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val title=intent?.getStringExtra(TITLE)?:"Sin titulo"
        val description=intent?.getStringExtra(DESCRIPTION)?:"Sin description"
        createNotification(context,title,description)

    }

    private fun createNotification(context: Context,title:String,description:String) {
        val intent=Intent(context,MainActivity::class.java).apply {
            flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        }
        val flag=if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0
        val pendingIntent=PendingIntent.getActivity(context,0,intent,flag)
        val notification = NotificationCompat.Builder(context, MY_CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_delete)
            .setContentText(description)
            .setContentTitle(title)
            .setStyle(NotificationCompat.BigTextStyle().bigText("mucho contenido"))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .build()
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(requestCodeRandom, notification)
    }
}