package org.techtown.tab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Fragment3 extends Fragment {

    TextView textView;
    ScrollView scrollViewRecv;
    SimpleDateFormat dataFormat = new SimpleDateFormat("yy.MM.dd HH:mm:ss");
    ClientThread clientThread;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment3, container, false);
        EditText editTextIp = view.findViewById(R.id.editTextIp);
        EditText editTextPort = view.findViewById(R.id.editTextPort);
        textView = view.findViewById(R.id.textViewRecv);
        scrollViewRecv = view.findViewById(R.id.scrollViewRecv);
        ToggleButton toggleButtonStart = view.findViewById(R.id.toggleButtonStart);
        Button buttonSend = view.findViewById(R.id.buttonSend);
        EditText editTextSend = view.findViewById(R.id.editTextSend);

        buttonSend.setEnabled(false);  //전송 버튼 비활성화

        //토글버튼 누르면
        toggleButtonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (toggleButtonStart.isChecked()) {    //토글버튼시작으로 확인되면
                    String strIp = editTextIp.getText().toString();   //editTextIp에서 받는 텍스트를 String 타입으로 전환해서 strIp로 저장
                    int intPort = Integer.parseInt(editTextPort.getText().toString());   //editTextId에서 받는 텍스트를 String 타입으로 전환해서 strPort로 저장
                    clientThread = new ClientThread(strIp, intPort);    //ClientThread 생성
                    clientThread.start();        //clientThread start 누르면 ClientThread의 run()이 실행된다
                    buttonSend.setEnabled(true);  //전송 버튼 활성화
                } else {
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
        return view;
    }

    void recvDataProcess(String data){
        Date date = new Date();    //날짜 데이터 생성
        String strDate = dataFormat.format(date);   // date형식으로 불러온 현재 시간을 strDate에 저장
        data += '\n';          //data에 줄바꿈 추가
        strDate = strDate + " " + data;             //strDate + 데이터 + 줄바꿈 -> strDate 저장
        textView.append(strDate);        //
        scrollViewRecv.fullScroll(View.FOCUS_DOWN);
    }
}
