package com.kcci.feb02test;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textIdView;
    TextView textPwView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        textIdView = findViewById(R.id.textIdView);
        textPwView = findViewById(R.id.textPwView);
        EditText editTextIP = findViewById(R.id.editTextIP);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String strIp = editTextIP.getText().toString();
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                intent.putExtra("Ip", strIp);

                startActivityForResult(intent, 1);


            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String strId = data.getStringExtra("Id");
        String strPw = data.getStringExtra("Pw");
        textIdView.setText(strId);
        textPwView.setText(strPw);

    }
}