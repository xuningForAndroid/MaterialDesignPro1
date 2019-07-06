package com.xn.materialdesignpro1.custom_view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xn.materialdesignpro1.R;

public class FlowLayoutActivity extends AppCompatActivity {

    private MyFlowLayout flowLayout;
    private String[] datas={"aaa","你好","可是可是撒飒飒奥撒撒飒飒","没空看说明书可能是可能是","噢噢噢噢","sssss"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_layout);
        flowLayout = findViewById(R.id.flowLayout);
        initData();
    }

    private void initData() {
        for (int i = 0; i < datas.length; i++) {
            TextView textView = new TextView(this);
            textView.setText(datas[i]);
            textView.setGravity(Gravity.CENTER);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(10,10,10,10);
            textView.setLayoutParams(layoutParams);
            flowLayout.addView(textView);
        }
    }
}
