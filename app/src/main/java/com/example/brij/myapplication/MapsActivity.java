package com.example.brij.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import Modules.DirectionFinder;
import Modules.DirectionFinderListener;
import Modules.Route;

//source for permission check and map drawing: stackoverflow.com and Android Documentation

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, DirectionFinderListener, LocationListener {

    private GoogleMap mMap;
    private Button btnFindPath;
    private EditText etOrigin;
    private EditText etDestination;
    private List<Marker> originMarkers = new ArrayList<>();
    private List<Marker> destinationMarkers = new ArrayList<>();
    private List<Polyline> polylinePaths = new ArrayList<>();
    private ProgressDialog progressDialog;
    //private FusedLocationProviderClient mFusedLocationClient;
    private GoogleMap mGoogleMap;
    private String TAG;
    protected LocationManager locationManager;
    protected LocationListener locationListener;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    String longtitue;
    String latitude;
    String address;
    String location;
    String current_long;
    String current_lat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                address = null;
                location = null;
                current_lat = null;
                current_long = null;
            } else {
                address= extras.getString("address");
                location = extras.getString("location");
                current_long = extras.getString("current_long");
                current_lat = extras.getString("current_lat");

            }
        } else {
            address= (String) savedInstanceState.getSerializable("address");
            location= (String) savedInstanceState.getSerializable("location");
            current_long= (String) savedInstanceState.getSerializable("current_long");
            current_lat= (String) savedInstanceState.getSerializable("current_lat");

        }
        Log.d(TAG, "Address is:>>>>>>>>>>>>"+address);
        Log.d(TAG, "Location is:>>>>>>>>>>>>"+location);
        Log.d(TAG, "Current long is:>>>>>>>>>>>>"+current_long);

        btnFindPath = (Button) findViewById(R.id.btnFindPath);
        etOrigin = (EditText) findViewById(R.id.etOrigin);
        etDestination = (EditText) findViewById(R.id.etDestination);
        etOrigin.setText(address);
        etDestination.setText(location);
        //fusedLocation test
        // locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

//        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
        //locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 0, this);
        Log.d(TAG, "****inside onCreate*****");
        sendRequest();
        btnFindPath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "%%%%%%inside onClickListener%%%%%%");
                sendRequest();
            }
        });
    }


    private void sendRequest() {
        //etOrigin.setText(address);
        String origin = etOrigin.getText().toString();
        String destination = etDestination.getText().toString();
        Log.d(TAG, "%%%%%%inside sendRequest%%%%%%");
        if (origin.isEmpty()) {
            Toast.makeText(this, "Please enter origin address!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (destination.isEmpty()) {
            Toast.makeText(this, "Please enter destination address!", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            new DirectionFinder(this, origin, destination).execute();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.getUiSettings().setCompassEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap = googleMap;
        mGoogleMap = googleMap;
        enableUserLocation();
        LatLng csula = new LatLng(Double.parseDouble(current_lat), Double.parseDouble(current_long));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(csula, 18));
        originMarkers.add(mMap.addMarker(new MarkerOptions()
                .title("you're here!")
                .position(csula)));
        Log.d(TAG, "####### INSIDE onMapReady#######");


//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            ActivityCompat.requestPermissions(MapsActivity.this, new String[] {
//                    Manifest.permission.ACCESS_FINE_LOCATION
//            }, 1);
//            return;
//        }
        //mMap.setMyLocationEnabled(true);
        //enableUserLocation();
    }
    private void enableUserLocation() {
        Log.d(TAG, "*****INSIDE FIRST METHOD*******");
        enableUserLocation(MapsActivity.this);
    }
    private void enableUserLocation(Activity activity){
        Log.d(TAG, "*****INSIDE PROPER METHOD*******");
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[] {
                    android.Manifest.permission.ACCESS_FINE_LOCATION
            }, 1);
        } else if(mGoogleMap != null){
            //mGoogleMap.setUserLocationEnabled(true);
            mGoogleMap.setMyLocationEnabled(true);
            if (locationManager != null) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000L, 0.0f, this);

                Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                if (location != null) {
                    LatLng lastKnownLatLng = new LatLng(location.getLatitude(), location.getLongitude());
                    Log.d(TAG, "^^^^^^^Lat is: "+location.getLatitude() );
                    mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastKnownLatLng, 15.0f));
                }
                else{
                    Log.d(TAG, "----------LOCATION IS NULL------------");
                }
            }
        }
    }


    @Override
    public void onDirectionFinderStart() {
        progressDialog = ProgressDialog.show(this, "Please wait.",
                "Finding direction..!", true);

        if (originMarkers != null) {
            for (Marker marker : originMarkers) {
                marker.remove();
            }
        }

        if (destinationMarkers != null) {
            for (Marker marker : destinationMarkers) {
                marker.remove();
            }
        }

        if (polylinePaths != null) {
            for (Polyline polyline:polylinePaths ) {
                polyline.remove();
            }
        }
    }

    @Override
    public void onDirectionFinderSuccess(List<Route> routes) {
        progressDialog.dismiss();
        polylinePaths = new ArrayList<>();
        originMarkers = new ArrayList<>();
        destinationMarkers = new ArrayList<>();

        for (Route route : routes) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(route.startLocation, 12));
            ((TextView) findViewById(R.id.tvDuration)).setText(route.duration.text);
            String distanceString = route.distance.text;
            distanceString = distanceString.replaceAll(",","");
            distanceString = distanceString.replaceAll(" mi","");
            distanceString = distanceString.replaceAll(" ", "");
            Log.d(TAG, "------------Distance debugger: "+distanceString);
            if(Double.parseDouble(distanceString)>100.00){

                ((TextView) findViewById(R.id.tvDistance)).setText(route.distance.text);
                alertUser();
                Log.d(TAG, "too much long route>>>>>>>>>>>>>>");
            }
            else{
                ((TextView) findViewById(R.id.tvDistance)).setText(route.distance.text);
            }

            originMarkers.add(mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.start_blue))
                    .title(route.startAddress)
                    .position(route.startLocation)));
            destinationMarkers.add(mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.end_green))
                    .title(route.endAddress)
                    .position(route.endLocation)));

            PolylineOptions polylineOptions = new PolylineOptions().
                    geodesic(true).
                    color(Color.BLUE).
                    width(10);

            for (int i = 0; i < route.points.size(); i++)
                polylineOptions.add(route.points.get(i));

            polylinePaths.add(mMap.addPolyline(polylineOptions));
        }
    }

    private void alertUser(){
        Toast.makeText(MapsActivity.this,"Distance is more than 100 miles!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLocationChanged(Location location) {
        //double latitude = location.getLatitude();
        //Log.d(TAG, "+++++++++lat is: "+latitude);
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE)
            return;
        if(permissionWasGranted(permissions, grantResults, android.Manifest.permission.ACCESS_FINE_LOCATION)) {
            enableUserLocation();
        } else {
            Log.e(TAG, "Permission wasn't granted");
        }
    }
    // verify that the permission granted is the location permission
    public static boolean permissionWasGranted(String[] permissions, int[] results, String permission) {
        for (int i = 0; i < permissions.length; i++) {
            if (permission.equals(permissions[i]))
                return results[i] == PackageManager.PERMISSION_GRANTED;
        }
        return false;
    }
    @Override
    public void onBackPressed() {


            super.onBackPressed();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}