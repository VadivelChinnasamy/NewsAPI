package com.newssource;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.newssource.Adapter.DetailAdapter;
import com.newssource.Constant.MySingleton;
import com.newssource.Constant.Util;
import com.newssource.DataClass.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DetailPageActivity extends AppCompatActivity {
    private String TAG = "res", url, logoview;
    private RecyclerView recyclerViewDetail;

    private LinearLayoutManager linearLayoutManager;

    private DetailAdapter adapterDetail;

    private List<News> dataDetail;
    private ProgressBar progressBar;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        url = Util.DETAIL + getIntent().getStringExtra(Util.ID) + "&apiKey=" + Util.APIKEY;
        Log.e("**********", "" + url);
        logoview = getIntent().getStringExtra(Util.LOGO);
        title = findViewById(R.id.title);
        title.setText(getIntent().getStringExtra(Util.NAME));
        recyclerViewDetail = (RecyclerView) findViewById(R.id.recycler_view_detail);
        progressBar = findViewById(R.id.progressBar2);
        dataDetail = new ArrayList<>();

        if (Util.isConnected(DetailPageActivity.this)) {
            mLoadDetailPage();
        } else {
            Toast.makeText(this, "check your internet connection", Toast.LENGTH_SHORT).show();
        }

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewDetail.setLayoutManager(linearLayoutManager);

        adapterDetail = new DetailAdapter(this, dataDetail);
        recyclerViewDetail.setAdapter(adapterDetail);
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void mLoadDetailPage() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // Log.d(TAG, response);
                        JSONObject jObject = null;
                        try {
                            jObject = new JSONObject(response);
                            JSONArray jsonarray = new JSONArray(jObject.getString("articles"));
                            for (int i = 0; i < jsonarray.length(); i++) {

                                JSONObject jsonobject = jsonarray.getJSONObject(i);
                                News data = new News(jsonobject.getString("title")
                                        , jsonobject.getString("author"),
                                        jsonobject.getString("urlToImage"),
                                        logoview, jsonobject.getString("publishedAt"),
                                        jsonobject.getString("description"));

                                dataDetail.add(data);
                                adapterDetail.notifyDataSetChanged();
                                progressBar.setVisibility(View.GONE);
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
                            Log.d(TAG, error.toString());
                        }
                    }
                }

        );

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }

}
