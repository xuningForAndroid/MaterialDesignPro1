package com.xn.materialdesignpro1.coordinatelayout;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xn.materialdesignpro1.R;
import com.xn.materialdesignpro1.fab_anim.FabRecyclerAdapter;

import java.util.ArrayList;

/**
 * CollapsingToolBarLayout:实现带折叠效果的ToolBar
 * 包裹ToolBar，
 *
 * 注意：AppbarLayout包裹CollapsingToolbarLayout，里边再包裹ToolBar,AppbarLayout高度设置为固定值（260dp）,必须要比ToolBar高
 * CollapsingToolbarLayout设置为match_parent,Toolbar设置自己的高度
 * AppbarLayout只对他里面的直接子控件起作用
 *
 *  app:layout_collapseMode="parallax|none|pin"
 *  parallax：折叠会有视差效果
 *  none：无效果,上滑时，toolbar会固定并被推出去
 *  pin:  固定模式，折叠之后先将title与ToolBar融合，再推出去
 *
 *  app:layout_collapseMode="parallax"
    app:layout_collapseParallaxMultiplier="0.7"视差倍数

 layout_scrollFlags的属性解释：
    scroll: 所有想滚动出屏幕的view都需要设置这个flag- 没有设置这个flag的view将被固定在屏幕顶部
    enterAlways:这个flag让任意向下的滚动都会导致该view变为可见，启用快速“返回模式”。
    enterAlwayscollapse:当你的视图已经设置minHeight属性又使用此标志时，你的视图只能已最小高度进入，只有当滚动视图到达顶部时才扩大到完整高度。
    exitUntilCollapsed: 滚动退出屏幕，最后折叠在顶端。
    snap:


 给CollapsingToolbarLayout设置属性  app:layout_scrollFlags="scroll|exitUntilCollapsed",会使得ToolBar不会推出屏幕

 *
 */
public class TwentyFirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twenty_first);
        CollapsingToolbarLayout collapsingToolbarLayout= findViewById(R.id.collapsing_toolbar);
        if (collapsingToolbarLayout!=null){
            //设置图片隐藏时ToolBar的颜色
            collapsingToolbarLayout.setContentScrimColor(Color.parseColor("#509de1"));
            //设置工具栏标题
            collapsingToolbarLayout.setTitle("折叠效果");
            collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
            collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        }
       RecyclerView recyclerView= findViewById(R.id.recyclerView);
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add("item"+i);
        }
        FabRecyclerAdapter adapter = new FabRecyclerAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }
}
