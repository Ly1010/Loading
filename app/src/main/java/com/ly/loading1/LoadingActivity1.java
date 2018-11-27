package com.ly.loading1;

import android.app.Activity;
import android.os.Bundle;

import com.ly.loading1.view.LoadingView;

public class LoadingActivity1 extends Activity {

    private LoadingView view_loading_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading1);

        view_loading_1 = findViewById(R.id.view_loading_1);
        view_loading_1.startAnim();
    }
}
