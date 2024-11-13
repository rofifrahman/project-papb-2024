package com.example.freelsapps;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.freelsapps.Fragment.HomePageFragment;
import com.example.freelsapps.Fragment.ProfileFragment;
import com.example.freelsapps.Fragment.TambahLowonganFragment;
import com.example.freelsapps.Fragment.TrackLowonganFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new HomePageFragment()).commit();
        }

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nbHome) {
                replaceFragment(new HomePageFragment());
            } else if (item.getItemId() == R.id.nbAdd) {
                replaceFragment(new TambahLowonganFragment());
            } else if (item.getItemId() == R.id.nbTrack) {
                replaceFragment(new TrackLowonganFragment());
            } else if (item.getItemId() == R.id.nbProfile) {
                replaceFragment(new ProfileFragment());
            }
            return true;
        });
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit();

    }
}