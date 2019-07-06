package com.xn.materialdesignpro1.translusentLayout;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class StatusAndnavigationBarUtil {

    public static void setStatusBarColor(Activity activity, int color) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            //5.0以上可以直接设置 statusbar颜色
            activity.getWindow().setStatusBarColor(color);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //4.4~5.0之间自己设置
            //设置statusbar透明
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //创建statusbarview设置背景 高度等于系统的statusbar高度
            View statusBarView = getStatusBarView(activity);
            statusBarView.setBackgroundColor(color);
            //获得contentview 并添加创建的statusbarview
            ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, getStatusBarHeigh(activity));
            params.gravity = Gravity.TOP;
            statusBarView.setLayoutParams(params);
            decorView.addView(statusBarView);
        } else {
            //4.4以下无法设置statusbar颜色
        }
    }

    public static View getStatusBarView(Activity activity) {
        View view = new View(activity);
        return view;
    }

    public static int getStatusBarHeigh(Context context) {
        int height = 0;
        int id = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (id > 0) {
            height = context.getResources().getDimensionPixelSize(id);
        }
        return height;
    }
    public static void hideBottomUIMenu(Activity activity){
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = activity.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView =activity. getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                    | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                    | View.SYSTEM_UI_FLAG_IMMERSIVE;
            decorView.setSystemUiVisibility(uiOptions);
//           activity. getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }
    public static void setNavigationBarColor(Activity activity, int color) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            //5.0以上可以直接设置 navigation颜色
            activity.getWindow().setNavigationBarColor(color);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // android:clipToPadding="false"
            //android:fitsSystemWindows="true"
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
            View mNavigationBar = getNavigationBarView(activity);
            FrameLayout.LayoutParams params;
            params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, getNavigationBarHeight(activity));
            params.gravity = Gravity.BOTTOM;
            mNavigationBar.setLayoutParams(params);
            mNavigationBar.setBackgroundColor(color);
            decorView.addView(mNavigationBar);
        }else{
            //4.4以下无法设置navigationbar颜色
        }

    }

    public static View getNavigationBarView(Activity activity) {
        View view = new View(activity);
        return view;
    }

    public static int getNavigationBarHeight(Context context) {
        int height = 0;
        int id = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        if (id > 0) {
            height = context.getResources().getDimensionPixelSize(id);
        }
        return height;
    }

}
