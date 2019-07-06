package com.xn.materialdesignpro1.custom_view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.xn.materialdesignpro1.R;

import java.util.ArrayList;
import java.util.List;

public class PieChartActivity extends AppCompatActivity {

    private List<PieData> pieDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);
        PieChartView pie= findViewById(R.id.piechart);
        initData();
        pie.setData(pieDatas);
        pie.setStartAngle(16);
    }

    private void initData() {
        pieDatas = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            PieData aaa = null;
            switch (i){
                case 0:
                    aaa=new PieData("aaa", 10);
                    break;
                case 1:
                     aaa = new PieData("aaa", 20);
                    break;
                case 2:
                     aaa = new PieData("aaa", 30);
                    break;
                case 3:
                     aaa = new PieData("aaa", 40);
                    break;
                case 4:
                     aaa = new PieData("aaa", 50);
                    break;
            }
            pieDatas.add(aaa);
        }
    }
}
