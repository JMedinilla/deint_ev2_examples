package com.tema5.ejemplo_transitiondrawable;

import android.app.Activity;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;

public class EjemploTransitionDrawable extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ImageView image = new ImageView(this);
		setContentView(image);
		TransitionDrawable transition = (TransitionDrawable) getResources()
				.getDrawable(R.drawable.transition);
		image.setImageDrawable(transition);
		transition.startTransition(2000);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_transition_drawable, menu);
		return true;
	}
}
