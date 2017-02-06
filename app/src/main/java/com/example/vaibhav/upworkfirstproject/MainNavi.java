package com.example.vaibhav.upworkfirstproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vaibhav.upworkfirstproject.Fragment.BottomNavigation.Home;
import com.example.vaibhav.upworkfirstproject.Fragment.BottomNavigation.Message;
import com.example.vaibhav.upworkfirstproject.Fragment.BottomNavigation.Profile;
import com.example.vaibhav.upworkfirstproject.Fragment.Navigation.TeacherData;

public class MainNavi extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_navi);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
//        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        bottomNavigationView=(BottomNavigationView) findViewById(R.id.navi_bottom);
        setBottomNavigationView();

//        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setBottomNavigationView(){
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                Fragment fragment=null;
                // Handle navigation view item clicks here.
                int id = item.getItemId();
                switch (id){
                    case R.id.menu_home:
                        fragment=new Home();
                        break;
                    case R.id.menu_message:
                        fragment=new Message();
                        break;
                    case R.id.menu_profile:
                        fragment=new Profile();
                        break;
                }
                fragmentTransaction.replace(R.id.content_main_navi,fragment).commit();
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_navi, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id){
            case R.id.menu_cs:
                startActivity(new Intent(MainNavi.this, TeacherData.class).putExtra("start","0").putExtra("end","1"));
                break;
            case R.id.menu_art:
                startActivity(new Intent(MainNavi.this, TeacherData.class).putExtra("start","8").putExtra("end","9"));
                break;
            case R.id.menu_bui:
                startActivity(new Intent(MainNavi.this, TeacherData.class).putExtra("start","2").putExtra("end","3"));
                break;
            case R.id.menu_health:
                startActivity(new Intent(MainNavi.this, TeacherData.class).putExtra("start","6").putExtra("end","7"));
                break;
            case R.id.menu_media:
                startActivity(new Intent(MainNavi.this, TeacherData.class).putExtra("start","12").putExtra("end","13"));
                break;
            case R.id.menu_law:
                startActivity(new Intent(MainNavi.this, TeacherData.class).putExtra("start","4").putExtra("end","5"));
                break;
            case R.id.menu_phy:
                startActivity(new Intent(MainNavi.this, TeacherData.class).putExtra("start","10").putExtra("end","11"));
                break;
            default:
                Toast.makeText(getApplicationContext(),"Wrong Input",Toast.LENGTH_LONG).show();

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        FragmentTransaction fragmentTransaction=this.getSupportFragmentManager().beginTransaction();
        Fragment fragment=new Home();
        fragmentTransaction.replace(R.id.content_main_navi,fragment).commit();

    }


}
