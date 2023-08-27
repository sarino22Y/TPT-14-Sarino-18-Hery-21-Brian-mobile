package com.mbds.tpt_sarino_brian_hery.view;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.mbds.tpt_sarino_brian_hery.R;
import com.mbds.tpt_sarino_brian_hery.model.user.User;
import com.mbds.tpt_sarino_brian_hery.model.utils.UserDatabaseHelper;
import com.mbds.tpt_sarino_brian_hery.viewmodel.user.UserViewModel;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toast.makeText(this, "Vous êtes sur la page d'inscription", Toast.LENGTH_SHORT).show();

        init();
    }

    private TextView lnkLogin, txtLastName, txtFirstName, txtEmail, txtPwd, txtConfirmPwd;
    private FirebaseAuth firebaseAuth;
    private ProgressBar progressBar;

    private UserDatabaseHelper databaseHelper;

    private UserViewModel userViewModel;

    private void init() {
        lnkLogin = findViewById(R.id.lnkLogin);
        txtLastName = findViewById(R.id.txtLastName);
        txtFirstName = findViewById(R.id.txtFirstName);
        txtEmail = findViewById(R.id.txtEmail);
        txtPwd = findViewById(R.id.txtPwd);
        txtConfirmPwd = findViewById(R.id.txtConfirmPwd);
        progressBar = findViewById(R.id.progress_bar_register);
        userViewModel = new UserViewModel(this);

        FirebaseApp.initializeApp(this);
        firebaseAuth = FirebaseAuth.getInstance();

        databaseHelper = new UserDatabaseHelper(this);

        // Creer un compte
        createAccount();
        goToLogin();
    }

    /**
     * Créer un compte.
     */
    private void createAccount() {
        findViewById(R.id.btnCreateAccount).setOnClickListener(v -> {
            showProgressBar();
            String lastName = txtLastName.getText().toString().trim();
            String firstName = txtFirstName.getText().toString().trim();
            String email = txtEmail.getText().toString().trim();
            String pwd = txtPwd.getText().toString().trim();
            String confirmPwd = txtConfirmPwd.getText().toString().trim();

            if (checkFields(lastName, firstName, email, pwd, confirmPwd)) {
                firebaseAuth.createUserWithEmailAndPassword(email,pwd)
                    .addOnCompleteListener( task -> {
                        if (task.isSuccessful()) {
                            User user = new User();
                            user.setLastName(lastName);
                            user.setFirstName(firstName);
                            user.setEmail(email);
                            user.setPassword(pwd);

                            userViewModel.createAccount(user);

                            Toast.makeText(this, "Inscription reussie", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(this, LoginActivity.class));
                            finish();
                        } else {
                            Toast.makeText(this, "Erreur lors de l'inscription", Toast.LENGTH_SHORT).show();
                            Toast.makeText(this, "Vrérifier si l'email est valide", Toast.LENGTH_SHORT).show();
                            hideProgressBar();
                        }
                    }
                );
                hideProgressBar();
            }
        });
    }

    /**
     * Controle des champs.
     */
    public boolean checkFields(String lastName, String firstName, String email, String pwd, String confirmPwd) {
        boolean isValid = true;
        if (TextUtils.isEmpty(lastName)) {
            txtLastName.setError("Entrer un nom");
            txtLastName.requestFocus();
            isValid = false;
        }
        if (TextUtils.isEmpty(firstName)) {
            txtFirstName.setError("Entrer un prénom");
            txtFirstName.requestFocus();
            isValid = false;
        }
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
        if (!pwd.equals(confirmPwd)) {
            txtConfirmPwd.setError("Entrer le même mot de passe");
            txtConfirmPwd.requestFocus();
            isValid = false;
        }
        return isValid;
    }

    /**
     * Redirection vers la page de connexion.
     */
    public void goToLogin() {
        lnkLogin.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
    }

    private void showProgressBar(){
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar(){
        progressBar.setVisibility(View.GONE);
    }
}