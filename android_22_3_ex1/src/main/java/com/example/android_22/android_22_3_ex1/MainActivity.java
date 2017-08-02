package com.example.android_22.android_22_3_ex1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final static String TAG = "MainActivity";
    final static int ALL_DELETE = 0;

    Button btnDelete, btn01, btn02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDelete = (Button) findViewById(R.id.bt_delete);
        btn01 = (Button) findViewById(R.id.bt_01);
        btn02 = (Button) findViewById(R.id.bt_02);

        btnDelete.setOnClickListener(listener);
        btn01.setOnClickListener(listener);
        btn02.setOnClickListener(listener);
    }

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ALL_DELETE:
                    allDataDelete();
                    break;
            }
        };
    };

    View.OnClickListener listener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.bt_delete:
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("삭제")
                            .setMessage("전체 삭제 하시겠습니까? \n 다소 시간이 소요될 수 있습니다.")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
//							allDataDelete();
                                    // 메세지를 사용하지 않고 바로 allDataDelete() 메소드 실행시에는
                                    // 완료시까지 작업을 잡고 멈춰있는다
                                    // 방지하기 위해서 작업시간이 오래 걸리면 메세지를 이용한다
                                    Message msg = Message.obtain();
                                    msg.what = ALL_DELETE;
                                    handler.sendMessageDelayed(msg, 10000);//10초 뒤에 메세지를 핸들러에 전달
                                    Log.i(TAG, "onClick() End");
                                }
                            })
                            .setNegativeButton("Cancel", null)
                            .show();
                    break;

                case R.id.bt_01:
                    Toast.makeText(MainActivity.this, "덧셈 연산 수행", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.bt_02:
                    Toast.makeText(MainActivity.this, "뺄셈 연산 수행", Toast.LENGTH_SHORT).show();
                    break;
            }

        }
    };

    private void allDataDelete() {
        Toast.makeText(MainActivity.this, "삭제 시작", Toast.LENGTH_SHORT).show();

        try {
            Thread.sleep(10000);
        } catch (Exception e) {}

        Toast.makeText(MainActivity.this, "삭제 완료", Toast.LENGTH_SHORT).show();

        Log.i(TAG, "allDataDelete() End");

    }
}

