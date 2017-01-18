package com.jmedinilla.asyncbubblesort;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends Activity implements HiddenFragment.ITaskCallback {

    private TextView txtProgress;
    private Button btnInit;
    private Button btnCancel;
    private HiddenFragment hiddenFragment;
    private ProgressBar progressBar;

    public static final int LENGHT = 20000;
    public static int numbers[] = new int[LENGHT];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtProgress = (TextView) findViewById(R.id.txtProgress);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        btnInit = (Button) findViewById(R.id.btnInit);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        if (hiddenFragment == null) {
            generateNumbers();
        }

        btnInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtProgress.setText("Ordenando . . .");

                FragmentManager fm = getFragmentManager();
                hiddenFragment = new HiddenFragment();
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(hiddenFragment, "hidden");
                ft.commit();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hiddenFragment.bubbleNumberTask.cancel(true);
            }
        });
    }

    private void generateNumbers() {
        Random random = new Random();
        for (int i = 0; i < LENGHT; i++) {
            numbers[i] = random.nextInt(101);
        }
    }

    @Override
    public void onPreExecute() {
        buttonTaskVisibility(true);
    }

    @Override
    public void onProgressUpdate(int progress) {
        progressBar.setProgress(progress);
    }

    @Override
    public void onCancelled() {
        txtProgress.setText("Operación cancelada");
        buttonTaskVisibility(false);
    }

    @Override
    public void onPostExecute(Long aVoid) {
        buttonTaskVisibility(false);
        txtProgress.setText(aVoid + " segundos");
    }

    private void buttonTaskVisibility(boolean vis) {
        if (vis) {
            btnCancel.setVisibility(View.VISIBLE);
            btnInit.setVisibility(View.INVISIBLE);
        } else {
            btnCancel.setVisibility(View.INVISIBLE);
            btnInit.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("buttoninitstate", btnInit.getVisibility());
        outState.putInt("buttoncancelstate", btnCancel.getVisibility());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.getInt("buttoninitstate") == 0) {
            btnInit.setVisibility(View.VISIBLE);
        } else {
            btnInit.setVisibility(View.INVISIBLE);
        }
        if (savedInstanceState.getInt("buttoncancelstate") == 0) {
            btnCancel.setVisibility(View.VISIBLE);
        } else {
            btnCancel.setVisibility(View.INVISIBLE);
        }
        hiddenFragment = (HiddenFragment)getFragmentManager().findFragmentByTag("hidden");
    }
}
