package com.jmedinilla.boundservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button btnStop;
    private Button btnTimestamp;
    private TextView txtTimestamp;

    private BoundService boundService;
    private boolean serviceBounded;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            boundService = ((BoundService.MyBinder) iBinder).getService();
            serviceBounded = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            serviceBounded = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStop = (Button) findViewById(R.id.btnStop);
        btnTimestamp = (Button) findViewById(R.id.btnPrintTime);
        txtTimestamp = (TextView) findViewById(R.id.txtTimestamp);
        btnStop.setEnabled(false);

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myServiceStop();
            }
        });

        btnTimestamp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtTimestamp.setText(boundService.getTimesStamp());
                btnStop.setEnabled(true);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(MainActivity.this, BoundService.class);
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        myServiceStop();
    }

    private void myServiceStop() {
        if (serviceBounded) {
            unbindService(serviceConnection);
            serviceBounded = false;
        }
        Intent intent = new Intent(MainActivity.this, BoundService.class);
        stopService(intent);
        btnStop.setEnabled(false);
        txtTimestamp.setText("");
    }
}
