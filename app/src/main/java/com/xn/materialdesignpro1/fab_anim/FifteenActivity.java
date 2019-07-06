package com.xn.materialdesignpro1.fab_anim;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.xn.materialdesignpro1.R;

import java.util.ArrayList;
import java.util.List;

/**
 * fab与toolbar的属性动画
 */
public class FifteenActivity extends AppCompatActivity implements FabShowHideListener {

    private ImageButton fab;
    private Toolbar toolbar;
    private List<String> dataList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#509dee"));
        setContentView(R.layout.activity_fifteen);
        toolbar = findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);
        fab = findViewById(R.id.fab);
      RecyclerView recyclerView= findViewById(R.id.recycleView);
      recyclerView.setLayoutManager(new LinearLayoutManager(this));
        for (int i = 0; i < 20; i++) {
            dataList.add("wiakkaks");
        }
      recyclerView.setAdapter(new FabRecyclerAdapter(dataList));
      recyclerView.addOnScrollListener(new FabScrollListener(this));
    }

    @Override
    public void onShow() {
        // 显示动画--属性动画
        toolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(3));

        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) fab.getLayoutParams();
        fab.animate().translationY(0).setInterpolator(new DecelerateInterpolator(3));
    }

    @Override
    public void onHide() {
        toolbar.animate().translationY(-toolbar.getHeight()).setInterpolator(new AccelerateInterpolator(3));
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) fab.getLayoutParams();
        fab.animate().translationY(fab.getHeight()+layoutParams.bottomMargin).setInterpolator(new AccelerateInterpolator(3));
    }
}
