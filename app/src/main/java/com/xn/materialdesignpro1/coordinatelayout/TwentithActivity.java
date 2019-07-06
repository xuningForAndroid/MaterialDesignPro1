package com.xn.materialdesignpro1.coordinatelayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.xn.materialdesignpro1.R;

/**
 * CoordinateLayout协调
 * TabLayout + viewPager+ fragment（布局为NestedScrollView或Recyclerview）
 *
 * 步骤：
 * 1、AppBarLayout嵌套Toolbar和TabLayout  toolbar app:layout_scrollFlags="scroll"
 * 2、ViewPager  app:layout_behavior="@string/appbar_scrolling_view_behavior"
 * 3、fragment布局  NestedScrollView  app:layout_behavior="@string/appbar_scrolling_view_behavior"
 *
 * 此处有坑：需要将TabLayout 放入AppBarLayout布局中，否则可能出现tab点击无效
 */
public class TwentithActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private String[] titles={"娱乐","体育","八卦","明星","汽车","房子","美女","美食"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getResources().getColor(R.color.navigationBarColor));
        setContentView(R.layout.activity_twentith);
        tabLayout = findViewById(R.id.tabLayout);

        final ViewPager viewPager=findViewById(R.id.viewPager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(adapter);
    }
    class ViewPagerAdapter extends FragmentPagerAdapter{

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public Fragment getItem(int position) {
            CoordinateFragment fragment = new CoordinateFragment();
            Bundle bundle = new Bundle();
            bundle.putString("title",titles[position]);
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getCount() {
            return titles.length;
        }
    }
}
