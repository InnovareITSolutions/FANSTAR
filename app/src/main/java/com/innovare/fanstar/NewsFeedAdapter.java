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

public class NewsFeedAdapter extends BaseAdapter {
	private Activity activity;
	private LayoutInflater inflater;
	private List<NewsFeedModel> newsFeedModels;
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();
	private Context context;
	private String imagePath;

	public NewsFeedAdapter(FragmentActivity fragment, List<NewsFeedModel> newsFeedModels) {
		this.activity = fragment;
		this.newsFeedModels = newsFeedModels;
	}

	@Override
	public int getCount() {
		return newsFeedModels.size();
	}

	@Override
	public Object getItem(int location) {
		return newsFeedModels.get(location);
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
			convertView = inflater.inflate(R.layout.feeds_list_item, null);

		if (imageLoader == null)
			imageLoader = AppController.getInstance().getImageLoader();

		TextView profileName = (TextView) convertView.findViewById(R.id.profile_name);
		TextView timestamp = (TextView) convertView.findViewById(R.id.time);
		TextView description = (TextView) convertView.findViewById(R.id.post);
		CircularImageView profilePic = (CircularImageView) convertView.findViewById(R.id.profile_pic);
		ImageView postsImage = (ImageView)convertView.findViewById(R.id.post_image);

		final NewsFeedModel item = newsFeedModels.get(position);

		profileName.setText(item.getProfilename());
		timestamp.setText(item.getTimestamp());
		description.setText(item.getDescription());


		try {
			Picasso.with(activity).load((item.getProfileimage())).fit().into(profilePic);

			Picasso.with(activity).load((item.getNewsfeedimage())).fit().into(postsImage);


		}
		catch (Exception e){
			Log.v("err", e.toString());
		}



		return convertView;
	}
}
