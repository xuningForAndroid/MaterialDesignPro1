package com.xn.materialdesignpro1.event_conflict;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class MyListView extends ListView {
    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = MeasureSpec.makeMeasureSpec(380, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, height);
    }
}
