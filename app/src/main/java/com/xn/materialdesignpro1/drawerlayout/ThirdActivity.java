package com.xn.materialdesignpro1.drawerlayout;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.xn.materialdesignpro1.R;

/**
 * 侧滑效果
 */
public class ThirdActivity extends AppCompatActivity {


    private DrawerLayout mDrawerlayout;
    private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_third);
            mDrawerlayout= findViewById(R.id.drawerlayout);
            mToolbar= findViewById(R.id.toolbar);
            setSupportActionBar(mToolbar);
//            @SuppressLint("ResourceType") ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,mDrawerlayout,mToolbar,R.mipmap.ic_launcher_round,R.mipmap.ic_launcher);
//            toggle.syncState();
//            mDrawerlayout.setDrawerListener(toggle);
            mDrawerlayout.setDrawerListener(new DrawerLayout.DrawerListener() {
                @Override
                public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                    Log.i("slideOffset",slideOffset+"");// slideOffset 0~1
                    View content = mDrawerlayout.getChildAt(0);
                    View menu=drawerView;

                    //设置menu的缩放(0.7-1)
                    float scale=1-slideOffset;//1-0
                    float menuScale= (float) (1-0.3*scale);
                    menu.setScaleY(menuScale);
                    menu.setScaleX(menuScale);

                    //设置内容部分的缩放(1-0.7)
                    float contentScale= (float) (1-0.3*slideOffset);
                    content.setScaleY(contentScale);
                    content.setScaleY(contentScale);

                }

                @Override
                public void onDrawerOpened(@NonNull View drawerView) {

                }

                @Override
                public void onDrawerClosed(@NonNull View drawerView) {

                }

                @Override
                public void onDrawerStateChanged(int newState) {

                }
            });
    }
}
