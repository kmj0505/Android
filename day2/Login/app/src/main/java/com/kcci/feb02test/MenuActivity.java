package com.kcci.feb02test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    TextView textIpView;
    EditText editTextId;
    EditText editTextPw;
    String TAG = "MenuActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        textIpView = findViewById(R.id.textIpView);
        Button login = findViewById(R.id.login);
        EditText editTextId = findViewById(R.id.editTextId);
        EditText editTextPw = findViewById(R.id.editTextPw);

        Intent intent2 = getIntent();
        String strIp = intent2.getStringExtra("Ip");
        Log.d(TAG, "onCreate: " + strIp);
        textIpView.setText(strIp);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strId = editTextId.getText().toString();
                String strPw = editTextPw.getText().toString();

                Intent intent = new Intent();
                intent.putExtra("Id", strId);
                intent.putExtra("Pw", strPw);
                setResult(RESULT_OK, intent);

                finish();

            }
        });

    }
}