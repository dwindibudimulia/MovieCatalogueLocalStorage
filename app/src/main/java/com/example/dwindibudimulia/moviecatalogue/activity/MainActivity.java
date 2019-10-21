package com.example.dwindibudimulia.moviecatalogue.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.dwindibudimulia.moviecatalogue.R;
import com.example.dwindibudimulia.moviecatalogue.adapter.ViewPagerAdapter;
import com.example.dwindibudimulia.moviecatalogue.fragment.MovieFragment;
import com.example.dwindibudimulia.moviecatalogue.fragment.TvShowFragment;
import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity {
    private static Context context;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    public static Context getContext() {
        return context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.viewpager);

        final ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.AddFragment(new MovieFragment(), getResources().getString(R.string.tab_movie));
        adapter.AddFragment(new TvShowFragment(), getResources().getString(R.string.tab_tv));
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_change_setting) {
            Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


}
