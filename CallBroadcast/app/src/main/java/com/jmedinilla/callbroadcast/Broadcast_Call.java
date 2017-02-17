package com.jmedinilla.callbroadcast;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.telephony.TelephonyManager;

public class Broadcast_Call extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        final int INCOMING_CALL = 1;

        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String state = bundle.getString(TelephonyManager.EXTRA_STATE);
            if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                String number = bundle.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);

                Intent newIntent = new Intent(context, Activity_Telephony.class);
                newIntent.putExtra("number", number);

                PendingIntent pendingIntent = PendingIntent.getActivity(context, INCOMING_CALL, newIntent, PendingIntent.FLAG_ONE_SHOT);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
                builder.setContentTitle(context.getApplicationContext().getPackageName());
                builder.setContentText("Número " + number + " llamando");
                builder.setSmallIcon(R.mipmap.ic_launcher);
                //Parámetros extra
                builder.setDefaults(Notification.DEFAULT_VIBRATE);
                builder.setDefaults(Notification.DEFAULT_LIGHTS);
                //Objeto PendingIntent
                builder.setContentIntent(pendingIntent);
                //Añadir notificación
                NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(INCOMING_CALL, builder.build());
            }
        }
    }
}
