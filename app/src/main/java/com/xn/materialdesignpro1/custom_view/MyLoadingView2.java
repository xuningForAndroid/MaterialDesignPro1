package com.xn.materialdesignpro1.custom_view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xn.materialdesignpro1.R;

public class MyLoadingView2 extends LinearLayout {

    private TextView tost_text;

    public MyLoadingView2(Context context) {
        super(context);
        initView();
    }


    public MyLoadingView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MyLoadingView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }
    private void initView() {
        View.inflate(getContext(), R.layout.view_loading,this);
        tost_text = findViewById(R.id.tost_text);

    }

    public void setToastText(String text){
        tost_text.setText(text);
    }


}
