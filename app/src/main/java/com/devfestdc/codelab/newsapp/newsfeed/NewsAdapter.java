package com.devfestdc.codelab.newsapp.newsfeed;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private ArrayList<Article> mArticleList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mHeadline;
        public TextView mSnippet;
        public ImageView mImageView;

        public ViewHolder(View v) {
            super(v);
            mHeadline = (TextView) v.findViewById(R.id.article_headline);
            mSnippet = (TextView) v.findViewById(R.id.article_detail);
            mImageView = (ImageView) v.findViewById(R.id.article_image);
        }
    }

    public NewsAdapter() {
        mArticleList = new ArrayList<>();
    }

    public void setArticles(String articleString) {
        JSONArray articles = null;
        try {
            articles = new JSONObject(articleString).getJSONArray("items");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mArticleList = new ArrayList<>();

        for (int i = 0; i < articles.length(); i++) {
            try {
                JSONObject articleJson = articles.getJSONObject(i);
                Article article = new Article(articleJson.getString("title"), articleJson.getString("summary"),
                        articleJson.getJSONObject("media").getJSONArray("images").getJSONObject(0).getString("url"));

                mArticleList.add(article);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        notifyDataSetChanged();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
            int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mHeadline.setText(mArticleList.get(position).getmHeadline());
        holder.mSnippet.setText(mArticleList.get(position).getmSummary());
        Picasso.with(holder.mImageView.getContext()).load(mArticleList.get(position).getmImageUrl()).into(holder.mImageView);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mArticleList.size();
    }
}
