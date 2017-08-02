package com.example.android_22.android_22_1_ex1;

import android.os.*;
import android.support.v7.app.*;
import android.util.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    final static String TAG = "MainActivity";

    private int mainNum;
    private int secondNum;
    private TextView tvMain, tvSecond;
    private Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvMain = (TextView) findViewById(R.id.tv_main_thread);
        tvSecond = (TextView) findViewById(R.id.tv_second_thread);
        btnStart = (Button) findViewById(R.id.bt_start);

        btnStart.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                startNum();
            }
        });

    }

    private void startNum(){
        mainNum++;
        tvMain.setText("mainNum : " + mainNum);

        NewThread newThread = new NewThread(mainHandler, secondNum);
        newThread.setDaemon(true);
        newThread.start();

    }

    Handler mainHandler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == NewThread.NEWTHREAD_WHAT) {
                secondNum = msg.arg1;
                tvSecond.setText("secondNum : " + secondNum);
                Log.i(TAG, "secondNum in handler : " + secondNum);
            }
        };
    };
}
