package com.xn.materialdesignpro1.translusentLayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;

import com.xn.materialdesignpro1.R;

/**
 * 沉浸式状态栏
 * 兼容到4.4
 * 步骤：
 * 1、//设置状态栏透明
 getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
   2、 //最外层布局
 android:fitsSystemWindows="true"
 * 3、//分别给最外层、toobar、下方内容部分分别设置background颜色
 *
 * 方法2：
 * SystemBarTintManager
 */
public class NinthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏透明
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_ninth);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            //5.0以上直接用api修改状态栏颜色
//            getWindow().setStatusBarColor(Color.parseColor("#509de1"));
//        }
       Toolbar toolbar= findViewById(R.id.toolbar);
       toolbar.setTitle("");
        setSupportActionBar(toolbar);

//        android:fitsSystemWindows="true"设置布局时，是否考虑当前系统窗口布局（包括状态栏view）

    }
}
