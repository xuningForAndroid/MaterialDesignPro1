package com.xn.materialdesignpro1.navigationview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.xn.materialdesignpro1.R;

/**
 * navigationView
 * 用来规范侧滑基本样式
 * drawerLayout+navigationView结合使用
 */
public class ForthActivity extends AppCompatActivity {

    private NavigationView navigationView;
    private Menu navigationViewMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forth);
        navigationView= findViewById(R.id.navigationView);
        navigationViewMenu = navigationView.getMenu();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_about:
                        Toast.makeText(ForthActivity.this, "action_about", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.action_detail:

                        break;
                    case R.id.action_gallery:

                        break;
                    case R.id.action_other:

                        break;
                    case R.id.submenu1:
                        Toast.makeText(ForthActivity.this, "submanu1", Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.submenu2:

                        break;
                }
                return true;
            }
        });
    }
}
