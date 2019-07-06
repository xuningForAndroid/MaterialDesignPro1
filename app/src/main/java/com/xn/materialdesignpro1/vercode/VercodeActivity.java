package com.xn.materialdesignpro1.vercode;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.xn.materialdesignpro1.R;

public class VercodeActivity extends AppCompatActivity implements VertificationAction.onVerficationCodeChangeListener {

    private VercodeEditText vercodeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vercode);
        vercodeView = findViewById(R.id.vercodeView);
        vercodeView.setOnVercodeChangedListener(this);
    }

    @Override
    public void onVerCodeChanged(CharSequence code, int start, int before, int count) {
        Log.i("xuning","code:"+code+",start:"+start+",before:"+before+",count:"+count);

    }

    @Override
    public void onInputCompleted(CharSequence code) {
        //请求接口
        Toast.makeText(this, "正在请求", Toast.LENGTH_SHORT).show();

    }
}
