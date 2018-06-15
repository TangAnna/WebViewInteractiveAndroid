package com.example.tanganan.webviewinteractiveandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_main_js).setOnClickListener(this);
        findViewById(R.id.btn_main_android).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_main_js:
                startActivity(new Intent(this, JsCallAndroidActivity.class));
                break;
            case R.id.btn_main_android:
                break;
        }
    }
}
