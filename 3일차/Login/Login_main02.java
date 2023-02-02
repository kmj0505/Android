package com.kcci.feb02;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login_main02 extends AppCompatActivity {
    public static final int REQUEST_CODE_MENU = 101;

    public static final String TAG = "Login_main02";
    TextView textIdView;
    TextView textPwView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main02);

        Button button = findViewById(R.id.button);
        textIdView = findViewById(R.id.textIdView);
        textPwView = findViewById(R.id.textPwView);
        EditText editTextIp = findViewById(R.id.editTextIP);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strIp = editTextIp.getText().toString();
                Log.d(TAG, "onClick: " + strIp);
                Intent intent = new Intent(getApplicationContext(), Login_menu02.class);
                intent.putExtra("Ip", strIp);

                startActivityForResult(intent, 1);


            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: "+requestCode + "," + resultCode);
        String Id = data.getStringExtra("Id");
        String Pw = data.getStringExtra("Pw");
        Log.d(TAG, "onActivityResult: " + Id + ", " + Pw);
        textIdView.setText(Id);
        textPwView.setText(Pw);

    }

}