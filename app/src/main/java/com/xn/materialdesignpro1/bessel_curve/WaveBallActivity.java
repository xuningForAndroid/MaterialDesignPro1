package com.xn.materialdesignpro1.bessel_curve;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.xn.materialdesignpro1.R;

public class WaveBallActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wave_ball);
        final WaveBall waveBall= findViewById(R.id.waveball);
        final RelativeLayout ll_parent= findViewById(R.id.ll_parent);
        waveBall.setColorListener(new WaveBall.OnAngleColorListener() {
            @Override
            public void colorListener(int red, int green) {
                Color color=new Color();
                //通过Color对象将RGB值转为int类型
                int backColor= Color.argb(100,red,green,0);
                //父布局设置背景
                ll_parent.setBackgroundColor(backColor);
            }
        });
        waveBall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                waveBall.changeAngle(200);
            }
        });
    }
}
