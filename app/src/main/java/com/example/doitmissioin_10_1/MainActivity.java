package com.example.doitmissioin_10_1;

import android.os.Bundle;
import android.view.Menu;

import com.example.doitmissioin_10_1.ui.moivelist.MovieListFragment;
import com.example.doitmissioin_10_1.ui.reservation.ReservationFragment;
import com.example.doitmissioin_10_1.ui.slideshow.SlideshowFragment;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doitmissioin_10_1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    MovieListFragment movieListFragment;
    ReservationFragment reservationFragment;
    SlideshowFragment slideshowFragment;

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_movie_list, R.id.nav_reservation, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        
        // Navigation Component 설정
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        setSupportActionBar(binding.appBarMain.toolbar);
        //navigationView.setNavigationItemSelectedListener(this); 초기 프래그먼트 생성 코드가 있기 때문에, 이걸 쓰면 프래그먼트가 중복 생성됨.

        movieListFragment = new MovieListFragment();
        reservationFragment = new ReservationFragment();
        slideshowFragment = new SlideshowFragment();

        // ActionBarDrawerToggle 설정 (GPT)
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, binding.drawerLayout, binding.appBarMain.toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );

        // DrawerLayout에 토글 설정 (GPT)
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}