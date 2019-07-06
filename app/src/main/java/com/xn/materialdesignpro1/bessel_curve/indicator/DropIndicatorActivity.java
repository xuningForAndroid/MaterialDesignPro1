package com.xn.materialdesignpro1.bessel_curve.indicator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;

import com.xn.materialdesignpro1.R;

public class DropIndicatorActivity extends AppCompatActivity {
    private DropViewPager mViewPager;
    private DropIndicator circleIndicator;
    private PagerAdapter mAdapter;
    private int[] imgs={R.drawable.pos0,R.drawable.pos1,R.drawable.pos2,R.drawable.pos3,R.drawable.pos0};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drop_indicator);
        mViewPager = (DropViewPager) findViewById(R.id.viewpager);
        circleIndicator = (DropIndicator) findViewById(R.id.circleIndicator);
        mAdapter = new MyPagerAdapter(imgs,this);
        mViewPager.setAdapter(mAdapter);
        circleIndicator.setViewPager(mViewPager);
    }
}
