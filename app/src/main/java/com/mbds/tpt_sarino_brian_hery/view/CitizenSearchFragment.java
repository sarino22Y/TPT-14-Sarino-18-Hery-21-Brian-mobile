package com.mbds.tpt_sarino_brian_hery.view;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mbds.tpt_sarino_brian_hery.R;

public class CitizenSearchFragment extends Fragment {

    private EditText searchField;
    private TextView resultText;

    public CitizenSearchFragment() {
        // Constructeur vide requis

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search_citizen, container, false);

        searchField = rootView.findViewById(R.id.search_field);
        resultText = rootView.findViewById(R.id.result_text);

        // Gérer la recherche lorsqu'un bouton de recherche est cliqué
        rootView.findViewById(R.id.search_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performSearch();
            }
        });

        return rootView;
    }

    private void performSearch() {
        String searchTerm = searchField.getText().toString().trim();

        // Effectuer la recherche en fonction du searchTerm
        // Si des citoyens sont trouvés, afficher les résultats dans resultText
        // Sinon, afficher "Aucun citoyen trouvé" en italique
        // resultText.setText("Aucun citoyen trouvé");
    }
}