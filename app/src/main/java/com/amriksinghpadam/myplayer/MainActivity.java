package com.amriksinghpadam.myplayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.internal.NavigationMenuView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private NavigationView navigationBar;
    private DrawerLayout drawerLayout;
    int backFlag = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(toolbar);

        toolbar = findViewById(R.id.mainToolBar);
        navigationBar = findViewById(R.id.mainNavBar);
        drawerLayout = findViewById(R.id.drawerId);
        toolbar.setTitle("My Player");
        toolbar.setTitleTextColor(getResources().getColor(R.color.whiteColor));
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawerLayout,toolbar,R.string.opoen_drawer,R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        Window window = getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.statusbar));

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        MainFragment fragMain = new MainFragment(this);
        ft.add(R.id.floatingLayoutId,fragMain);
        ft.commit();

        navigationBar.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Intent intent = new Intent(MainActivity.this,CommonPlayerGridView.class);
                Bundle bundle = new Bundle();
                switch (menuItem.getItemId()){
                    case R.id.nav_feature_id:

                        drawerLayout.closeDrawer(GravityCompat.START);
                        bundle.putString("title","Featured Artists");
                        intent.putExtras(bundle);
                        startActivity(intent);
                    break;
                    case R.id.nav_latest_id:

                        drawerLayout.closeDrawer(GravityCompat.START);
                        bundle.putString("title","Latest Songs");
                        intent.putExtras(bundle);
                        startActivity(intent);
                    break;
                    case R.id.nav_discover_id:

                        drawerLayout.closeDrawer(GravityCompat.START);
                        bundle.putString("title","Discover");
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case R.id.nav_most_watch_id:

                        drawerLayout.closeDrawer(GravityCompat.START);
                        bundle.putString("title","Most Watched");
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case R.id.nav_new_video_id:

                        drawerLayout.closeDrawer(GravityCompat.START);
                        bundle.putString("title","New Video");
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case R.id.nav_indian_video_id:

                        drawerLayout.closeDrawer(GravityCompat.START);
                        bundle.putString("title","Hindi & Punjabi");
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case R.id.nav_englishi_video_id:

                        drawerLayout.closeDrawer(GravityCompat.START);
                        bundle.putString("title","English Video");
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    default:
                        drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                }
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        backFlag=0;
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            if(backFlag==1){
                super.onBackPressed();
            }else{
                backFlag++;
                Toast.makeText(MainActivity.this,"Press Back Again",Toast.LENGTH_SHORT).show();
            }

        }
    }
}
