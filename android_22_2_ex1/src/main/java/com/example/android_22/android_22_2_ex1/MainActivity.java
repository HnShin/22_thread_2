package com.example.android_22.android_22_2_ex1;

import android.support.v7.app.ActionBarActivity;
import android.os.*;
import android.support.v7.app.AppCompatActivity;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    TextView tvSound;
    Button btnPiano, btnGuitar, btnDrum;
    InstrumentSound instrumentSound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvSound = (TextView) findViewById(R.id.tv_sound);
        btnPiano = (Button) findViewById(R.id.bt_conversion_piano);
        btnGuitar = (Button) findViewById(R.id.bt_conversion_guitar);
        btnDrum = (Button) findViewById(R.id.bt_conversion_drum);

        btnPiano.setOnClickListener(listener);
        btnGuitar.setOnClickListener(listener);
        btnDrum.setOnClickListener(listener);

        instrumentSound = new InstrumentSound(mHandler);
        instrumentSound.setDaemon(true);
        instrumentSound.start();

    }

    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            String str = "";
            switch (msg.what) {
                case InstrumentSound.SOUND_PIANO:
                    Toast.makeText(MainActivity.this, "피아노소리로 전환 완료", Toast.LENGTH_SHORT).show();
                    str = (String) msg.obj;
                    break;

                case InstrumentSound.SOUND_GUITAR:
                    Toast.makeText(MainActivity.this, "기타소리로 전환 완료", Toast.LENGTH_SHORT).show();
                    str = (String) msg.obj;
                    break;

                case InstrumentSound.SOUND_DRUM:
                    Toast.makeText(MainActivity.this, "드럼소리로 전환 완료", Toast.LENGTH_SHORT).show();
                    str = (String) msg.obj;
                    break;
            }
            tvSound.setText(str);
            //insturment가 보내는 것을 인식해 토스트를 띠운다
        };
    };

    OnClickListener listener = new OnClickListener() {
        Message msg;
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            switch (v.getId()) {
                case R.id.bt_conversion_piano:
                    msg = Message.obtain();
                    msg.what = InstrumentSound.SOUND_PIANO;

                    break;

                case R.id.bt_conversion_guitar:
                    msg = Message.obtain();
                    msg.what = InstrumentSound.SOUND_GUITAR;
                    break;
                case R.id.bt_conversion_drum:
                    msg = Message.obtain();
                    msg.what = InstrumentSound.SOUND_DRUM;
                    break;
            }

            instrumentSound.getHandler().sendMessage(msg);
        }
    };
}

