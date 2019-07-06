package com.xn.materialdesignpro1.headerandfooter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xn.materialdesignpro1.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> datas=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyRecycleView mRecycleView= findViewById(R.id.recycleView);
        for (int i = 0; i < 30; i++) {
            datas.add("第 "+i+" 项内容");
        }
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        mRecycleView.setLayoutManager(linearLayoutManager);

        ViewGroup.LayoutParams params=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,200);
        ImageView imageView=new ImageView(this);
        imageView.setLayoutParams(params);
        imageView.setImageResource(R.mipmap.ic_launcher);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        mRecycleView.addHeaderView(imageView);

        ViewGroup.LayoutParams params1=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,80);
        TextView textView=new TextView(this);
        textView.setLayoutParams(params1);
        textView.setText("到底部了");
        textView.setGravity(Gravity.CENTER);
        mRecycleView.addFooterView(textView);

        MyRecyclerAdapter adapter=new MyRecyclerAdapter(datas);
        mRecycleView.setAdapter(adapter);


    }
}
