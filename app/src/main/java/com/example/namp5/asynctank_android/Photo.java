package com.example.namp5.asynctank_android;

/**
 * Created by namp5 on 11/26/2018.
 */

public class Photo {
    private String mTitle;
    private String mPath;

    public Photo(String mTitle, String mPath) {
        this.mTitle = mTitle;
        this.mPath = mPath;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmPath() {
        return mPath;
    }

    public void setmPath(String mPath) {
        this.mPath = mPath;
    }
}
