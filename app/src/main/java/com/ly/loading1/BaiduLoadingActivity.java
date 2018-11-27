package com.ly.loading1;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.ly.loading1.view.BaiduView;

public class BaiduLoadingActivity extends Activity {

    private BaiduView view_baidu_loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baidu);

        view_baidu_loading = findViewById(R.id.view_baidu_loading);
        view_baidu_loading.startAnim();
    }
}
