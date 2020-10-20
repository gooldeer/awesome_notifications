package me.carda.awesome_notifications.notifications.managers;

import android.content.Context;

import com.google.common.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import me.carda.awesome_notifications.Definitions;
import me.carda.awesome_notifications.notifications.models.returnedData.ActionReceived;

public class ActionReceivedManager {

    private static SharedManager<ActionReceived> shared = new SharedManager<>();
    private static Type typeToken = new TypeToken<ActionReceived>(){}.getType();

    public static Boolean removeAction(Context context, Integer id) {
        return shared.remove(context, Definitions.SHARED_ACTIONS, id.toString());
    }

    public static List<ActionReceived> listActions(Context context) {
        return shared.getAllObjects(context, typeToken, Definitions.SHARED_ACTIONS);
    }

    public static void saveAction(Context context, ActionReceived received) {
        shared.set(context, Definitions.SHARED_ACTIONS, received.id.toString(), received);
    }

    public static ActionReceived getActionByKey(Context context, Integer id){
        return shared.get(context, typeToken, Definitions.SHARED_ACTIONS, id.toString());
    }

    public static void cancelAllActions(Context context) {
        List<ActionReceived> receivedList = shared.getAllObjects(context, typeToken, Definitions.SHARED_ACTIONS);
        if (receivedList != null){
            for (ActionReceived received : receivedList) {
                cancelAction(context, received.id);
            }
        }
    }

    public static void cancelAction(Context context, Integer id) {
        ActionReceived received = getActionByKey(context, id);
        if(received !=null)
            removeAction(context, received.id);
    }
}