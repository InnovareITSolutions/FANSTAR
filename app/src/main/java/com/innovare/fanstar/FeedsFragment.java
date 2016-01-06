package com.innovare.fanstar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class FeedsFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String TAG = FeedsFragment.class.getSimpleName();
    private ListView listView;
    private NewsFeedAdapter listAdapter;
    private List<NewsFeedModel> newsFeedModels;
    private String URL_FEED = "http://www.json-generator.com/api/json/get/cvdhMorqMi?indent=2";


    public FeedsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_feeds, container, false);
        listView = (ListView) rootView.findViewById(R.id.list);
        newsFeedModels = new ArrayList<NewsFeedModel>();



        // making fresh volley request and getting json
        JsonArrayRequest jsonReq = new JsonArrayRequest(Request.Method.GET,
                URL_FEED, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                VolleyLog.d(TAG, "Response: " + response.toString());


                if (response != null) {
                    parseJsonFeed(response);

                }
                //hideProgressDialog();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());

            }
        });

        // Adding request to volley request queue
        AppController.getInstance().addToRequestQueue(jsonReq);
        listAdapter = new NewsFeedAdapter(getActivity(), newsFeedModels);
        listView.setAdapter(listAdapter);
        return rootView;
    }

    private void parseJsonFeed(JSONArray response) {
        try {

            for (int i = 0; i < response.length(); i++) {
                JSONObject feedObj = (JSONObject) response.get(i);

                NewsFeedModel item = new NewsFeedModel();
                item.setId(feedObj.getString("id"));
                item.setProfilename(feedObj.getString("feed_name"));
                item.setTimestamp(feedObj.getString("feed_date"));
                item.setDescription(feedObj.getString("feed_description"));

                item.setProfileimage(feedObj.getString("feed_pic"));

                item.setNewsfeedimage(feedObj.getString("feed_image"));

                newsFeedModels.add(item);
            }


            // notify data changes to list adapater
            listAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();

        }
    }

}
