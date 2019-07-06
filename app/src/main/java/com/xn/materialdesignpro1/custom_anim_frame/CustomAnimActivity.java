package com.xn.materialdesignpro1.custom_anim_frame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.xn.materialdesignpro1.R;

/**
 * 自定义动画框架
 * 实现思路：
 * 想要让布局在滑动过程执行布局中子view的动画，那么需要给子view动态设置动画，需要将外层布局与内部子view滑动关联起来
 * 自定义外层view，动态给每个子view包裹一层衣服，使得本来作用在这层衣服上的属性作用在他的子view上
 * 难点：在哪块插一脚，思路看源码，可以在addView中插一脚，获取每个子view，看有没有设置特殊属性，如果设置了，那么将该属性保留
 * 设置给子view，然后将子view设置给自己的衣服，然后将这层衣服作为view加入外层布局。
 * 难点，通过源码可以知道，在调用addView之前，需要先调用generateLayoutParams(attr)方法，那么我们需要获取属性的话就要重写这个
 * 方法，自定义params,获取我们的属性，然后在addView中转换为我们的params，进而获取属性，进而加入我们这层衣服上，再加入外层布局
 *
 *
 */
public class CustomAnimActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_anim);
    }
}
