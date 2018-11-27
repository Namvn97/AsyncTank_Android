package com.example.namp5.asynctank_android;

import android.os.AsyncTask;
import android.os.Environment;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by namp5 on 11/26/2018.
 */

public class PhotoAsyncTank extends AsyncTask<String , Void , ArrayList<Photo>> {
    public static final int ZERO_INDEX = 0;
    public static final int SPE_INDEX = 19;
    @Override
    protected ArrayList<Photo> doInBackground(String... strings) {
        return getPhotos(strings[ZERO_INDEX]);
    }

    public ArrayList<Photo> getPhotos(String path) {
        String pathDir = Environment.getExternalStorageDirectory().getPath() + path;
        ArrayList<Photo> photos = new ArrayList<>();
        final File fileDir = new File(pathDir);
        File[] listFile = fileDir.listFiles();
        for (File f : listFile) {
            if (f.isFile()) {
                photos.add(new Photo(f.getPath(), f.getName()));
            } else if (f.isDirectory()) {
                photos.addAll(getPhotos(f.getPath().substring(SPE_INDEX)));
            }
        }
        return photos;
    }
}
