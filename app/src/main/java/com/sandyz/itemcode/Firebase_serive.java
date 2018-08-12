package com.sandyz.itemcode;

import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import static android.content.ContentValues.TAG;

public class Firebase_serive extends FirebaseMessagingService {
    public Firebase_serive() {
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());

            Log.d(TAG, "Message data payload: " + remoteMessage.getData());

           String title = remoteMessage.getNotification().getTitle();
           String message = remoteMessage.getNotification().getBody();

            NotificationCompat.Builder notifi= (NotificationCompat.Builder) new NotificationCompat.Builder(this).setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.log2othum))
                    .setSmallIcon(R.mipmap.log2othum)
                    .setContentTitle(title)
                    .setContentText(message)
                    ;


    }

}
