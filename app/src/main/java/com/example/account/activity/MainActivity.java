package com.example.account.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.account.R;
import com.example.account.adapter.MonthlyAccountAdapter;
import com.example.account.dao.ItemDataBaseHelper;
import com.example.account.fragment.MyFragment;
import com.example.account.fragment.montlyFragment;
import com.example.account.fragment.showFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

import java.util.PrimitiveIterator;

public class MainActivity extends AppCompatActivity {


    private Fragment fragment_show,fragment_monthly,fragment_person;
    private Fragment[] fragments;
    private int lastfragment;//用于记录上个选择的Fragment
    private BottomNavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFragment();

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        navigationView  = findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_home:
                        if(lastfragment!=0)
                        {
                            switchFragment(lastfragment,0);
                            lastfragment=0;
                        }

                        return true;
                    case R.id.navigation_monthly:
                        if(lastfragment!=1)
                        {
                            switchFragment(lastfragment,1);
                            lastfragment=1;
                        }

                        return true;
                    case R.id.navigation_person:
                        if(lastfragment!=2)
                        {
                            switchFragment(lastfragment,2);
                            lastfragment=2;
                        }

                        return true;
                }
                return false;
            }
        });
    }

    private void initFragment() {
       fragment_show = new showFragment();
        fragment_monthly = new montlyFragment();
        fragment_person = new MyFragment();
        fragments = new Fragment[]{fragment_show,fragment_monthly,fragment_person};
        lastfragment=0;
        getSupportFragmentManager().beginTransaction().replace(R.id.mainview,fragment_show).show(fragment_show).commit();


    }
    //切换Fragment
    private void switchFragment(int lastfragment,int index)
    {
        FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
        transaction.hide(fragments[lastfragment]);//隐藏上个Fragment
        if(fragments[index].isAdded()==false)
        {
            transaction.add(R.id.mainview,fragments[index]);
        }
        transaction.show(fragments[index]).commitAllowingStateLoss();


    }
}