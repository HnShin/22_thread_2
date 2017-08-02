package com.example.android_22.android_22_2_ex1;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * Created by ASUS on 2017-08-03.
 */


    public class InstrumentSound extends Thread {

        final static int SOUND_PIANO = 0;
        final static int SOUND_GUITAR = 1;
        final static int SOUND_DRUM = 2;

        private Handler mHandler;
        private Handler handler;

        // 생성될때 전달받은 핸들러를 mHandler에 넣음
        public InstrumentSound(Handler h) {
            this.mHandler = h;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            Looper.prepare(); // 루퍼를 만든다
            // 핸들러가 있어야 메세지를 받을 수 있으므로 핸들러도 만든다
            handler = new Handler() {
                public void handleMessage(Message msg) {
                    Message msgForMain = Message.obtain();
                    // msg의 what에 따라서 switch문실행
                    // 그 결과에 해당하는 것을 msgForMain을 이용해서전달
                    switch (msg.what) {
                        case SOUND_PIANO:
                            try {
                                Thread.sleep(3000);
                            } catch (Exception e) {
                            }
                            msgForMain.what = SOUND_PIANO;
                            msgForMain.obj = "피아노 소리";
                            break;

                        case SOUND_GUITAR:
                            try {
                                Thread.sleep(4000);
                            } catch (Exception e) {
                            }
                            msgForMain.what = SOUND_GUITAR;
                            msgForMain.obj = "기타 소리";
                            break;
                        case SOUND_DRUM:
                            try {
                                Thread.sleep(5000);
                            } catch (Exception e) {
                            }
                            msgForMain.what = SOUND_DRUM;
                            msgForMain.obj = "드럼 소리";
                            break;
                    }
                    //메인 핸들러에 전달
                    mHandler.sendMessage(msgForMain);
                }
            };

            Looper.loop();

        }

        public Handler getHandler() {
            return handler;
        }

    }

