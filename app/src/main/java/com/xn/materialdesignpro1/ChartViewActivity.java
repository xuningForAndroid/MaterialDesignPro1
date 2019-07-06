package com.xn.materialdesignpro1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChartViewActivity extends AppCompatActivity {
    //x轴坐标对应的数据
    private List<String> xValue = new ArrayList<>();
    //y轴坐标对应的数据
    private List<Integer> yValue = new ArrayList<>();
    //折线对应的数据
    private Map<String, Integer> value = new HashMap<>();

    private List<Integer> colors=new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_view);
        for (int i = 0; i < 12; i++) {
            xValue.add((i + 1) + "月");
            value.put((i + 1) + "月", (int) (Math.random() * 181 + 60));//60--240
        }

        for (int i = 0; i < 6; i++) {
            yValue.add(i * 60);
        }

        ChartView chartView = (ChartView) findViewById(R.id.chartview);
        chartView.setValue(value, xValue, yValue);
        chartView.setDivideValue(80,200);


        colors.add(0xFF19B95D);
        colors.add(0xFFF05522);
        colors.add(0xFFF05522);
        colors.add(0xFFF59805);
        colors.add(0xFFF59805);
        colors.add(0xFF19B95D);
        colors.add(0xFFF59805);
        colors.add(0xFF19B95D);
        colors.add(0xFFF05522);
        colors.add(0xFFF59805);
        colors.add(0xFF19B95D);
        chartView.setLinesColors(colors);
    }
}
