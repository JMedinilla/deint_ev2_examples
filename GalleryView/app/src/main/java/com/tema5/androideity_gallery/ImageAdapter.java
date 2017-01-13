package com.tema5.androideity_gallery;

import java.util.List;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {

	private List<Drawable> drawables;
	private Context context;
	private int itemBackground;

	public ImageAdapter(Context context, List<Drawable> drawables) {
		this.drawables = drawables;
		this.context = context;
		TypedArray attr = context.obtainStyledAttributes(R.styleable.MiGallery);
		itemBackground=attr.getResourceId(R.styleable.MiGallery_android_galleryItemBackground, 0);
		attr.recycle();
	}

	public int getCount() {
		return drawables.size();
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		ImageView imageView;
		if (convertView == null) {
			imageView = new ImageView(context);
			imageView.setScaleType(ImageView.ScaleType.FIT_XY);
			imageView.setLayoutParams(new Gallery.LayoutParams(150, 120));
			imageView.setBackgroundResource(itemBackground);

		} else
			imageView = (ImageView) convertView;
		imageView.setImageDrawable(drawables.get(position));
		
		return imageView;

	}

}