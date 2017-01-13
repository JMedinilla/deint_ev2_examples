package com.tema5.androideity_gallery;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class AndroideityGallery extends Activity {
	/** Called when the activity is first created. */
	private ImageView selectedImageView;
	private ArrayList<Drawable> drawables;

	private void getDrawablesList() {

		drawables = new ArrayList<Drawable>();
		drawables.add(getResources().getDrawable(R.drawable.image_1));
		drawables.add(getResources().getDrawable(R.drawable.image_2));
		drawables.add(getResources().getDrawable(R.drawable.image_3));
		drawables.add(getResources().getDrawable(R.drawable.image_4));
		drawables.add(getResources().getDrawable(R.drawable.image_5));
		drawables.add(getResources().getDrawable(R.drawable.image_6));
		drawables.add(getResources().getDrawable(R.drawable.image_7));

	}

	private void setSelectedImage(int selectedImagePosition) {

		BitmapDrawable bd = (BitmapDrawable) drawables
				.get(selectedImagePosition);
		Bitmap b = Bitmap.createScaledBitmap(bd.getBitmap(),
				(int) (bd.getIntrinsicHeight() * 0.9),
				(int) (bd.getIntrinsicWidth() * 0.7), false);
		selectedImageView.setImageBitmap(b);
		selectedImageView.setScaleType(ScaleType.FIT_XY);

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		getDrawablesList();
		Gallery gallery = (Gallery) findViewById(R.id.gallery);
		gallery.setAdapter(new ImageAdapter(this, drawables));
		selectedImageView = (ImageView) findViewById(R.id.imagen);

		gallery.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> adapter, View v,
					int position, long id) {
				setSelectedImage(position);
			}
		});
	}
}
