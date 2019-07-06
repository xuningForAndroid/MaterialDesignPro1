package com.xn.materialdesignpro1.bessel_curve;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class PathMeasureSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new SearchView(this));
//        setContentView(R.layout.activity_path_measure_search);
    }
}
