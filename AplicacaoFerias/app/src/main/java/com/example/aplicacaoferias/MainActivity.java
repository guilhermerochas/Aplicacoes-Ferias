package com.example.aplicacaoferias;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aplicacaoferias.Fragments.CalculaTrianguloFragment;
import com.example.aplicacaoferias.Fragments.ContatosFragment;
import com.example.aplicacaoferias.Fragments.GPSFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Adapter
    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SetAdapter();
    }


    private void SetAdapter(){
        tabLayout = findViewById(R.id.tabbarlayout);
        viewPager = findViewById(R.id.view_page);
        MyFragAdapter fragAdapter = new MyFragAdapter(getSupportFragmentManager(),
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(fragAdapter);
        tabLayout.setupWithViewPager(viewPager);
        return;
    }
}

class MyFragAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragList = new ArrayList<>();
    private List<String> nameFragList = new ArrayList<>();
    CalculaTrianguloFragment calculaTrianguloFragment = new CalculaTrianguloFragment();
    GPSFragment gpsFragment = new GPSFragment();
    ContatosFragment contatosFragment = new ContatosFragment();

    public MyFragAdapter(FragmentManager fm, int behavior) {
        super(fm, behavior);
        AddFragmentsToList();
    }

    public void AddFragmentsToList(){
        fragList.add(calculaTrianguloFragment);
        nameFragList.add("CALCULAR");
        fragList.add(gpsFragment);
        nameFragList.add("GPS");
        fragList.add(contatosFragment);
        nameFragList.add("CONTATOS");
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return nameFragList.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return fragList.get(position);
    }

    @Override
    public int getCount() {
        return fragList.size();
    }
}