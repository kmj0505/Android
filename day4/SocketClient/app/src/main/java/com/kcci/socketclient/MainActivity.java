package com.kcci.socketclient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    static Handler mainHandler;
    ClientThread clientThread;
    TextView textView;
    ScrollView scrollViewRecv;
    SimpleDateFormat dataFormat = new SimpleDateFormat("yy.MM.dd HH:mm:ss");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editTextIp = findViewById(R.id.editTextIp);
        EditText editTextPort = findViewById(R.id.editTextPort);
        textView = findViewById(R.id.textViewRecv);
        scrollViewRecv = findViewById(R.id.scrollViewRecv);
        ToggleButton toggleButtonStart = findViewById(R.id.toggleButtonStart);
        Button buttonSend = findViewById(R.id.buttonSend);
        EditText editTextSend = findViewById(R.id.editTextSend);

        buttonSend.setEnabled(false);  //전송 버튼 비활성화

        //토글버튼 누르면
        toggleButtonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(toggleButtonStart.isChecked()) {    //토글버튼시작으로 확인되면
                    String strIp = editTextIp.getText().toString();   //editTextIp에서 받는 텍스트를 String 타입으로 전환해서 strIp로 저장
                    int intPort = Integer.parseInt(editTextPort.getText().toString());   //editTextId에서 받는 텍스트를 String 타입으로 전환해서 strPort로 저장
                    clientThread = new ClientThread(strIp, intPort);    //ClientThread 생성
                    clientThread.start();        //clientThread start 누르면 ClientThread의 run()이 실행된다
                    buttonSend.setEnabled(true);  //전송 버튼 활성화
                } else{
                    clientThread.stopClient();    //토글버튼시작으로 확인이 안되면
                    buttonSend.setEnabled(false);    //전송 버튼 비활성화
                }
            }
        });
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {      //전송 버튼이 눌리면
                String strSend = editTextSend.getText().toString();    //editTextSend에서 받은 문자를 문자열로 변환 후 strSend에 저장
                clientThread.sendData(strSend);  //clientThread에 strSend데이터 보냄
                editTextSend.setText("");        //strSend데이터 보내고 editTextSend창 깨끗하게
            }
        });
        mainHandler = new MainHandler();    // mainHandler 생성
    }
    class MainHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Date date = new Date();    //날짜 데이터 생성
            String strDate = dataFormat.format(date);   // date형식으로 불러온 현재 시간을 strDate에 저장
            Bundle bundle = msg.getData();             // msg로 받은 데이터를 bundle 객체에 저장
            String data = bundle.getString("msg");     //bundle객체에서 저장된 "msg"text data에 저장
            data += '\n';          //data에 줄바꿈 추가
            strDate = strDate + " " + data;             //strDate + 데이터 + 줄바꿈 -> strDate 저장
            textView.append(strDate);        //textView에 입력받은 데이터 strDate를 나타낸다
            scrollViewRecv.fullScroll(View.FOCUS_DOWN);
        }
    }
}