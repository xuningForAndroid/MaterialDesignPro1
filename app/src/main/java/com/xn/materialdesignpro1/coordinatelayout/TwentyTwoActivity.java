package com.xn.materialdesignpro1.coordinatelayout;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.xn.materialdesignpro1.R;

/**v7包能兼容到3.0
 * behavior
 *桥梁，处理包裹在里面的所有子控件的联动效果
 *  1、某个view需要监听另一个view的状态（位置、大小、显示）CustomBehavior
 *          需要重写：layoutdependsOn()  ondepentviewchanged()
 *  2、某个view需要监听CoordinateLayout里面所有的滑动状态(见TwentyThreeActivity)
 *          需要重写：onStartNestedScroll  onNestedScroll或onNestedPreScroll
 *          注意：能被CoordinateLayout捕获到的滑动状态的控件只有：RecyclerView、NestedScrollView、ViewPager
 */
public class TwentyTwoActivity extends AppCompatActivity {

    private boolean flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twenty_two);
        findViewById(R.id.tv1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag){
                    ViewCompat.offsetTopAndBottom(v,-100);
                }else {
                    ViewCompat.offsetTopAndBottom(v,100);
                }
                flag=!flag;
            }
        });
    }
}
