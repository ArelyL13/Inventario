package com.example.controlv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.VideoView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity2 extends AppCompatActivity {
BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setSelectedItemId(R.id.home);
        VideoView mivdeo = findViewById(R.id.videoView1);
        String videop="android.resource://"+ getPackageName()+"/"+R.raw.control;
        Uri uri= Uri.parse(videop);
        mivdeo.setVideoURI(uri);

        MediaController mediaController= new MediaController(this);
        mivdeo.setMediaController(mediaController);
        mediaController.setAnchorView(mivdeo);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.dashboard:
                        startActivity(new Intent(getApplicationContext(),Dashboard.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        return true;
                    case R.id.inventario:
                        startActivity(new Intent(getApplicationContext(),Inventario.class));
                        overridePendingTransition(0,0);
                        return true;



                }
                return false;
            }
        });


    }
}