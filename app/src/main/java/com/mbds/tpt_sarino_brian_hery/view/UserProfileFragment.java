package com.mbds.tpt_sarino_brian_hery.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mbds.tpt_sarino_brian_hery.R;
import com.mbds.tpt_sarino_brian_hery.model.user.User;
import com.mbds.tpt_sarino_brian_hery.model.utils.UserDatabaseHelper;

public class UserProfileFragment extends Fragment {

    public UserProfileFragment() {
        // Constructeur vide requis
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_userprofil, container, false);

        init(rootView);

        return rootView;
    }


    private View rootView;
    private FirebaseUser user;
    private TextView userName;
    private TextView userEmail, userLastName, userFirstName, userPassword;

    private void init(View rootView) {
        user = FirebaseAuth.getInstance().getCurrentUser();
        userEmail = rootView.findViewById(R.id.user_name);
        if (user != null) {
            userEmail.setText(user.getEmail());
        }

        userLastName = rootView.findViewById(R.id.user_lastname);
        userFirstName = rootView.findViewById(R.id.user_firstname);
        userPassword = rootView.findViewById(R.id.user_password);

        // Récupérez les informations de l'utilisateur depuis la base de données
        UserDatabaseHelper databaseHelper = new UserDatabaseHelper(getContext());
        User userInfo = databaseHelper.getUserByEmail(user.getEmail());

        if (userInfo != null) {
            userLastName.setText(userInfo.getLastName());
            userFirstName.setText(userInfo.getFirstName());
            userPassword.setText(userInfo.getPassword());
        } else {
            Toast.makeText(getContext(), "Erreur lors de la récupération des informations" , Toast.LENGTH_SHORT).show();
        }

        logout(rootView);
    }

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
