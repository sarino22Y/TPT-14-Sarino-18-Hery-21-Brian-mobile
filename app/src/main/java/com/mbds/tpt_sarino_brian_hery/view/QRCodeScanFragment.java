package com.mbds.tpt_sarino_brian_hery.view;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mbds.tpt_sarino_brian_hery.R;

public class QRCodeScanFragment extends Fragment {

    public QRCodeScanFragment() {
        // Constructeur vide requis
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_scan_q_r_code, container, false);

        // Intégrer ici la logique de scan QRCode en utilisant une bibliothèque comme ZXing

        return rootView;
    }
}