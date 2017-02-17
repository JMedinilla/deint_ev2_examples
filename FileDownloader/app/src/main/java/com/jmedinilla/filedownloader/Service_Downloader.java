package com.jmedinilla.filedownloader;

import android.app.IntentService;
import android.content.Intent;
import android.os.Environment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Service_Downloader extends IntentService {
    public static final String ACTION_COMPLETE =
            "com.jmedinilla.filedownloader.action.COMPLETE";

    public Service_Downloader() {
        super("Service_Downloader");
    }

    @Override
    public void onHandleIntent(Intent i) {
        try {
            File root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

            root.mkdirs();

            File output = new File(root, i.getData().getLastPathSegment());

            if (output.exists()) {
                output.delete();
            }

            URL url = new URL(i.getData().toString());
            HttpURLConnection c = (HttpURLConnection) url.openConnection();

            FileOutputStream fos = new FileOutputStream(output.getPath());
            BufferedOutputStream out = new BufferedOutputStream(fos);

            try {
                InputStream in = c.getInputStream();
                byte[] buffer = new byte[8192];
                int len;

                while ((len = in.read(buffer)) >= 0) {
                    out.write(buffer, 0, len);
                }

                out.flush();
            } finally {
                fos.getFD().sync();
                out.close();
                c.disconnect();
            }

            //LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(ACTION_COMPLETE));
            getApplicationContext().sendBroadcast(new Intent(ACTION_COMPLETE));
        } catch (IOException e2) {
            Log.e(getClass().getName(), "Exception in download", e2);
        }
    }
}