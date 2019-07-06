package com.xn.materialdesignpro1.custom_view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.xn.materialdesignpro1.R;

public class MyLoadingView2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_loading_view2);
        MyLoadingView2 loadingView2= findViewById(R.id.loadView);
        loadingView2.setToastText("正在清空缓存");
    }
}
