package com.xn.materialdesignpro1.translusentLayout;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.xn.materialdesignpro1.R;

/**
 * 4.4+ 有虚拟导航，设置虚拟导航底部颜色
 */
public class EleventhActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusAndnavigationBarUtil.setStatusBarColor(this,Color.parseColor("#509de1"));
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//
        setContentView(R.layout.activity_eleventh);

        Toolbar toolbar= findViewById(R.id.toolbar);
        ViewGroup.LayoutParams layoutParams = toolbar.getLayoutParams();
        //在toobar高度上再加上status高度
        layoutParams.height+=getStatusBarHeight(this);
        toolbar.setLayoutParams(layoutParams);
        toolbar.setTitle("消息");
        toolbar.setNavigationIcon(R.mipmap.ic_launcher_round);
        //设置padding，防止内容被status遮盖
        toolbar.setPadding(toolbar.getPaddingLeft(),toolbar.getPaddingTop()+getStatusBarHeight(this),
                toolbar.getPaddingRight(),toolbar.getPaddingBottom());
        setSupportActionBar(toolbar);

        //如果没有虚拟导航栏，直接设置
//        StatusAndnavigationBarUtil.setNavigationBarColor(this,Color.parseColor("#509de1"));

        //这个的作用是适配，解决4.4+虚拟导航栏
        View nav = findViewById(R.id.nav);
        ViewGroup.LayoutParams layoutParams2 = nav.getLayoutParams();
        layoutParams2.height+= getNavigationBarHeight(this);
        nav.setLayoutParams(layoutParams2);

    }
    private int getStatusBarHeight(Context context) {
        // 反射手机运行的类：android.R.dimen.status_bar_height.
        return getSystemComponentDimen(this,"status_bar_height");
    }
    private int getNavigationBarHeight(Context context) {
        return getSystemComponentDimen(this, "navigation_bar_height");
    }

    public static int getSystemComponentDimen(Context context, String dimenName){
        int myHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            String heightStr = clazz.getField(dimenName).get(object).toString();
            int height = Integer.parseInt(heightStr);
            //dp--->px
            myHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myHeight;
    }
}
