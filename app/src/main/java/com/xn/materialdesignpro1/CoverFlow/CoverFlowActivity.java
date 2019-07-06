package com.xn.materialdesignpro1.CoverFlow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.xn.materialdesignpro1.R;

public class CoverFlowActivity extends AppCompatActivity implements MyCoverAdapter.onItemClick {

    private CoverflowRecyclerView recyclerView;
    private int[] mColors={
            R.drawable.img_3,

            R.drawable.avft_active
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cover_flow);
        recyclerView = findViewById(R.id.coverRecycleView);
        recyclerView.setAlphaItem(false);
        MyCoverAdapter adapter = new MyCoverAdapter(mColors, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setOnSelectedListener(new CoverFlowManager.onItemSelectedListener() {
            @Override
            public void onSelect(int pos) {
                Toast.makeText(CoverFlowActivity.this, "pos"+pos%mColors.length, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void clickItem(int pos) {
        recyclerView.smoothScrollToPosition(pos);
    }
}
