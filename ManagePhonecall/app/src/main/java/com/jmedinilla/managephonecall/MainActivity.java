package com.jmedinilla.managephonecall;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.listView);
        ArrayList<String> callArrayList = new ArrayList<>();

        Uri call_log = CallLog.Calls.CONTENT_URI;
        String[] projection = new String[]{CallLog.Calls.NUMBER, CallLog.Calls.TYPE, CallLog.Calls.DATE, CallLog.Calls.DURATION};
        ContentResolver contentResolver = getContentResolver();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Cursor c = contentResolver.query(call_log, projection, null, null, null, null);

        if (c.moveToFirst()) {
            do {
                String number = c.getString(c.getColumnIndex(CallLog.Calls.NUMBER));
                String type = c.getString(c.getColumnIndex(CallLog.Calls.TYPE));
                String date = c.getString(c.getColumnIndex(CallLog.Calls.DATE));
                String duration = c.getString(c.getColumnIndex(CallLog.Calls.DURATION));
                Call call = new Call(number, type, date, duration);
                callArrayList.add(call.toString());
            } while (c.moveToNext());
            c.close();

            listView.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_activated_1, callArrayList));
        }
    }
}
