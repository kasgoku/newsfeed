package com.devfestdc.codelab.newsapp.newsfeed;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private static final String FEED_URL = "https://channel.comet.aol.com/v1/channels/us.primary";

    private NewsAdapter myAdapter;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        RecyclerView rv = (RecyclerView) findViewById(R.id.news_feed);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        myAdapter = new NewsAdapter();
        rv.setAdapter(myAdapter);

        FetchNewsTask fetchTask = new FetchNewsTask();
        fetchTask.execute();
    }

    private class FetchNewsTask extends AsyncTask<Void, Void, String> {
        public FetchNewsTask() {
            super();
        }

        @Override protected void onPostExecute(String articles) {
            super.onPostExecute(articles);

            myAdapter.setArticles(articles);
        }

        @Override protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(FEED_URL);
                StringBuilder builder = new StringBuilder();
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    String line = "";

                    while ((line = br.readLine()) != null)
                        builder.append(line);

                } finally {
                    urlConnection.disconnect();
                }

                return builder.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}
