 package com.Satway.gpstracker;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
//import android.view.animation.Animation;
//import android.view.animation.AnimationUtils;
import android.widget.Button;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
//import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

//Map activity page
 public class MapActivity2 extends FragmentActivity implements OnMapReadyCallback  {

     private String imei;
    private static final String maps ="http://68.183.92.233:8000/api/android_map_view";
    String latitude,longitude,vh;
    GoogleMap map;
    Button btn, bnt,but;
     private String TAG="MapActivity2";

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map2);

        Intent intent = getIntent();

        imei = intent.getStringExtra("imei_c");
        Log.d(TAG, "onCreate: imeino"+imei);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

     @Override
     public void onMapReady(GoogleMap googleMap) {
         boolean success = googleMap.setMapStyle(
                 MapStyleOptions.loadRawResourceStyle(
                         this, R.raw.style_json));
         RequestQueue MyRequestQueue = Volley.newRequestQueue(MapActivity2.this);
         map = googleMap;
         googleMap.getUiSettings().setZoomControlsEnabled(true);
         googleMap.getUiSettings().setRotateGesturesEnabled(true);
         googleMap.getUiSettings().setScrollGesturesEnabled(true);
         googleMap.getUiSettings().setTiltGesturesEnabled(true);
         btn = (Button) findViewById(R.id.btn);
         bnt = (Button) findViewById(R.id.bnt);
         but = (Button) findViewById(R.id.but);

         final StringRequest MyStringRequest = new StringRequest(Request.Method.POST,maps, new com.android.volley.Response.Listener<String>() {
             @Override

             public void onResponse(String response) {
                                System.out.println(response);
                 try {

                     JSONObject obj = new JSONObject(response);

                     latitude =obj.getString("latitude");
                     longitude=obj.getString("longitude");
                     vh=obj.getString("vehicle_no");


                     LatLng Maharasthhra = new LatLng(Float.parseFloat(latitude), Float.parseFloat(longitude));
                     map.addMarker(new MarkerOptions().position(Maharasthhra).title("Vehicle No:  "+vh));
                     map.moveCamera(CameraUpdateFactory.newLatLng(Maharasthhra));
                     CameraUpdate center= CameraUpdateFactory.newLatLng(Maharasthhra);
                     CameraUpdate zoom=CameraUpdateFactory.zoomTo(16);
                     map.moveCamera(center);
                     map.animateCamera(zoom);
                     System.out.println(latitude);
                     System.out.println(longitude);
                     System.out.println(vh);



                 } catch (Exception e) {

                 }
                 btn.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {

                         LatLng Maharasthhra = new LatLng(Float.parseFloat(latitude), Float.parseFloat(longitude));
                         map.addMarker(new MarkerOptions().position(Maharasthhra).title("Vehicle No"+vh));
                         map.moveCamera(CameraUpdateFactory.newLatLng(Maharasthhra));
                         CameraUpdate center= CameraUpdateFactory.newLatLng(Maharasthhra);
                         CameraUpdate zoom=CameraUpdateFactory.zoomTo(16);

                         map.moveCamera(center);
                         map.animateCamera(zoom);
                         map.setMapType(map.MAP_TYPE_SATELLITE);
                         map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                         System.out.println(latitude);
                         System.out.println(longitude);
                         System.out.println(vh);


                     }
                 });
                 bnt.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {

                         LatLng Maharasthhra = new LatLng(Float.parseFloat(latitude), Float.parseFloat(longitude));
                         map.addMarker(new MarkerOptions().position(Maharasthhra).title("Vehicle No"+vh));
                         map.moveCamera(CameraUpdateFactory.newLatLng(Maharasthhra));
                         CameraUpdate center= CameraUpdateFactory.newLatLng(Maharasthhra);
                         CameraUpdate zoom=CameraUpdateFactory.zoomTo(16);


                         map.moveCamera(center);
                         map.animateCamera(zoom);
                         map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                         System.out.println(latitude);
                         System.out.println(longitude);
                         System.out.println(vh);





                     }
                 });

                 but.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {

                         LatLng Maharasthhra = new LatLng(Float.parseFloat(latitude), Float.parseFloat(longitude));
                         map.addMarker(new MarkerOptions().position(Maharasthhra).title("Vehicle No"+vh));
                         map.moveCamera(CameraUpdateFactory.newLatLng(Maharasthhra));
                         CameraUpdate center= CameraUpdateFactory.newLatLng(Maharasthhra);
                         CameraUpdate zoom=CameraUpdateFactory.zoomTo(16);

                         map.moveCamera(center);
                         map.animateCamera(zoom);

                         map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                         System.out.println(latitude);
                         System.out.println(longitude);
                         System.out.println(vh);




                     }
                 });
             }




         }, new com.android.volley.Response.ErrorListener() { //Create an error listener to handle errors appropriately.
             @Override
             public void onErrorResponse(VolleyError error) {

             }
         }) {
             protected Map<String, String> getParams() {
                 Map<String, String> MyData = new HashMap<String, String>();
                 MyData.put("imei", imei);
                 return MyData;
             }
         };


         MyRequestQueue.add(MyStringRequest);




     }
 }





























