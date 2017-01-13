package com.sai.thread;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class HandlerThread extends Activity {

	private Button start;
	private ProgressBar pbarProgreso;
	private Handler messageHandler = new Handler() {

		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				pbarProgreso.incrementProgressBy(10);
				break;
			/*
			 * case 1: pbarProgreso.setProgress(0);
			 * Toast.makeText(getApplicationContext(), "Fin de la aplicación",
			 * Toast.LENGTH_SHORT).show(); 
			 * break;
			 */

			}
			String mensaje = msg.getData().getString("mensajefinal");
			if (mensaje != null) {
				Toast.makeText(getApplicationContext(), mensaje,
						Toast.LENGTH_SHORT).show();
				pbarProgreso.setProgress(0);
			}
		}
	};

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		pbarProgreso = (ProgressBar) findViewById(R.id.pbarProgreso);
		start = (Button) findViewById(R.id.Button01);
		start.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				pbarProgreso.setMax(100);
				pbarProgreso.setProgress(0);
				new Thread(new Runnable() {
					public void run() {

						for (int i = 1; i <= 10; i++) {
							tareaLarga();
							messageHandler.sendEmptyMessage(0);
						}
						Message msg = new Message();
						Bundle bundle = new Bundle();
						bundle.putString("mensajefinal", "Operación final");
						msg.setData(bundle);
						messageHandler.sendMessage(msg);
						// messageHandler.sendEmptyMessage(1);
					}

				}).start();

			}

		});
	}

	private void tareaLarga() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
	}
}