package com.xn.materialdesignpro1.searchview;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.xn.materialdesignpro1.R;

public class SixthActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sixth);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search,menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        //直接呈现搜索框
//        searchView.setIconified(false);//是否隐藏，设置之后默认显示搜索框
//        searchView.setIconifiedByDefault(false);//默认显示搜索框，并且不能被隐藏，没有×

        //需要实现自定义扩展效果
        ImageView goBtn= searchView.findViewById(R.id.search_go_btn);
//        goBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(SixthActivity.this, "点击了提交", Toast.LENGTH_SHORT).show();
//
//            }
//        });
        //设置提交按钮是否可见可用
        searchView.setSubmitButtonEnabled(true);
        //点搜索按钮
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SixthActivity.this, "点搜索了", Toast.LENGTH_SHORT).show();
            }
        });
        //关闭时调用
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                Toast.makeText(SixthActivity.this, "关闭了", Toast.LENGTH_SHORT).show();
                //return true;不再相应原来的默认操作
                return false;
            }
        });
        //查询时调用
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //文本提交调用
                Toast.makeText(SixthActivity.this, "文本提交"+query, Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //文本改变时调用
                Toast.makeText(SixthActivity.this, "文本改变"+newText, Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        SearchView.SearchAutoComplete et=searchView.findViewById(R.id.search_src_text);
        et.setHint("请输入要搜索的内容");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
