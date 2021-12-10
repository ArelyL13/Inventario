package com.example.controlv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Dashboard extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
 Button button;
 Button btnCam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setSelectedItemId(R.id.home);
        button= findViewById(R.id.Storage);
        btnCam=findViewById(R.id.Camara);
        btnCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Camara.class);
                startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), FotosFB.class);
                startActivity(intent);
            }
        });
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.dashboard:

                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),MainActivity2.class));
                        overridePendingTransition(0,0);
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