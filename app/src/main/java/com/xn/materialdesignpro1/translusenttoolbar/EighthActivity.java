package com.xn.materialdesignpro1.translusenttoolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.xn.materialdesignpro1.R;

public class EighthActivity extends AppCompatActivity implements ScrollTranslusentListener{

    private Toolbar mToolbar;
    private MyScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eighth);
        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);

        scrollView = findViewById(R.id.scrollView);
        scrollView.setTranslusentListener(this);

        int color=11;
        float persent=0.6f;
        //方法更改颜色的透明度
        setTransLusentAlpha(color,persent);
    }

    /**
     * 更改颜色的透明度
     * @param color   需要设置的颜色
     * @param persent 需要的透明度比例
     */
    private void setTransLusentAlpha(int color, float persent) {
        //1.将颜色中的argb提取出来
//        int blue = color  & 0xff;
        int blue = Color.blue(color);

//        int green = color >> 8 & 0xff;
        int green = Color.green(color);

//        int red = color >> 16 & 0xff;
        int red = Color.red(color);

//        int alpha = color >>> 24;
        int alpha = Color.alpha(color);
        //2.进行重新设置
        alpha= Math.round (alpha*persent);

        color= Color.argb(alpha,red,green,blue);
    }

    @Override
    public void onTranslusent(float alpha) {
        mToolbar.setAlpha(alpha);
    }
}
