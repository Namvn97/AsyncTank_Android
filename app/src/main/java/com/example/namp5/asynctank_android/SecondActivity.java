package com.example.namp5.asynctank_android;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class SecondActivity extends AppCompatActivity {
    public static final int REQUEST_READ_EXT = 1;
    public static final int NUMBER_GRID = 3;
    public static final String DEFAULT_PATH = "/Download/";
    private PhotoAdapter mPhotoAdapter;
    private ArrayList<Photo> mPhotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity_main);
        loadData();
        initView();
    }

    private void loadData() {
        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(permissions[PhotoAsyncTank.ZERO_INDEX]) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(permissions, REQUEST_READ_EXT);
            } else loadPhoto();
        }
    }

    private void loadPhoto(){
        try {
            mPhotos = new PhotoAsyncTank().execute(DEFAULT_PATH).get();
        } catch (InterruptedException|ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == REQUEST_READ_EXT) {
            if (grantResults[PhotoAsyncTank.ZERO_INDEX] == PackageManager.PERMISSION_GRANTED) {
               loadPhoto();
            } else if (grantResults[PhotoAsyncTank.ZERO_INDEX] == PackageManager.PERMISSION_DENIED) {
                loadData();
            }
        }
    }

    private void initView (){
        RecyclerView recyclerView = findViewById(R.id.recycler_photos);
        mPhotoAdapter = new PhotoAdapter(mPhotos);
        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), NUMBER_GRID);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mPhotoAdapter);
    }
}
