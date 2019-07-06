package com.xn.materialdesignpro1.custom_anim_frame2;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

import com.xn.materialdesignpro1.R;

public class CustomAnim2Activity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_custom_anim2);
		
		ParallaxContainer container =  findViewById(R.id.parallaxContainer);
		container.setUp(R.layout.view_intro_1,
                R.layout.view_intro_2,
                R.layout.view_intro_3,
                R.layout.view_intro_4,
                R.layout.view_intro_5,
                R.layout.view_login);
		
		//设置动画
		ImageView iv_man = findViewById(R.id.iv_man);
		iv_man.setBackgroundResource(R.drawable.walk_woman_anim);
		container.setIv_man(iv_man);
		
	}


}
