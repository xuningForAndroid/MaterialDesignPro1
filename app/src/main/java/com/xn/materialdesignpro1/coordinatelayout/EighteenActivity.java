package com.xn.materialdesignpro1.coordinatelayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xn.materialdesignpro1.R;
import com.xn.materialdesignpro1.fab_anim.FabRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 注意：CoordinateLayout中的behavior能协调的滑动控件包括RecyclerView、viewPager、NestedScrollView，其余控件不支持（listView、ScrollView。。。）
 *
 * 实现折叠toolbar需要步骤：
 * 1、recyclerView设置属性 app:layout_behavior="@string/appbar_scrolling_view_behavior"
 * 2、AppBarLayout嵌套Toolbar
 * 3、toolBar设置属性：app:layout_scrollFlags="scroll"
 *                          enterAlways快速返回(不会等到拉到第一个)
 *                          enterAlwaysCollapsed
 *                          exitUntilCollapsed
 *
 */
public class EighteenActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getResources().getColor(R.color.navigationBarColor));
        setContentView(R.layout.activity_eighteen);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<String> list=new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            list.add("item"+i);
        }
        recyclerView.setAdapter(new FabRecyclerAdapter(list));

    }
}
