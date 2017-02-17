package com.jmedinilla.filedownloader;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class Receiver_Download extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, R.string.download_file, Toast.LENGTH_SHORT).show();
    }
}
