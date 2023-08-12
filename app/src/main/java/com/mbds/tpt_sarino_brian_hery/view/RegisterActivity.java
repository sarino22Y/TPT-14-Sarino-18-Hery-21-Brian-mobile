package com.mbds.tpt_sarino_brian_hery.view;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.mbds.tpt_sarino_brian_hery.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toast.makeText(this, "Vous êtes sur la page d'inscription", Toast.LENGTH_SHORT).show();

        init();
    }

    TextView lnkLogin, txtName, txtEmail, txtPwd, txtConfirmPwd;
    FirebaseAuth firebaseAuth;

    private void init() {
        lnkLogin = findViewById(R.id.lnkLogin);
        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);
        txtPwd = findViewById(R.id.txtPwd);
        txtConfirmPwd = findViewById(R.id.txtConfirmPwd);

        FirebaseApp.initializeApp(this);
        firebaseAuth = FirebaseAuth.getInstance();

        String name = txtName.getText().toString().trim();
        String email = txtEmail.getText().toString().trim();
        String pwd = txtPwd.getText().toString().trim();
        String confirmPwd = txtConfirmPwd.getText().toString().trim();
        createAccount(name, email, pwd, confirmPwd);
        goToLogin();
    }

    /**
     * Créer un compte.
     */
    private void createAccount(String name, String email, String pwd, String confirmPwd) {
        findViewById(R.id.btnCreateAccount).setOnClickListener(v -> {
            if (checkFields(name, email, pwd, confirmPwd)) {
                firebaseAuth.createUserWithEmailAndPassword(email,pwd);
                Toast.makeText(this, "Enregistrement en cours", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Controle des champs.
     */
    public boolean checkFields(String name, String email, String pwd, String confirmPwd) {
        boolean isValid = true;
        if (TextUtils.isEmpty(name)) {
            txtName.setError("Entrer un nom");
            txtName.requestFocus();
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
        });
    }
}