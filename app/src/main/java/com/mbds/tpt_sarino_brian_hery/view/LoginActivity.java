package com.mbds.tpt_sarino_brian_hery.view;

import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mbds.tpt_sarino_brian_hery.R;

import java.util.Arrays;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        init();
    }

    TextView lnkRegister;
    private static final int RC_SIGN_IN = 123; // Un code de demande arbitraire

    private void init() {
        lnkRegister = findViewById(R.id.lnkRegister);
        // Vérifiez si l'utilisateur est déjà connecté
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            // L'utilisateur est déjà connecté, faites quelque chose ici
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Bienvenue " + currentUser.getEmail(), Toast.LENGTH_SHORT).show();
        } else {
            // L'utilisateur n'est pas connecté, affichez l'écran de connexion
            startSignIn();
            Toast.makeText(this, "Vous êtes sur la page de connexion", Toast.LENGTH_SHORT).show();
        }
        goToRegister();
    }

    /**
     * Connexion avec google.
     */
    public void startSignIn() {
        findViewById(R.id.btnGoogle).setOnClickListener(v -> {
            // Configurer les fournisseurs d'authentification
            List<AuthUI.IdpConfig> providers = Arrays.asList(
                    new AuthUI.IdpConfig.GoogleBuilder().build()
            );
            // Afficher la fenêtre de connexion
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(providers)
                            .build(),
                    RC_SIGN_IN
            );
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);
            if (resultCode == RESULT_OK) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                Toast.makeText(this, "Vous êtes connecté" + user.getEmail(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Vous n'êtes pas connecté", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Redirection vers la page d'inscription.
     */
    public void goToRegister() {
        lnkRegister.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class));
        });
    }
}