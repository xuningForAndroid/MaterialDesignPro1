package com.xn.materialdesignpro1.itemtouchhelper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Toast;

import com.xn.materialdesignpro1.R;

import java.util.List;

public class SecondActivity extends AppCompatActivity
        implements StartDraggerListener,QQMessageAdapter.OnItemClickListener{

    private RecyclerView mRecyclerView;
    private ItemTouchHelper itemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mRecyclerView = findViewById(R.id.recycleView2);
        List<QQDataBean> datas = DataUtils.init();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        QQMessageAdapter adapter = new QQMessageAdapter(datas,this);
        mRecyclerView.setAdapter(adapter);

        ItemTouchHelper.Callback callback=new MyItemTouchHelperCallBack(adapter);
        itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);

        adapter.setOnItemClickListener(this);
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder holder) {
        itemTouchHelper.startDrag(holder);
    }

    @Override
    public void onItemClick(RecyclerView.ViewHolder holder,int position) {
        int adapterPosition = position;
        Toast.makeText(this, "position"+adapterPosition, Toast.LENGTH_SHORT).show();
    }
}
