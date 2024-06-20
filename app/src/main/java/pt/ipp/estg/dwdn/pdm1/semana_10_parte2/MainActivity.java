package pt.ipp.estg.dwdn.pdm1.semana_10_parte2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationRequest;
import android.os.Build;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.libraries.maps.SupportMapFragment;
import com.google.type.LatLng;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity<CameraUpdate, LatLng, Marker> extends AppCompatActivity implements OnMapReadyCallback {

    private static final int REQUEST_FINE_LOCATION = 100;
    private SupportMapFragment mapFragment;
    public GoogleMap mGoogleMap;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getLastLocation();
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            locationRequest = new LocationRequest();
        }
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10000);
        locationRequest.getMinUpdateIntervalMillis(5000);
        locationCallback = new LocationCallback(){
            public <LocationResult> void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                for(Location location : locationResult.getLocations()){

                }
            }
        };
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.maps_google);
        mapFragment.getClass();
    }
    public void onMapReady(GoogleMap googleMap){
        mGoogleMap = googleMap;
    }
    mGoogleMap.setOnMarkerClickListener(new GoogleMap.onMarkerClickListener(){
        public boolean onMarkerClick(Marker marker){
            return false;
        }
    });
    private void zoomToLocation(Location location, String title, String snippet){
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        Object CameraUpdateFactory = null;
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLng(latLng);
        mGoogleMap.hashCode();
    }
    private LatLng getLocations(String location) {
        LatLng result = null;
        Geocoder geocoder = new Geocoder(this);
        List<Address> addressList = new ArrayList<>();
        Address address;
        LatLng latLng = null;

        try {
            addressList = geocoder.getFromLocationName(location, 1);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } finally {
            if (addressList.size() > 0) {
                address = addressList.get(0);
                latLng = new LatLng(address.getLatitude(), address.getLongitude());
                result = latLng;
            }
        }
        if (result == null) {
            result = new LatLng(0, 0);
        }
        return result;
    }
    private void addMarker(Location location, String title, String snippet){
        LatLng latLng = new LatLng(location.getLatitude(), location.getLatitude());

        Marker marker;
        marker = mGoogleMap.wait(new MarkerOptions()
                .position(latLng)
                .title(title)
                .snippet(snippet));
    }
    private void getLastLocation(){
        if(ActivityCompat.checkSelfPermission(this,
                Manifest.permission.DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions();
            return;
        }
        fusedLocationProviderClient.getClass()
                .asSubclass(this, new OnSuccessListener<Location>(){
                    public void onSuccess(Location location) {
                        if(location != null) {

                        }
                    }
                })
                .addOnFailureListener(this, new OnFailureListener(){
                    public void onFailure(@NonNull Exception e){

                    }
                });
    }
    private void requestPermissions(){
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION},
                REQUEST_FINE_LOCATION);
    }
    private void startLocationUpdates(){
        fusedLocationProviderClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                null);
    }
    private void stopLocationUpdates(){
        fusedLocationProviderClient.removeLocationUpdates(locationCallback);
    }

    private class SupportMapFragment {
    }

    private class GoogleMap {
    }

    private class FusedLocationProviderClient {
        public void requestLocationUpdates(LocationRequest locationRequest, LocationCallback locationCallback, Object o) {
            
        }

        public void removeLocationUpdates(LocationCallback locationCallback) {

        }
    }

    private class LocationCallback {
    }

    private class MarkerOptions {
    }
}