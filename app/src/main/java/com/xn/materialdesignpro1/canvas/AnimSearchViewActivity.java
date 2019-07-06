package com.xn.materialdesignpro1.canvas;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.xn.materialdesignpro1.R;

public class AnimSearchViewActivity extends Activity {

    private AnimSearchView searchView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anim_search);
        searchView = findViewById(R.id.searchView);
        searchView.setController(new BigMirrorController());
    }

    public void startAnim(View view) {
        searchView.startAnim();

    }

    public void resetAnim(View view) {
        searchView.resetAnim();
    }
}
