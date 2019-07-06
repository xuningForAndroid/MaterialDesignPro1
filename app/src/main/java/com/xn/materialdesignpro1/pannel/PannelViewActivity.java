package com.xn.materialdesignpro1.pannel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.xn.materialdesignpro1.R;

public class PannelViewActivity extends AppCompatActivity {

    private SlidingUpPanelLayout panelLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pannel_view);
        panelLayout = findViewById(R.id.pannel);
    }
}
