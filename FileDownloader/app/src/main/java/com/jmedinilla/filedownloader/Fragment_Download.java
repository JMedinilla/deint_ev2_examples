package com.jmedinilla.filedownloader;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class Fragment_Download extends Fragment implements View.OnClickListener {
    Button btnDownload;
    private final static int REQUEST_WRITE_EXTERNAL = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_download, container, false);
        btnDownload = (Button) rootView.findViewById(R.id.btnDownload);
        btnDownload.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View view) {
        if (checkWritePermission()) {
            onDownload();
        }
    }

    /**
     * Método que inicia la descarga de un fichero
     */
    private void onDownload() {
        btnDownload.setEnabled(false);
        Intent intent = new Intent(getActivity(), Service_Downloader.class);
        intent.setData(Uri.parse("https://commonsware.com/Android/Android-1_0-CC.pdf"));
        getActivity().startService(intent);
    }

    /**
     * Método que comprueba si la aplicación tiene los permisos
     * necesarios para ejecutarse
     *
     * @return True (yes) / False (no)
     */
    private boolean checkWritePermission() {
        final String permission = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        int permissionCheck = ContextCompat.checkSelfPermission(getActivity(), permission);
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else if (shouldShowRequestPermissionRationale(permission)) {
            Snackbar.make(
                    getActivity().findViewById(R.id.activity_download_fragment),
                    R.string.giveMePermission, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ActivityCompat.requestPermissions(getActivity(), new String[]{permission}, REQUEST_WRITE_EXTERNAL);
                        }
                    }).show();
            return false;
        } else {
            requestPermissions(new String[]{permission}, REQUEST_WRITE_EXTERNAL);
            return false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_WRITE_EXTERNAL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                onDownload();
            } else {
                Toast.makeText(getActivity(), R.string.donthavePermission, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
