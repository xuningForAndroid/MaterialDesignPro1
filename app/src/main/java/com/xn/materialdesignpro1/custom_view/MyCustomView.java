package com.xn.materialdesignpro1.custom_view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class MyCustomView extends ViewGroup {
    private int OFFSET=80;
    private Paint paint;

    public MyCustomView(Context context) {
        super(context);
    }

    public MyCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.GREEN);
//        setWillNotDraw(false);//调用这个方法后，则会绘制自身
    }

//    @Override
//    protected void onDraw(Canvas canvas) {
//        //默认情况下，不会调用这个方法，因为viewgroup默认调用了setWillNotDraw（true）,也就是不画自身,但是可以自身调用这个方法来实现绘制
//        super.onDraw(canvas);
//        canvas.drawCircle(getWidth()/2,getHeight()/2,getWidth()/2,paint);
//    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int height=0;
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);

            int left=i*OFFSET;
            int top=height;
            int right=i*OFFSET+child.getMeasuredWidth();
            int bottom=height+child.getMeasuredHeight();
            child.layout(left,top,right,bottom);

            height+= child.getMeasuredHeight();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int measuredWidth=0;
        int measuredHeight=0;

        //1.测量自己的尺寸
//        viewgroup.onmeasure();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            //这一句话顶替下边的这些
            //方法1：
//            measureChild(child,widthMeasureSpec,heightMeasureSpec);
            //方法2：
            LayoutParams childLayoutParams = child.getLayoutParams();
            int childWidth = childLayoutParams.width;
            int childHeight = childLayoutParams.height;
            //为每一个子view计算测量规格信息
            int childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec, 0, childWidth);
            int childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec, 0, childHeight);
            //子view测量自己的尺寸
            child.measure(childWidthMeasureSpec,childHeightMeasureSpec);
        }
        //子view测量完后，viewGroup就可以拿到这个子view的尺寸
             switch (widthMode){
                case MeasureSpec.EXACTLY:
                    measuredWidth=widthSize;
                break;
                case MeasureSpec.AT_MOST:
                    case MeasureSpec.UNSPECIFIED:
                    for (int i = 0; i < childCount; i++) {
                        View child = getChildAt(i);
                        int widthAndOffset=i*OFFSET +child.getMeasuredWidth();
                        measuredWidth= Math.max(widthAndOffset,measuredWidth);
                    }
                    break;
                    default:
                        break;

            }

        switch (heightMode){
            case MeasureSpec.EXACTLY:
                measuredHeight=heightSize;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                for (int i = 0; i < childCount; i++) {
                    View child = getChildAt(i);
                    int height=i*child.getMeasuredHeight()+child.getMeasuredHeight();
                    measuredWidth= height;
                }
                break;
            default:
                break;

        }
//        2.保存自己的尺寸
        setMeasuredDimension(measuredWidth,measuredHeight);
    }


}
