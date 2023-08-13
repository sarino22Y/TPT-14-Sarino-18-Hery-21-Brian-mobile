package com.mbds.tpt_sarino_brian_hery.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mbds.tpt_sarino_brian_hery.R;

public class EventDeclarationFragment extends Fragment {

    private EditText titleField;
    private EditText descriptionField;
    private EditText departmentField;
    private EditText citizenIdField;

    public EventDeclarationFragment() {
        // Constructeur vide requis
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_declare_event, container, false);

        titleField = rootView.findViewById(R.id.title_field);
        descriptionField = rootView.findViewById(R.id.description_field);
        departmentField = rootView.findViewById(R.id.department_field);
        citizenIdField = rootView.findViewById(R.id.citizen_id_field);

        Button submitButton = rootView.findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupérer les valeurs des champs et effectuer la déclaration d'événement
            }
        });

        return rootView;
    }
}