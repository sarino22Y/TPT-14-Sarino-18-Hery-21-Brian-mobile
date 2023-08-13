package com.mbds.tpt_sarino_brian_hery.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mbds.tpt_sarino_brian_hery.R;

public class UserProfileFragment extends Fragment {

    public UserProfileFragment() {
        // Constructeur vide requis
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_userprofil, container, false);
        user = FirebaseAuth.getInstance().getCurrentUser();

        setupNavigationView(); // Configuration de la NavigationView

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        drawerLayout = getActivity().findViewById(R.id.drawer_layout);
    }


    private View rootView;
    private DrawerLayout drawerLayout;
    FirebaseUser user;


    // Configurer la NavigationView
    private void setupNavigationView() {
        NavigationView navigationView = rootView.findViewById(R.id.nav_profile);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                // Gérer les clics sur les éléments du menu
                switch (item.getItemId()) {
                    case R.id.menu_edit_profile:
                        // Ouvrir l'écran de modification de profil
                        break;
                    case R.id.menu_logout:
                        // Deconnexion
                        logout();
                        break;
                }
                // Fermer le menu après avoir géré la sélection
                drawerLayout.closeDrawer(Gravity.RIGHT);
                return true;
            }
        });
    }

    /**
     *Deconnexion
     */
    public void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }
}
