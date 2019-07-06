package com.xn.materialdesignpro1.snackbar;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.xn.materialdesignpro1.R;

/**
 * 自定义吐司
 * snackbar介于吐司和dialog之间不影响用户操作
 *
 */
public class FifthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);
    }

    public void showToast(View view) {
        Toast result = new Toast(this);

        LayoutInflater inflate = (LayoutInflater)
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflate.inflate(R.layout.custom_toast, null);

        result.setView(v);
        result.setDuration(Toast.LENGTH_SHORT);
        result.show();
    }

    public void showSnackBar(View view) {
        Snackbar snackbar=Snackbar.make(view,"确定开启加速吗？",Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast(null);
            }
        });
        snackbar.show();
    }
}
