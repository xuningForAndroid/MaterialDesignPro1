package com.xn.materialdesignpro1.paint;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.xn.materialdesignpro1.R;

public class RaDarActivity extends AppCompatActivity {

    private RadarScanView radarScanView;
    private int progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ra_dar);
        radarScanView = findViewById(R.id.radar);
        radarScanView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (progress <=Integer.MAX_VALUE){
                            progress+=5;
                            radarScanView.setProgress(progress);
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();

            }
        });
    }
}
