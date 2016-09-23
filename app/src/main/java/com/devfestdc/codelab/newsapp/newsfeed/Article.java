package com.devfestdc.codelab.newsapp.newsfeed;

/**
 * Created by kqureshi14 on 9/23/16.
 */

public class Article {
    private String mHeadline;
    private String mSummary;
    private String mImageUrl;

    public Article(String headline, String summary, String imageUrl) {
        this.mHeadline = headline;
        this.mSummary = summary;
        this.mImageUrl = imageUrl;
    }

    public String getmHeadline() {
        return mHeadline;
    }

    public void setmHeadline(String mHeadline) {
        this.mHeadline = mHeadline;
    }

    public String getmSummary() {
        return mSummary;
    }

    public void setmSummary(String mSummary) {
        this.mSummary = mSummary;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }
}
