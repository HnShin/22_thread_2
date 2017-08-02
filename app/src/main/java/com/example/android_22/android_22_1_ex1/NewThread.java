package com.example.android_22.android_22_1_ex1;

import android.os.Handler;
import android.os.Message;

/**
 * Created by ASUS on 2017-08-03.
 */

public class NewThread extends Thread {
    final static int NEWTHREAD_WHAT = 0;

    private Handler mHandler;
    private int secondNum;
    public NewThread(Handler h, int sn) {
        this.mHandler = h;
        this.secondNum = sn;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub

        while (true) {
            secondNum++;
            Message msg = Message.obtain();
            msg.what = NEWTHREAD_WHAT;
            msg.arg1 = secondNum;
            mHandler.sendMessage(msg);
            try {
                Thread.sleep(500);
            } catch (Exception e) {}
        }
    }

}
