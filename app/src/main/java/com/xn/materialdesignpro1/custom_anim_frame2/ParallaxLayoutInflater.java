package com.xn.materialdesignpro1.custom_anim_frame2;


import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.xn.materialdesignpro1.R;

public class ParallaxLayoutInflater extends LayoutInflater {

	private ParallaxFragment fragment;
	
	protected ParallaxLayoutInflater(LayoutInflater original, Context newContext,ParallaxFragment fragment) {
		super(original, newContext);
		this.fragment = fragment;
		//重新设置布局加载器的工厂
		//工厂：创建布局文件中所有的视图
		setFactory(new ParallaxFactory(this,getFactory()));
	}

	@Override
	public LayoutInflater cloneInContext(Context newContext) {
		return new ParallaxLayoutInflater(this,newContext,fragment);
	}

	class ParallaxFactory implements Factory{

		private final Factory factory;
		private LayoutInflater inflater;
		private final String[] sClassPrefix = {
				"android.widget.",
				"android.webkit.",
				"android.view."
		};
		public ParallaxFactory(LayoutInflater inflater,Factory factory) {
			this.inflater = inflater;
			this.factory=factory;
		}

		//自定义,视图创建的过程
		@Override
		public View onCreateView(String name, Context context,
				AttributeSet attrs) {
			View view = null;

			if (context instanceof LayoutInflater.Factory) {
				view = ((LayoutInflater.Factory) context).onCreateView(name, context, attrs);
			}

			if (factory != null && view == null) {
				view = factory.onCreateView(name, context, attrs);
			}

			if (view == null) {
				view = createViewOrFailQuietly(name,context,attrs);
			}
			
			//实例化完成
			if (view != null) {
				//获取自定义属性，通过标签关联到视图上
				setViewTag(view,context,attrs);
				fragment.getParallaxViews().add(view);
				Log.d("ricky", "view:"+view);
			}
			
			return view;
		}
		
		private void setViewTag(View view, Context context, AttributeSet attrs) {
			//所有自定义的属性
			int[] attrIds = {
					R.attr.a_in,
					R.attr.a_out,
					R.attr.x_in,
					R.attr.x_out,
					R.attr.y_in,
					R.attr.y_out};
			
			//获取
			TypedArray a = context.obtainStyledAttributes(attrs, attrIds);
			if (a != null && a.length() > 0) {
				//获取自定义属性的值
				ParallaxViewTag tag = new ParallaxViewTag();
				tag.alphaIn = a.getFloat(0, 0f);
				tag.alphaOut = a.getFloat(1, 0f);
				tag.xIn = a.getFloat(2, 0f);
				tag.xOut = a.getFloat(3, 0f);
				tag.yIn = a.getFloat(4, 0f);
				tag.yOut = a.getFloat(5, 0f);
				
				//index
				view.setTag(R.id.parallax_view_tag,tag);
			}
			
			a.recycle();
			
		}

		private View createViewOrFailQuietly(String name, String prefix,Context context,
				AttributeSet attrs) {
			try {
				//通过系统的inflater创建视图，读取系统的属性
				return inflater.createView(name, prefix, attrs);
			} catch (Exception e) {
				return null;
			}
		}

		private View createViewOrFailQuietly(String name, Context context,
				AttributeSet attrs) {
			//1.自定义控件标签名称带点，所以创建时不需要前缀
			if (name.contains(".")) {
				createViewOrFailQuietly(name, null, context, attrs);
			}
			//2.系统视图需要加上前缀
			for (String prefix : sClassPrefix) {
				View view = createViewOrFailQuietly(name, prefix, context, attrs);
				if (view != null) {
					return view;
				}
			}
			
			return null;
		}
		
	}
	
}
