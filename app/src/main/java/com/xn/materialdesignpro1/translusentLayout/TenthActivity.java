package com.xn.materialdesignpro1.translusentLayout;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.xn.materialdesignpro1.R;

public class TenthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        setContentView(R.layout.activity_tenth);
        Toolbar toolbar =  findViewById(R.id.toolbar);
//：现在布局中设置toolbar的高度?attr/actionBarSize
        ViewGroup.LayoutParams layoutParams = toolbar.getLayoutParams();
        //在toobar高度上再加上status高度
        layoutParams.height+=getStatusBarHeight(this);
        toolbar.setLayoutParams(layoutParams);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);


//      ：  设置paddingTop，以达到状态栏不遮挡toolbar的内容。
        toolbar.setPadding(
                toolbar.getPaddingLeft(),
                toolbar.getPaddingTop()+getStatusBarHeight(this),
                toolbar.getPaddingRight(),
                toolbar.getPaddingBottom());

    }


    /**
     * 获取状态栏的高度
     * @param context
     * @return
     */
    private int getStatusBarHeight(Context context) {
        // 反射手机运行的类：android.R.dimen.status_bar_height.
        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            String heightStr = clazz.getField("status_bar_height").get(object).toString();
            int height = Integer.parseInt(heightStr);
            //dp--->px
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }
}
