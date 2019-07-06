package com.xn.materialdesignpro1.canvas;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.xn.materialdesignpro1.R;

public class CanvasActivity extends AppCompatActivity {
    private int level=5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //canvas高级使用
//        setContentView(new MyCanvasView(this));
        //canvas的恢复与保存
//        setContentView(new CanvasRestoreView(this));
        final ImageView imageView = new ImageView(this);
        Drawable unselected=getResources().getDrawable(R.drawable.avft);
        Drawable selected=getResources().getDrawable(R.drawable.avft_active);
        imageView.setImageDrawable(new RevealDrawable(unselected,selected));
        setContentView(imageView);
        imageView.getDrawable().setLevel(5000);
        imageView.setClickable(true);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                level-=200;
                if (level <0)
                    level=5000;
                imageView.getDrawable().setLevel(level);
            }
        });
    }
}
