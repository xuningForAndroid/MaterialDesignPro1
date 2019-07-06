package com.xn.materialdesignpro1.value_anim;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.xn.materialdesignpro1.R;

/**
 * Activity转场动画
 * 两个Activity进行跳转的时候出现的转场动画
 *
 */
public class Transition2Activity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //设置允许转场
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition2);

        //普通转场效果
//        Slide slide=new Slide();
//        slide.setDuration(100);
//        Explode explode=new Explode();
//        getWindow().setExitTransition(explode);
//        getWindow().setEnterTransition(explode);

       TransitionBean transitionBean= (TransitionBean) getIntent().getSerializableExtra("transitionBean");
      TextView tv_title= findViewById(R.id.tv_title);
      ImageView iv_content=findViewById(R.id.iv_content);
      iv_content.setImageResource(transitionBean.getRes());
      tv_title.setText(transitionBean.getTitle());
    }
    public void jumpBack(View view) {
        super.onBackPressed();//要的是返回的动画效果
    }
}
