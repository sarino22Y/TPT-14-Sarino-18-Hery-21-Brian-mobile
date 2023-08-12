package com.mbds.tpt_sarino_brian_hery.view;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;
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

    FirebaseAuth firebaseAuth;
    TextView lnkRegister;
    EditText txtEmail, txtPwd;
    private static final int RC_SIGN_IN = 123; // Un code de demande arbitraire

    private void init() {
        firebaseAuth = FirebaseAuth.getInstance();
        lnkRegister = findViewById(R.id.lnkRegister);
        txtEmail = findViewById(R.id.txtEmailLogin);
        txtPwd = findViewById(R.id.txtPwdLogin);
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
        login(txtEmail.getText().toString(), txtPwd.getText().toString());
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
     * Se connecter avec un compte existant.
     */
    private void login(String email, String pwd) {
        findViewById(R.id.btnLogin).setOnClickListener(v -> {
            if (checkFields(email, pwd)) {
                if (firebaseAuth.createUserWithEmailAndPassword(email,pwd).isComplete()) {
                    Toast.makeText(this, "Vous êtes connecté", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Erreur lors de la connexion", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * Controle des champs.
     */
    public boolean checkFields(String email, String pwd) {
        boolean isValid = true;
        if (TextUtils.isEmpty(email)) {
            txtEmail.setError("Entrer un e-mail");
            txtEmail.requestFocus();
            isValid = false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            txtEmail.setError("Entrer un e-mail valide");
            txtEmail.requestFocus();
            isValid = false;
        }
        if (TextUtils.isEmpty(pwd)) {
            txtPwd.setError("Entrer un mot de passe");
            txtPwd.requestFocus();
            isValid = false;
        }

        return isValid;
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