package com.innovare.fanstar;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class YourFansAdapter extends BaseAdapter {
	private Activity activity;
	private LayoutInflater inflater;
	private List<YourFanModel> yourFanModels;
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();
	private Context context;
	private String imagePath;

	public YourFansAdapter(FragmentActivity fragment, List<YourFanModel> yourFanModels) {
		this.activity = fragment;
		this.yourFanModels = yourFanModels;
	}

	@Override
	public int getCount() {
		return yourFanModels.size();
	}

	@Override
	public Object getItem(int location) {
		return yourFanModels.get(location);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		if (inflater == null)
			inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (convertView == null)
			convertView = inflater.inflate(R.layout.your_fans_list_item, null);

		if (imageLoader == null)
			imageLoader = AppController.getInstance().getImageLoader();

		TextView yourfanname = (TextView) convertView.findViewById(R.id.your_fan_name);
		CircularImageView yourFanImage = (CircularImageView) convertView.findViewById(R.id.your_fan_pic);

		final YourFanModel item = yourFanModels.get(position);

		yourfanname.setText(item.getYourfan_name());

		try {
			Picasso.with(activity).load((item.getYourfan_image())).fit().into(yourFanImage);
		}
		catch (Exception e){
			Log.v("err", e.toString());
		}



		return convertView;
	}
}
