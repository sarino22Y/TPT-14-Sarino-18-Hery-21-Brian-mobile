package com.mbds.tpt_sarino_brian_hery.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        userEmail = rootView.findViewById(R.id.user_name);
        if (user != null) {
            userEmail.setText(user.getEmail());
        }

        logout(rootView);
        return rootView;
    }


    private View rootView;
    private FirebaseUser user;
    private TextView userName;
    private TextView userEmail;

    /**
     *Deconnexion
     */
    public void logout(View rootView) {
        rootView.findViewById(R.id.imageViewLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
