package com.xn.materialdesignpro1.banner;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.xn.materialdesignpro1.R;

public class MyBannerActivity extends AppCompatActivity implements OnSelectListener {

    private MyCustomBanner banner;
    private int[] resId={R.drawable.icon2,R.drawable.avatar,R.drawable.img_3};
    private View indicator2;
    private View indicator3;
    private View indicator1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_banner);
        initIndicator();
        banner = findViewById(R.id.myBanner);
        MyPagerAdapter pagerAdapter = new MyPagerAdapter(resId);
        banner.setAdapter(pagerAdapter);
        banner.setOnSelectListener(this);
        banner.setShowTimes(2000L);
        banner.setDirection(Direction.left);

        banner.startLoop();
        banner.setCurrentItem(20001);
    }

    private void initIndicator() {
        indicator1 = findViewById(R.id.indicator1);
        indicator2 = findViewById(R.id.indicator2);
        indicator3 = findViewById(R.id.indicator3);
    }

    @Override
    public void onSelect(int position) {
        switch (position){
            case 0:
                indicator1.setBackgroundColor(Color.parseColor("#905de1"));
                indicator2.setBackgroundColor(Color.parseColor("#905d23"));
                indicator3.setBackgroundColor(Color.parseColor("#905d23"));
                break;
            case 1:
                indicator1.setBackgroundColor(Color.parseColor("#905d23"));
                indicator2.setBackgroundColor(Color.parseColor("#905de1"));
                indicator3.setBackgroundColor(Color.parseColor("#905d23"));
                break;
            case 2:
                indicator1.setBackgroundColor(Color.parseColor("#905d23"));
                indicator2.setBackgroundColor(Color.parseColor("#905d23"));
                indicator3.setBackgroundColor(Color.parseColor("#905de1"));
                break;
        }
    }

    class MyPagerAdapter extends PagerAdapter{
        public int[] resId;

        public MyPagerAdapter(int[] resIds){
            this.resId=resIds;
        }
        @Override
        public int getCount() {

            return this.resId==null?0:Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view==object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            position=position % resId.length;
            int res = resId[position];
            ImageView imageView = new ImageView(MyBannerActivity.this);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            imageView.setImageResource(res);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        banner.stopLoop();
    }
}
