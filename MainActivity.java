package com.kcci.buttontest;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintest2);
    }
    public void onButton1Clicked(View v)
    {
        Log.d("onButton1Clicked()", "Test1");
        Toast.makeText(this, "확인1 버튼이 눌렸어요.", Toast.LENGTH_SHORT).show();
        Log.d("onButton1Clicked()", "Test2");
    }

    public void onButton2Clicked(View v)
    {
        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.naver.com"));
        startActivity(myIntent);
    }

    public void onButton3Clicked(View v)
    {
        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:010-1234-1234"));
        startActivity(myIntent);
    }


}