package com.excercise.geofun;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Random;

public class ActivityInteractive extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap map;
    DatabaseHelper dbh;
    TextView countryDisplay;
    Random rng = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDB();
        initUI();
    }

    public void initDB(){
        dbh = new DatabaseHelper(this, this);
    }

    private void initUI(){
        setContentView(R.layout.activity_interactive);
        countryDisplay = findViewById(R.id.country);
        setQuestion();
        returnToMainMenu();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void setQuestion(){
        int i = rng.nextInt(5);
        dbh.getCountry(i, new CountryQueryResultListener() {
            @Override
            public void onResult(Country country) {
                countryDisplay.setText(country.getName());
            }
        });
    }

    public void returnToMainMenu() {
        FloatingActionButton interactiveBackButton = findViewById(R.id.interactiveBackButton);
        interactiveBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityInteractive.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        LatLng rgbg = new LatLng(49, 12);
        map.addMarker(new MarkerOptions().position(rgbg).title("Default Markierung Regensburg"));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(rgbg, 3.0f));
    }
}
