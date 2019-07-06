package com.xn.materialdesignpro1.value_anim;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.xn.materialdesignpro1.R;

/**
 * 属性动画：真实改变控件属性
 *补间动画是假象
 *
 * 注意：view.setTranslationX(100) --是将坐标直接定位，不是连续动
 *
 * 属性动画与抛物线
 */
public class ValueAnimatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_animator);

    }

    public void click(final View view) {
        //方法1：
        //没有haha属性时就是ValueAnimator
//        @SuppressLint("ObjectAnimatorBinding")
//        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "haha", 0f, 100f);
//        animator.setDuration(200);
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                //动画执行过程不断调用此方法
//                float value = (float) animation.getAnimatedValue();//是一个从0到100的值
//                view.setScaleX(value/300+0.7f);//0.5-1 0-0.5    0.7-1   0-0.3
//                view.setScaleY(value/300+0.7f);
//            }
//        });
//        animator.start();
        //方法2：
//        ValueAnimator animator=ValueAnimator.ofFloat(0f, 100f);
//        animator.setDuration(200);
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                //动画执行过程不断调用此方法
//                float value = (float) animation.getAnimatedValue();//是一个从0到100的值
//                view.setScaleX(value/300+0.7f);//0.5-1 0-0.5    0.7-1   0-0.3
//                view.setScaleY(value/300+0.7f);
//            }
//        });

        //方法3：
//        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 1f, 0.7f,1f);
//        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 1f,0.7f, 1f);
//        ValueAnimator animator = ObjectAnimator.ofPropertyValuesHolder(view,scaleX, scaleY);
//        animator.setInterpolator(new DecelerateInterpolator());
//        animator.setDuration(200);
//        animator.start();
        //方法4：
//        ObjectAnimator animator1=ObjectAnimator.ofFloat(view,"scaleX",1f,0.7f,1f);
//        ObjectAnimator animator2=ObjectAnimator.ofFloat(view,"scaleY",1f,0.7f,1f);
//        AnimatorSet animatorSet=new AnimatorSet();
//        animatorSet.play(animator1).with(animator2);
//        animator1.start();

        //实现抛物线  水平vt 竖直1/2gtt
        final ValueAnimator animator=new ValueAnimator();
        animator.setDuration(4000);
        animator.setObjectValues(new PointF(0f,0f));
        //估值器
        animator.setEvaluator(new TypeEvaluator() {
            @Override
            public Object evaluate(float fraction, Object startValue, Object endValue) {
                //fraction是执行百分比
                PointF pointF = new PointF();
                pointF.x = 100f*fraction*4;
                pointF.y = 0.5f*9.8f*fraction*4*fraction*4;
                return pointF;
            }
        });
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
               PointF pointF= (PointF) animation.getAnimatedValue();
               view.setX(pointF.x);
               view.setY(pointF.y);
            }
        });
        animator.start();

        //加速器
//         AccelerateInterpolator()//逐渐加速
//         DecelerateInterpolator()//逐渐减速
//         AccelerateDecelerateInterpolator()//先加速再减速
//         AnticipateInterpolator()//初始回弹
//         OvershootInterpolator()//结束回弹
//         AnticipateOvershootInterpolator()//既有开始回弹，又有结束回弹
//         CycleInterpolator(4)//来回弹跳
//         BounceInterpolator()//阻尼效果（皮球弹跳）
    }
}
