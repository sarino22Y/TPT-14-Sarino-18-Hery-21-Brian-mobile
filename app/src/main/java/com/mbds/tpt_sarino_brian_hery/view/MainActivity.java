package com.mbds.tpt_sarino_brian_hery.view;

import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mbds.tpt_sarino_brian_hery.R;

public class MainActivity extends AppCompatActivity {

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
        emailCurrentUser = findViewById(R.id.emailCurrentUser);

        if (user != null) {
            emailCurrentUser.setText(user.getEmail());
        } else {
            Toast.makeText(this, "Vous n'êtes pas connecté", Toast.LENGTH_SHORT).show();
//            goToRegister();
        }

        logout();
    }

    /**
     * Deconnexion
     */
    public void logout(){
        findViewById(R.id.btnLogout).setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(this, "Vous êtes déconnecté", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });
    }
}