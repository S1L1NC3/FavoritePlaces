package com.dmd.favoriteplacesjavaversion.activities;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.dmd.favoriteplacesjavaversion.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener {

    private GoogleMap mMap;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private int stepCount = 0;
    private String address = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        mMap.clear();
        mMap.addMarker(new MarkerOptions().position(latLng).title("BAM BAM ŞİKİ ŞİKİ BAM BAM"));
        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
        address = "";
        try {
            List<Address> addressList = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            if (addressList != null && addressList.size() > 0) {
                if (addressList.get(0).getThoroughfare() != null) {
                    address += addressList.get(0).getThoroughfare();
                    if (addressList.get(0).getSubThoroughfare() != null) {
                        address += addressList.get(0).getSubThoroughfare();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i("LocationTrack", "onLocationChanged: " + address);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapLongClickListener(this);

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                /*mMap.clear();
                LatLng userLocation = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation,15f));

                Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                try {
                    List<Address> addressList = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                    if (addressList != null && addressList.size() > 0){
                        address += addressList.get(0).getSubLocality();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.i("LocationTrack", "onLocationChanged: " + address);*/
                /*stepCount += 1;
                Toast.makeText(getApplicationContext(),"Your location changed!",Toast.LENGTH_SHORT).show();
                LatLng userLocation = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.addMarker(new MarkerOptions().position(userLocation).title("Your Past Location: " + stepCount));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation,15f));*/
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        //23 üstüyse izin istiyecek değilse zaten otomatik alıcak
        if(Build.VERSION.SDK_INT >= 23){
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
            } else {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0f, locationListener);
                Toast.makeText(this,"Permission Granted",Toast.LENGTH_SHORT).show();
                Location lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                LatLng userLastLocation = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
                //mMap.addMarker(new MarkerOptions().position(userLastLocation).title("Marker in EiffelTower"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLastLocation,15f));
            }
        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0f, locationListener);
            Location lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            LatLng userLastLocation = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
            mMap.addMarker(new MarkerOptions().position(userLastLocation).title("Marker in EiffelTower"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLastLocation,15f));
        }
        /*
        LatLng eiffelTower = new LatLng(48.858541, 2.294154);
        mMap.addMarker(new MarkerOptions().position(eiffelTower).title("Marker in EiffelTower"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(eiffelTower,15f));*/
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length > 0){
            if (requestCode == 1){
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0f, locationListener);
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}