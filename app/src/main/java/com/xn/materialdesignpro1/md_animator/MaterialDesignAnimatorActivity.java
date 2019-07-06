package com.xn.materialdesignpro1.md_animator;

import android.animation.Animator;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;

import com.xn.materialdesignpro1.R;

public class MaterialDesignAnimatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_design_animator);
    }

    //实现点击按钮的 图形水波纹揭露效果
    public void rippleBtn(View view) {
        switch (view.getId()){
            case R.id.btn:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Animator animator = ViewAnimationUtils.createCircularReveal(view, 0, 0, 0f, (float) Math.hypot(view.getWidth(), view.getHeight()));
                    animator.setDuration(1000);
                    animator.start();
                }
                break;
            case R.id.tv:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        Animator circularReveal = ViewAnimationUtils.createCircularReveal(view, view.getWidth() / 2, view.getHeight() / 2, 0, (float) view.getWidth());
                        circularReveal.setDuration(1000);
                        circularReveal.start();
                }
                
            break;
        }
    }
}
