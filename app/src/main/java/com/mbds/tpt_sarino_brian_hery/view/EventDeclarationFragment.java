package com.mbds.tpt_sarino_brian_hery.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mbds.tpt_sarino_brian_hery.R;
import com.mbds.tpt_sarino_brian_hery.model.declareevent.DeclareEvent;
import com.mbds.tpt_sarino_brian_hery.model.declareevent.DeclareEventServiceLocal;

import java.util.List;

public class EventDeclarationFragment extends Fragment {

    private EditText titleField;
    private EditText descriptionField;
    private EditText departmentField;
    private EditText citizenIdField;
    private DeclareEventServiceLocal declareEventServiceLocal;

    public EventDeclarationFragment() {
        // Constructeur vide requis
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_declare_event, container, false);

        init(rootView);

        return rootView;
    }

    private void init(View rootView) {
        titleField = rootView.findViewById(R.id.title_field);
        descriptionField = rootView.findViewById(R.id.description_field);
        departmentField = rootView.findViewById(R.id.department_field);
        citizenIdField = rootView.findViewById(R.id.citizen_id_field);
        declareEventServiceLocal = new DeclareEventServiceLocal(getContext(), null, null, 1);
        rootView.findViewById(R.id.submit_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleField.getText().toString();
                String description = descriptionField.getText().toString();
                String department = departmentField.getText().toString();
                String citizenId = citizenIdField.getText().toString();
                if (checkFields(title, description, department, citizenId)) {
                    DeclareEvent event = new DeclareEvent(title, description, department, citizenId, "1");
                    event = declareEventServiceLocal.addDeclareEvent(event);
                    if (event != null) {
                        titleField.setText("");
                        descriptionField.setText("");
                        departmentField.setText("");
                        citizenIdField.setText("");
                        Toast.makeText(getContext(), "Evenement ajouté", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Evenement non ajouté", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        goToMyListe(rootView);
    }

    /**
     * Est-ce que les champs réquis sont valides.
     */
    private boolean checkFields(String title, String description, String department, String citizenId) {
        boolean isValid = true;
        if (TextUtils.isEmpty(title)) {
            titleField.setError("Entrer un titre");
            titleField.requestFocus();
            isValid = false;
        }
        if (TextUtils.isEmpty(description)) {
            descriptionField.setError("Entrer une description");
            descriptionField.requestFocus();
            isValid = false;
        }
        if (TextUtils.isEmpty(department)) {
            departmentField.setError("Entrer un nom de département");
            departmentField.requestFocus();
            isValid = false;
        }
        if (TextUtils.isEmpty(citizenId)) {
            citizenIdField.setError("Entrer un identifiant");
            citizenIdField.requestFocus();
            isValid = false;
        }

        return isValid;
    }

    /**
     * Aller vers la page de la liste des évènements déclarés par l'utilisateur connecté.
     */
    private void goToMyListe(View viewRoot) {
        viewRoot.findViewById(R.id.myliste_button).setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), ListeDeclareEnventActivity.class);
            startActivity(intent);
        });
    }
}