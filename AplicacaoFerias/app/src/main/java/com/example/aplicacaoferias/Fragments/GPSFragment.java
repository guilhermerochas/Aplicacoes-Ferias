package com.example.aplicacaoferias.Fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.aplicacaoferias.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import java.util.Objects;
import java.util.concurrent.Executor;

public class GPSFragment extends Fragment {

    private String txtLatitude, txtLongitude;
    private TextView latitude, longitude;
    private FusedLocationProviderClient fusedLocationClient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_gps, container, false);

        latitude = v.findViewById(R.id.userLatitude);
        longitude = v.findViewById(R.id.userLongitude);
        Button exibeCoordenadas = v.findViewById(R.id.exibe_posicao);

        txtLatitude = latitude.getText().toString();
        txtLongitude = longitude.getText().toString();

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(Objects.requireNonNull(getContext()));

        exibeCoordenadas.setOnClickListener(view -> {
            if (ActivityCompat.checkSelfPermission(Objects.requireNonNull(getActivity()), Manifest.permission.ACCESS_FINE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Objects.requireNonNull(getActivity()), new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            }
            else{
                fusedLocationClient.getLastLocation()
                        .addOnSuccessListener(getActivity(), location -> {
                            onLocationChanged(location);
                        });
            }
        });
        return v;
    }

    @SuppressLint("SetTextI18n")
    public void onLocationChanged(Location location) {
        latitude.setText(txtLatitude + " " + location.getLatitude());
        longitude.setText(txtLongitude + " " + location.getLongitude());
    }
}