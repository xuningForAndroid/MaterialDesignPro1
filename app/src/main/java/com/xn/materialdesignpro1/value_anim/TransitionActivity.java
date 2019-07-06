package com.xn.materialdesignpro1.value_anim;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;

import com.xn.materialdesignpro1.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity转场动画
 * 两个Activity进行跳转的时候出现的转场动画
 * 之前的做法：
 * //        startActivity(new Intent(this,Transition2Activity.class));
 //        //简单的转场动画
 //        overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
 *
 * 转场动画分类：1.共享元素转换，2.普通转换
 * 共享元素转换实现步骤：
 *      1.使用转换动画的前提：需要设置Activity，允许使用转场动画
 *                  getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
 *      2.然后设置共享元素：（需要转场的view设置属性）注意：两边都要设置
 *                  android:transitionName="iv_transition"
 *      3.代码中利用工具类：ActivityOptionsCompat 进行设置（可以设置单个view，也可以设置多个view）
 *      其中Pair.create(iv,"iv_transition")表示的是view对应的transitionName
         ActivityOptionsCompat optionsCompat=ActivityOptionsCompat.
         makeSceneTransitionAnimation(this, Pair.create(iv,"iv_transition"),Pair.create(tv,"tv_transition"));
         Bundle bundle = optionsCompat.toBundle();

 普通转场动画实现：滑动效果（Slide）、展开效果（Explode）、渐变显示隐藏效果（Fade）
    两个Activity都这样写：
         Slide slide=new Slide();
         slide.setDuration(100);
         Fade fade=new Fade();
         Explode explode=new Explode();
         getWindow().setExitTransition(explode);
         getWindow().setEnterTransition(explode);

 * demo实现recycleView条目转场动画在TransitionActivity与Transition2Activity之间
 */
public class TransitionActivity extends AppCompatActivity implements TransitionAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private List<TransitionBean> dataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //设置有转场动画
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);
        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        initData();
        TransitionAdapter transitionAdapter = new TransitionAdapter(dataList, this);
        transitionAdapter.setItemClickListener(this);
        recyclerView.setAdapter(transitionAdapter);

    }

    private void initData() {
        dataList=new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            TransitionBean transitionBean = new TransitionBean();
            switch (i%4){
                case 0:
                    transitionBean.setRes(R.drawable.meinv3);
                    transitionBean.setTitle("美女");
                    break;
                case 1:
                    transitionBean.setRes(R.drawable.tulips2);
                    transitionBean.setTitle("鲜花");
                    break;
                case 2:
                    transitionBean.setRes(R.drawable.pg2_2);
                    transitionBean.setTitle("电话");
                    break;
                case 3:
                    transitionBean.setRes(R.drawable.pg1_1);
                    transitionBean.setTitle("背景");
                    break;
            }
            dataList.add(transitionBean);
        }
    }


    @Override
    public void onItemClick(int position, View iv, View tv) {
//      1、 共享元素实现方法： 实现两个view的转场动画
        ActivityOptionsCompat optionsCompat=ActivityOptionsCompat.
                makeSceneTransitionAnimation(this, Pair.create(iv,"iv_transition"),Pair.create(tv,"tv_transition"));
        Bundle bundle = optionsCompat.toBundle();
        Intent intent = new Intent(this, Transition2Activity.class);
        intent.putExtra("transitionBean",dataList.get(position));
        startActivity(intent,bundle);

//        2、普通方法实现转场
////        Slide slide=new Slide();
////        slide.setDuration(100);
////        Fade fade=new Fade();
//        Explode explode=new Explode();
//        getWindow().setExitTransition(explode);
//        getWindow().setEnterTransition(explode);
//        ActivityOptionsCompat optionsCompat=ActivityOptionsCompat.makeSceneTransitionAnimation(this);
//        Bundle bundle = optionsCompat.toBundle();
//        Intent intent = new Intent(this, Transition2Activity.class);
//        intent.putExtra("transitionBean",dataList.get(position));
//        startActivity(intent,bundle);
    }


}
