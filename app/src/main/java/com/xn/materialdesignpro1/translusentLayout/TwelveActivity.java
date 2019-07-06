package com.xn.materialdesignpro1.translusentLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.xn.materialdesignpro1.R;

/**
 * 华为手机做特殊处理,将其余的封装起来
 */
public class TwelveActivity extends TranslucentBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_eleventh);
       Toolbar toolbar= findViewById(R.id.toolbar);
        View navigationbar = findViewById(R.id.nav);
        setOrChangeTranslucentColor(toolbar,navigationbar, Color.parseColor("#509de1"));
    }
}
