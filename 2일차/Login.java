package com.kcci.feb01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    String textView1;
    String textView2;
    String setText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);



        Button login = findViewById(R.id.login);

        TextView textView1 = (TextView)findViewById(R.id.textView1);
        TextView textView2 = (TextView)findViewById(R.id.textView2);

        EditText editTextId = findViewById(R.id.editTextId);
        EditText editTextPw = findViewById(R.id.editTextPw);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strId = editTextId.getText().toString();
                String strPw = editTextPw.getText().toString();
                Log.d("onClick()", "Id : " + strId + "Pw : " + strPw);
                textView1.setText("Id : " + strId);
                textView2.setText("Pw : " + strPw);

            }
        });
    }
}