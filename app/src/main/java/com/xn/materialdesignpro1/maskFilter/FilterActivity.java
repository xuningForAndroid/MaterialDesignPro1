package com.xn.materialdesignpro1.maskFilter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * 滤镜与颜色通道
 * 颜色矩阵运算
 */
public class FilterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MaskFilterView maskFilterView = new MaskFilterView(this);
        setContentView(maskFilterView);
//        setContentView(R.layout.activity_filter);
    }
}
