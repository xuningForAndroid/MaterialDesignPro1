package com.xn.materialdesignpro1.custom_view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.xn.materialdesignpro1.R;

import java.util.ArrayList;
import java.util.List;

public class StepViewActivity extends AppCompatActivity {

    private StepView stepView;
    private int steps;
    private List<String> titles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_view);
        stepView = findViewById(R.id.stepView);
        titles=new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            if (i==0){
                titles.add("身份验证");
            }else if (i==1){
                titles.add("更换手机号");
            }else if (i==2){
                titles.add("更换密码");
            }else if (i==3){
                titles.add("完成");
            }

        }
        stepView.setTitles(titles);
        steps = stepView.getSteps();

    }

    public void nextStep(View view) {
        if (stepView.getCurrentStep()< steps)
            stepView.nextStep();
        else
            Toast.makeText(this, "已经完成", Toast.LENGTH_SHORT).show();
    }
}
