package com.mbds.tpt_sarino_brian_hery.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.mbds.tpt_sarino_brian_hery.R;

import com.google.zxing.*;

public class QRCodeScanFragment extends Fragment {

    public QRCodeScanFragment() {
        // Constructeur vide requis
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_scan_q_r_code, container, false);

        // Bouton pour déclencher le scan QRCode
        rootView.findViewById(R.id.imageViewScan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startZxinginQRCodeScan();
            }
        });

        return rootView;
    }

    // Méthode pour démarrer le scan QRCode par zxing.
    private void startZxinginQRCodeScan() {
        IntentIntegrator integrator = IntentIntegrator.forSupportFragment(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
        integrator.setPrompt("Scanner un QRCode");
        integrator.setCameraId(0);  // Utilisation de la caméra arrière
        integrator.setOrientationLocked(true);
        integrator.initiateScan();
    }

    // Méthode appelée lorsque le résultat du scan QRCode est obtenu
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null && result.getContents() != null) {
            // Utiliser le contenu du QRCode obtenu
            String qrCodeContents = result.getContents();
            qrCodeContents = qrCodeContents.trim();
            if (qrCodeContents.charAt(0) == '/') qrCodeContents = "http://" + qrCodeContents;

            // Vérifier si le contenu du QRCode est une URL valide
            if (isURLValid(qrCodeContents)) {
                openURLInBrowser(qrCodeContents); // Ouvrir l'URL dans le navigateur
            } else {
                Toast.makeText(getActivity(), "QRCode scanné : " + qrCodeContents, Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Méthode pour vérifier si une chaîne est une URL valide
    private boolean isURLValid(String url) {
        return url != null && (url.startsWith("http://") || url.startsWith("https://"));
    }

    // Méthode pour ouvrir une URL dans le navigateur du téléphone
    private void openURLInBrowser(String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }
}