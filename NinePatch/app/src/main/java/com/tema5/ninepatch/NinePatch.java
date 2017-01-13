package com.tema5.ninepatch;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class NinePatch extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueve_patch);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_nueve_patch, menu);
        return true;
    }
}
