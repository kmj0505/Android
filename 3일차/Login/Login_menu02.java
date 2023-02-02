package com.kcci.feb02;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class Login_menu02 extends AppCompatActivity {
    EditText editTextId;
    EditText editTextPw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_menu02);
        TextView textIpView = (TextView)findViewById(R.id.textIpView);
        EditText editTextId = findViewById(R.id.editTextId);
        EditText editTextPw = findViewById(R.id.editTextPw);
        Button login = findViewById(R.id.login);

        Intent intent2 = getIntent();
        String strIP = intent2.getStringExtra("Ip");
        textIpView.setText(strIP);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strId = editTextId.getText().toString();
                String strPw = editTextPw.getText().toString();
                //Intent intent2 = new Intent(getApplicationContext(), Login_menu02.class);

                //startActivityForResult(intent2, 1);

                Intent intent = new Intent();
                intent.putExtra("Id", strId);
                intent.putExtra("Pw", strPw);
                setResult(RESULT_OK, intent);

                finish();


            }
        });
    }
}
