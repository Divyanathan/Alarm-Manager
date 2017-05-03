package com.example.user.alarmmanager.broad_cast_reciver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

import com.example.user.alarmmanager.ui.DisplayAlarm;
import com.example.user.alarmmanager.utility.TagUtilityClass;

/**
 * Created by user on 26/04/17.
 */

public class AlarmReciver extends BroadcastReceiver {
    @Override

    public void onReceive(Context pContext, Intent pIntent) {

//        Toast.makeText(context, "hi", Toast.LENGTH_SHORT).show();

//        Uri lUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
//        Ringtone lRingtone=RingtoneManager.getRingtone(pContext,lUri);
//        lRingtone.play();

        Intent lOpenAlarmClass=new Intent(pContext, DisplayAlarm.class);
        lOpenAlarmClass.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        lOpenAlarmClass.putExtra(TagUtilityClass.INTENT_VALUE,"alarm");
        pContext.startActivity(lOpenAlarmClass);


        Log.v("set_alarm_clicked","alarm ringing");
    }
}
