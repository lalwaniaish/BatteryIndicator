package com.example.aishlalwani.batteryindicator;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView mBatteryLevelText;
    private ProgressBar mBatteryLevelProgress;
    private BroadcastReceiver mReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBatteryLevelText = (TextView) findViewById(R.id.textView);
        mBatteryLevelProgress = (ProgressBar) findViewById(R.id.progressBar);
        mReceiver = new BatteryBroadcastReceiver();
    }

    @Override
    protected void onStart() {
        registerReceiver(mReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        super.onStart();

    }

    @Override
    protected void onStop() {
        unregisterReceiver(mReceiver);
        super.onStop();
    }

    private class BatteryBroadcastReceiver extends BroadcastReceiver {

        private final static String BATTERY_LEVEL = "level";

        @Override
        public void onReceive(Context context, Intent intent) {
            int level = intent.getIntExtra(BATTERY_LEVEL, 0);

            mBatteryLevelText.setText("Battery Level: " + " " + level);
            mBatteryLevelProgress.setProgress(level);
        }
    }
}
