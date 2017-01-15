package com.hidakei.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver br = new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED)){
                int level = intent.getIntExtra("level", 0);
                int scale = intent.getIntExtra("scale", 0);
                TextView tv = (TextView)findViewById(R.id.textView);
                tv.setText("batt res: " + (int)(1f*level/scale*100) + "%");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final TextView tv = (TextView)findViewById(R.id.textView);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                tv.setText("bt p");

            }
        });

    }

    @Override
    protected void onStart() {
        Log.i("TAG","onStart");
        super.onStart();

    }

    @Override
    protected void onResume() {
        Log.i("TAG","onResume");
        super.onResume();
        IntentFilter nif = new IntentFilter();
        nif.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(br, nif);
    }

    @Override
    protected void onPause() {
        Log.i("TAG","onPause");
        super.onPause();
        unregisterReceiver(br);
    }




}
