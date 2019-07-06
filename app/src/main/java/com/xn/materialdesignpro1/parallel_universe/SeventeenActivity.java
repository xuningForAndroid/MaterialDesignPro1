package com.xn.materialdesignpro1.parallel_universe;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.xn.materialdesignpro1.R;

/**
 * 平行空间欢迎界面效果
 *
 * 1.viewPager+relativeLayout
 * 2.平移、翻转动画
 * 3.
 */
public class SeventeenActivity extends AppCompatActivity {

    private int[] layouts={
            R.layout.welcome1,R.layout.welcome2,R.layout.welcome3
    };

    private ViewPager vp;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#509de1"));
        setContentView(R.layout.activity_seventeen);
        vp= findViewById(R.id.viewpager);
        WelComePageAdapter adapter=new WelComePageAdapter(getSupportFragmentManager());
        vp.setOffscreenPageLimit(layouts.length);
        vp.setAdapter(adapter);

        WelcompagerTransformer transformer = new WelcompagerTransformer();
        vp.setPageTransformer(true,transformer);

        vp.setOnPageChangeListener(transformer);
    }

    class WelComePageAdapter extends FragmentPagerAdapter{

        public WelComePageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            TranslateFragment fragment = new TranslateFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("layoutId",layouts[position]);
            bundle.putInt("pageIndex",position);
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }
    }
}
