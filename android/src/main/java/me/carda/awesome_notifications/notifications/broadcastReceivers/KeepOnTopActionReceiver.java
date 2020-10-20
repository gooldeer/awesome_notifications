package me.carda.awesome_notifications.notifications.broadcastReceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import me.carda.awesome_notifications.BroadcastSender;
import me.carda.awesome_notifications.notifications.NotificationBuilder;
import me.carda.awesome_notifications.notifications.managers.ActionReceivedManager;
import me.carda.awesome_notifications.notifications.models.returnedData.ActionReceived;

/**
 * Created by michaelbui on 24/3/18.
 */

public class KeepOnTopActionReceiver extends BroadcastReceiver {

    private static final String TAG = "BroadcastSender";


    @Override
    public void onReceive(final Context context, Intent intent) {
        ActionReceived actionReceived = NotificationBuilder.buildNotificationActionFromIntent(context, intent);
        if (actionReceived != null) {
            try {
                Log.d(TAG, "Action Received. Saving and broadcasting");
                ActionReceivedManager.saveAction(context, actionReceived);
                BroadcastSender.SendBroadcastKeepOnTopAction(
                    context,
                    actionReceived
                );

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
