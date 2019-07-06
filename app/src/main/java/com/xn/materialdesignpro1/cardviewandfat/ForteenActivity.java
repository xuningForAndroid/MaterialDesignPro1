package com.xn.materialdesignpro1.cardviewandfat;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.xn.materialdesignpro1.R;

/**
 * floatActionButton
 */
public class ForteenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forteen);
       FloatingActionButton fab= findViewById(R.id.fab);

    }
    boolean reverse=false;

    public void rotate(View view) {
        float todegree=reverse?90f:-90f;
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotation", 0.0f, todegree).setDuration(400);
        animator.start();
        reverse=!reverse;
    }
}
