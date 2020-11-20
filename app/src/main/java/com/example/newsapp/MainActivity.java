package com.example.newsapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private NewsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        fetchData();

        adapter = new NewsAdapter();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void fetchData() {
        String url = "https://newsapi.org/v2/top-headlines?country=in&apiKey=5499b181da704537a7e8d93fb2c4be75";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,
                null, response -> {

            try {
                List<News> newsList = extractNewsFromJson(response);
                adapter.updateNews(newsList);
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(this, "Unable to extract data", Toast.LENGTH_SHORT).show();
            }

        }, error ->{
                Toast.makeText(MainActivity.this,
                        "Unable to fetch news ", Toast.LENGTH_SHORT).show();}){

            /**
             * Passing some request headers
             */
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("X-Api-Key", "5499b181da704537a7e8d93fb2c4be75");
                return headers;
            }
        };


        MySingleton.getInstance(this.getApplicationContext()).addToRequestQueue(jsonObjectRequest);
    }

    private List<News> extractNewsFromJson(JSONObject response) throws JSONException {

        List<News> newsList = new ArrayList<>();
        JSONArray jsonArray = response.getJSONArray("articles");

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            News news = new News(
                    jsonObject.getString("title"),
                    jsonObject.getString("author"),
                    jsonObject.getString("url"),
                    jsonObject.getString("urlToImage")
            );

            newsList.add(news);
        }

        return newsList;
    }
}