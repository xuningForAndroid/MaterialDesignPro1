package com.xn.materialdesignpro1.value_anim;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.xn.materialdesignpro1.R;

public class ValueAnimatordemo2Activity extends AppCompatActivity {

    private View view1;
    private View view2;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_animatordemo2);
        view1 = findViewById(R.id.view1);
        view2 = findViewById(R.id.view2);
        textView = findViewById(R.id.view1_tv);

    }

    /**
     * 点击显示按钮执行动画
     * @param view
     */
    public void playAnimator1(View view) {
        textView.setClickable(false);
        ObjectAnimator rotationX=ObjectAnimator.ofFloat(view1,"rotationX",0f,25f);
        rotationX.setDuration(200);
        ObjectAnimator rotationX2=ObjectAnimator.ofFloat(view1,"rotationX",25f,0f);
        rotationX2.setDuration(200);
        ObjectAnimator scaleX=ObjectAnimator.ofFloat(view1,"scaleX",1f,0.8f);
        scaleX.setDuration(200);
        ObjectAnimator scaleY=ObjectAnimator.ofFloat(view1,"scaleY",1f,0.8f);
        scaleY.setDuration(200);
        ObjectAnimator translationY=ObjectAnimator.ofFloat(view1,"translationY",0,-0.1f*view1.getHeight());
        translationY.setDuration(200);
        ObjectAnimator alpha=ObjectAnimator.ofFloat(view1,"alpha",1f,0.8f);
        alpha.setDuration(200);
        //设置下边的布局弹上来
        ObjectAnimator view2_translationY=ObjectAnimator.ofFloat(view2,"translationY",view2.getHeight(),0f);
        view2_translationY.setDuration(200);

        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.play(rotationX2).with(translationY).after(rotationX).with(scaleX).with(scaleY).with(alpha).with(view2_translationY);//先旋转再转回
        animatorSet.start();
    }

    /*
    点击下方图片执行相反方向的动画
     */
    public void playAnimator2(View view) {
        textView.setClickable(true);
        ObjectAnimator rotationX=ObjectAnimator.ofFloat(view1,"rotationX",0f,25f);
        rotationX.setDuration(200);
        ObjectAnimator rotationX2=ObjectAnimator.ofFloat(view1,"rotationX",25f,0f);
        rotationX2.setDuration(200);
        ObjectAnimator scaleX=ObjectAnimator.ofFloat(view1,"scaleX",0.8f,1f);
        scaleX.setDuration(200);
        ObjectAnimator scaleY=ObjectAnimator.ofFloat(view1,"scaleY",0.8f,1f);
        scaleY.setDuration(200);
        ObjectAnimator translationY=ObjectAnimator.ofFloat(view1,"translationY",-0.1f*view1.getHeight(),0);
        translationY.setDuration(200);
        ObjectAnimator alpha=ObjectAnimator.ofFloat(view1,"alpha",0.8f,1f);
        alpha.setDuration(200);

        ObjectAnimator view2_translationY=ObjectAnimator.ofFloat(view2,"translationY",0f,view2.getHeight());
        view2_translationY.setDuration(200);
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.play(view2_translationY).with(rotationX).with(scaleX).with(scaleY).with(alpha).before(rotationX2).with(translationY);
        animatorSet.start();
    }
}
