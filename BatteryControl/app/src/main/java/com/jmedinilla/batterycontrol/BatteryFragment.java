package com.jmedinilla.batterycontrol;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class BatteryFragment extends Fragment {
    ProgressBar progressBar;
    TextView textView;
    ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_battery, container, false);
        progressBar = (ProgressBar)rootView.findViewById(R.id.fragment_prbLevel);
        textView = (TextView)rootView.findViewById(R.id.fragment_txtLevel);
        imageView = (ImageView)rootView.findViewById(R.id.fragment_imgStatus);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        //Crear un IntentFilter para el action ACTION_BATTERY_CHANGED
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        //Registrar el BroadcastReceiver
        getActivity().registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(broadcastReceiver);
    }

    /*
     * Broadcast que depende del ciclo de vida del Fragment
     */
    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //Leer la información que llega del Intent: level, status
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            float battery = level*100 / (float)scale;
            progressBar.setProgress((int)battery);

            //Estado de la batería
            int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
            int plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
            if (plugged == BatteryManager.BATTERY_PLUGGED_USB
                    || plugged == BatteryManager.BATTERY_PLUGGED_AC
                    || plugged == BatteryManager.BATTERY_PLUGGED_WIRELESS) {
                switch (status) {
                    case BatteryManager.BATTERY_STATUS_CHARGING:
                        imageView.setImageResource(R.mipmap.ic_charging);
                        break;
                    case BatteryManager.BATTERY_STATUS_FULL:
                        imageView.setImageResource(R.mipmap.ic_full);
                        break;
                    default:
                        break;
                }
            }
            else {
                if ((level*100) == 100) {
                    imageView.setImageResource(R.mipmap.ic_unplugged);
                }
                else {
                    //Aquí que ponen el resto de imágenes sin enchufar y menos de 99%
                }
            }
        }
    };
}
