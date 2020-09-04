package com.dmd.favoriteplacesjavaversion.fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.dmd.favoriteplacesjavaversion.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsFragment extends Fragment implements OnMapReadyCallback {
    private GoogleMap mMap;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment == null){
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            mapFragment = SupportMapFragment.newInstance();
            ft.replace(R.id.map, mapFragment).commit();
        }
        mapFragment.getMapAsync(this);
        return inflater.inflate(R.layout.fragment_map, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            getContext(), R.raw.mapstyle));

            if (!success) {
                Log.e("MapStyling", "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e("MapStyling", "Can't find style. Error: ", e);
        }


        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        /*mMap.addMarker(new MarkerOptions().position(locationChanged).title("Marker in locationChanged"));
                LatLng locationChanged = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.addMarker(new MarkerOptions().position(locationChanged).title("Marker in locationChanged"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(locationChanged,2f));*/
        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                LatLng locationChanged = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(locationChanged, 15f));

                /*mMap.addMarker(new MarkerOptions().position(locationChanged).title("Marker in locationChanged"));
                LatLng locationChanged = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.addMarker(new MarkerOptions().position(locationChanged).title("Marker in locationChanged"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(locationChanged,2f));*/
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

        if(Build.VERSION.SDK_INT >= 23){
            if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                //İzin iste
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
            } else {
                //İzin verilmiş
                //Location will check in every 30 seconds and 100 meters
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1,100f, locationListener);
                mMap.setMyLocationEnabled(true);
                Location lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                LatLng userLastLocation = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLastLocation,15f));
            }
        } else {
            //No need for permission :D
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1,100f, locationListener);

            mMap.setMyLocationEnabled(true);
            Location lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            LatLng userLastLocation = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLastLocation,15f));
        }
    }

}
