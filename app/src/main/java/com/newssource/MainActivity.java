package com.newssource;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.newssource.Adapter.CustomAdapter;
import com.newssource.Constant.MySingleton;
import com.newssource.Constant.Util;
import com.newssource.DataClass.News;
import com.newssource.Interfaces.OnClickCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnClickCallback {

    private RecyclerView mRecycleView;
    private ArrayList<News> newsData;
    private GridLayoutManager gridLayoutManager;
    private CustomAdapter adapter;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecycleView = findViewById(R.id.recycle_view);
        mProgressBar = findViewById(R.id.progressBar);

        newsData = new ArrayList<>();
        if (Util.isConnected(MainActivity.this)) {
            getNews();
        } else {
            Toast.makeText(this, "check your internet connection", Toast.LENGTH_SHORT).show();
        }
        gridLayoutManager = new GridLayoutManager(this, 3);
        mRecycleView.setLayoutManager(gridLayoutManager);

        adapter = new CustomAdapter(this, newsData);
        mRecycleView.setAdapter(adapter);
        adapter.setCallBack(this);

    }

    private void getNews() {


        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                Util.BASEURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject jObject = null;
                        try {
                            jObject = new JSONObject(response);
                            JSONArray jsonarray = new JSONArray(jObject.getString("sources"));
                            for (int i = 0; i < jsonarray.length(); i++) {

                                JSONObject jsonobject = jsonarray.getJSONObject(i);
                                News data = new News(jsonobject.getString("id"), jsonobject.getString("name"), jsonobject.getJSONObject("urlsToLogos").getString("large"));

                                newsData.add(data);
                                adapter.notifyDataSetChanged();
                                mProgressBar.setVisibility(View.GONE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error != null) {
                            Log.d("***ERROR*", error.toString());
                            Toast.makeText(getApplicationContext(), "Please try again later", Toast.LENGTH_LONG).show();
                        }
                    }
                }

        );

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }

    @Override
    public void OnClick(int position) {
        Intent intent = new Intent(MainActivity.this, DetailPageActivity.class);
        intent.putExtra(Util.ID, newsData.get(position).getId());
        intent.putExtra(Util.LOGO, newsData.get(position).getImage_link());
        intent.putExtra(Util.NAME, newsData.get(position).getName());
        startActivity(intent);
    }
}
