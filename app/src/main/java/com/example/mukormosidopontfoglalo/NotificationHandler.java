package com.example.mukormosidopontfoglalo;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class NotificationHandler {
    private NotificationManager manager;
    private Context context;
    private static final String CHANNEL_ID = "1";
    private static final int NOTIFICATION_ID = 0;

    public NotificationHandler(Context context) {
        this.context = context;
        manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        createChannel();
    }

    private void createChannel() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O)
            return;
        NotificationChannel channel = new NotificationChannel(
                CHANNEL_ID,
                "Idopont foglalo notification",
                NotificationManager.IMPORTANCE_DEFAULT
        );

        channel.enableVibration(true);
        channel.setDescription("fasz");
        manager.createNotificationChannel(channel);
    }

    public void send(String message) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentTitle("Műkörmös időpontfoglaló")
                .setContentText(message)
                .setSmallIcon(R.drawable.ic_shopping_cart);

        this.manager.notify(NOTIFICATION_ID, builder.build());
    }
}
