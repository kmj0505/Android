package org.techtown.tab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Fragment1 extends Fragment {

    MainActivity mainActivity;
    TextView textViewRecv1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, container, false);

        mainActivity = (MainActivity)getActivity();
        EditText editTextSend1 = view.findViewById(R.id.editTextSend1);
        textViewRecv1 = view.findViewById(R.id.textViewRecv1);
        Button button = view.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strData = editTextSend1.getText().toString();    //editTextSend에서 받은 문자를 문자열로 변환 후 strSend에 저장
                mainActivity.clientThread.sendData(strData);
                editTextSend1.setText("");
            }
        });
        return view;
    }

    void recvDataProcess(String data){
        data += '\n';          //data에 줄바꿈 추가
        textViewRecv1.append(data);        //
    }


}
