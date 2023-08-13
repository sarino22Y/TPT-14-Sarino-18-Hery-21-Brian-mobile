package com.mbds.tpt_sarino_brian_hery.view;

import android.content.Intent;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mbds.tpt_sarino_brian_hery.R;
import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {
/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }
    FirebaseUser user;
    TextView emailCurrentUser;

    private void init(){
        user = FirebaseAuth.getInstance().getCurrentUser();
//        emailCurrentUser = findViewById(R.id.emailCurrentUser);

        if (user != null) {
//            emailCurrentUser.setText(user.getEmail());
        } else {
            Toast.makeText(this, "Vous n'êtes pas connecté", Toast.LENGTH_SHORT).show();
//            goToRegister();
        }

        logout();
    }

    *//**
     * Deconnexion
     *//*
    public void logout(){
        *//*findViewById(R.id.btnLogout).setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(this, "Vous êtes déconnecté", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });*//*
    }*/

    /*private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.action_search_citizen:
                        fragment = new CitizenSearchFragment();
                        break;
                    case R.id.action_scan_qr:
                        fragment = new QRCodeScanFragment();
                        break;
                    case R.id.action_event_declaration:
                        fragment = new EventDeclarationFragment();
                        break;
                    case R.id.action_user_profile:
                        fragment = new UserProfileFragment();
                        break;
                }

                if (fragment != null) {
                    replaceFragment(fragment);
                }
                return true;
            }
        });

        // Afficher le premier fragment au démarrage
        if (savedInstanceState == null) {
            bottomNavigationView.setSelectedItemId(R.id.action_search_citizen);
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }*/
    private ViewPager2 viewPager;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.view_pager);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        viewPager.setAdapter(new PagerAdapter(this));

        // Désactiver le swipe entre les fragments
//        viewPager.setUserInputEnabled(false);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_search_citizen:
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.action_scan_qr:
                    viewPager.setCurrentItem(1);
                    break;
                case R.id.action_event_declaration:
                    viewPager.setCurrentItem(2);
                    break;
                case R.id.action_user_profile:
                    viewPager.setCurrentItem(3);
                    break;
            }
            return true;
        });

        // Écouteur pour gérer le changement de fragment lors du swipe
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
            }
        });
    }

    private static class PagerAdapter extends FragmentStateAdapter {
        PagerAdapter(MainActivity activity) {
            super(activity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new CitizenSearchFragment();
                case 1:
                    return new QRCodeScanFragment();
                case 2:
                    return new EventDeclarationFragment();
                case 3:
                    return new UserProfileFragment();
                default:
                    return new Fragment();
            }
        }

        @Override
        public int getItemCount() {
            return 4;
        }
    }
}