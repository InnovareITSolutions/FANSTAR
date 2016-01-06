package com.innovare.fanstar;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

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


public class YourFansFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String TAG = YourFansFragment.class.getSimpleName();
    private ListView listView;
    private YourFansAdapter listAdapter;
    private List<YourFanModel> yourFanModels;
    private String URL_FEED = "http://www.json-generator.com/api/json/get/bUMVFgCniq?indent=2";

    public YourFansFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
    }
    @Override
    public void onAttach(Activity activity) {


        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.v("YOURFAN", "CAME HERE1");
        Toast.makeText(getActivity(),"Fragment started",Toast.LENGTH_SHORT).show();
        View rootView = inflater.inflate(R.layout.fragment_yourfans, container, false);

        listView = (ListView) rootView.findViewById(R.id.listview);
        yourFanModels = new ArrayList<YourFanModel>();
        listAdapter = new YourFansAdapter(getActivity(), yourFanModels);
        listView.setAdapter(listAdapter);


// making fresh volley request and getting json
        JsonArrayRequest jsonReq = new JsonArrayRequest(Request.Method.GET,
                URL_FEED, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                VolleyLog.d(TAG, "FANS " + response.toString());
                Log.v("RESPONSE VASTONDI", "VASTONDI");

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



        return rootView;

        
    }

    private void parseJsonFeed(JSONArray response) {
        try {

            for (int i = 0; i < response.length(); i++) {
                JSONObject feedObj = (JSONObject) response.get(i);

                YourFanModel item = new YourFanModel();
                item.setYourfan_name(feedObj.getString("your_fan_name"));

                item.setYourfan_image(feedObj.getString("your_fan_pic"));

                yourFanModels.add(item);
            }


            // notify data changes to list adapater
            listAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();

        }
    }


}
