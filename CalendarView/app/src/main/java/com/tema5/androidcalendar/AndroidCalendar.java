package com.tema5.androidcalendar;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.Toast;

public class AndroidCalendar extends Activity {
	private CalendarView cv;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_android_calendar);
		cv = (CalendarView) findViewById(R.id.calview);
		// get date from CalendarView.
		cv.setOnDateChangeListener(new OnDateChangeListener() {
			@Override
			public void onSelectedDayChange(CalendarView arg0, int year,
					int month, int date) {
				Toast.makeText(getApplicationContext(),
						date + "/" + month + "/" + year, Toast.LENGTH_LONG).show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_android_calendar, menu);
		return true;
	}
}
