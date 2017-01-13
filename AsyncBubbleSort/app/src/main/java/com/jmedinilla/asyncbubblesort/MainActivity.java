package com.jmedinilla.asyncbubblesort;

import android.app.Activity;
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

    public static final int LENGHT = 2000;
    public static int numbers[] = new int[LENGHT];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtProgress = (TextView) findViewById(R.id.txtProgress);
        btnInit = (Button) findViewById(R.id.btnInit);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        if (hiddenFragment == null) {
            generateNumbers();
        }

        btnInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hiddenFragment = new HiddenFragment();
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

    }

    @Override
    public void onProgressUpdate(int progress) {

    }

    @Override
    public void onCancelled() {

    }

    @Override
    public void onPostExecute() {

    }
}
