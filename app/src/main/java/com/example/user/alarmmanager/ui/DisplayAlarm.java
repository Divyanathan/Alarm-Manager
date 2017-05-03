package com.example.user.alarmmanager.ui;

import android.app.AlarmManager;
import android.app.KeyguardManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.PowerManager;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.user.alarmmanager.R;
import com.example.user.alarmmanager.broad_cast_reciver.AlarmReciver;
import com.example.user.alarmmanager.utility.TagUtilityClass;

import java.util.Calendar;

public class DisplayAlarm extends AppCompatActivity {

    Ringtone lRingtone;


    final String TAG="DisplayAlarm";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_alarm);
//        Vibrator lVibrator= (Vibrator) this.getSystemService(this.VIBRATOR_SERVICE);
//        lVibrator.vibrate(5000);

        wakeDevice();

        String lDisplayString=getIntent().getStringExtra(TagUtilityClass.INTENT_VALUE);

        TextView lDispayValueTextView=(TextView) findViewById(R.id.display_value);

        if (lDisplayString.equals("alarm")){
            lDispayValueTextView.setText(""+Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+
                    Calendar.getInstance().get(Calendar.MINUTE) ) ;
        }else {
            lDispayValueTextView.setText(lDisplayString);
        }


        Uri lUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
         lRingtone=RingtoneManager.getRingtone(this,lUri);
        lRingtone.play();

        Button lStopAlarmButton=(Button) findViewById(R.id.stop_alarm);
        lStopAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lRingtone.stop();

                Intent intent = new Intent(DisplayAlarm.this, AlarmReciver.class);
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent,0);
                alarmManager.cancel(pendingIntent);
                DisplayAlarm.this.finish();
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        lRingtone.stop();
        finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unregisterReceiver(mKillReceiver);
    }
    private final class KillReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            finish();
        }
    }

    public void wakeDevice() {



        KeyguardManager lKeyguardManager = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
        KeyguardManager.KeyguardLock keyguardLock = lKeyguardManager.newKeyguardLock("TAG");
        keyguardLock.disableKeyguard();
        runOnUiThread(new Runnable(){
            public void run(){

                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG,"onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "onRestart: " );
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: " );
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop: " );
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: " );
    }
}

