package com.ly.loading1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_main_baiduloading;
    private Button btn_main_loading1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_main_baiduloading = findViewById(R.id.btn_main_baiduloading);
        btn_main_loading1 = (Button) findViewById(R.id.btn_main_loading1);

        btn_main_loading1.setOnClickListener(this);
        btn_main_baiduloading.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_main_baiduloading:
                Intent intent = new Intent(MainActivity.this, BaiduLoadingActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_main_loading1:
                Intent intent1 = new Intent(MainActivity.this, LoadingActivity1.class);
                startActivity(intent1);
                break;
        }
    }
}
