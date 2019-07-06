package com.xn.materialdesignpro1.anim_behavior;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.xn.materialdesignpro1.R;
import com.xn.materialdesignpro1.fab_anim.FabRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * behavior实现fat的动画
 * CoordinatorLayout:协调调度子控件,实现动画效果
 * 可以设置view的behavior来实现触摸的动画调度
 */
public class SixteenActivity extends AppCompatActivity {

    private FloatingActionButton fab;
    private List<String> list=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#509de1"));
        setContentView(R.layout.activity_sixteen);
        fab = findViewById(R.id.fab);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        RecyclerView recyclerView= findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        for (int i = 0; i < 30; i++) {
            list.add("item------"+i);
        }
        recyclerView.setAdapter(new FabRecyclerAdapter(list));

    }
}
