package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import java.util.ArrayList;
import java.util.List;

public class AddParkingArea extends AppCompatActivity implements OnMapReadyCallback{
    private RequestQueue requestQueue;
    private static final String URL = "http://carparking1.online/AddParkingArea.php";
    GoogleMap gMap;
    private Button bt_done,bt_clear;
    Polygon polygon = null;
    private static AddParkingArea mInstancee;
    List<LatLng> latLngList = new ArrayList<>();
    List <Marker> markerList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_parking_area);
        bt_done = (Button)findViewById(R.id.bt_done);
        bt_clear = (Button)findViewById(R.id.bt_clear);
        ParkingArea p = new ParkingArea();

        //initialize SupportMapFragment
        SupportMapFragment supportMapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.google_map);
        supportMapFragment.getMapAsync(this);




        requestQueue = Volley.newRequestQueue(this);
        bt_done.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){

                //after the button is clicked these lats will stored into ParkingArea variable
                    ParkingArea p = new ParkingArea(1,
                            latLngList.get(0).latitude,latLngList.get(0).longitude,
                            latLngList.get(1).latitude,latLngList.get(1).longitude,
                            latLngList.get(2).latitude,latLngList.get(2).longitude,
                            latLngList.get(3).latitude,latLngList.get(3).longitude);
                    //send it to the next step
                Intent intent = new Intent(AddParkingArea.this,NumberOfSlots.class);
                intent.putExtra("p",p);
                startActivity(intent);






            }

        });

// to clear the display map and the list of markers and latlngs
        bt_clear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //Clear ALL
                if (polygon != null) polygon.remove();
                for (Marker marker : markerList) marker.remove();
                latLngList.clear();
                markerList.clear();
            }
        });
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        gMap.setOnMapClickListener(new GoogleMap.OnMapClickListener(){
            @Override
            public void onMapClick(LatLng latLng){
                //Create MarkerOptions
                MarkerOptions markerOptions = new MarkerOptions().position(latLng);
                //Create Marker
                Marker marker = gMap.addMarker(markerOptions);
                //Add LanLng and Marker
                latLngList.add(latLng);
                markerList.add(marker);
                if (markerList.size() == 4) {
                    //Draw Polyline on Map
                    if (polygon != null)
                        polygon.remove();
                    //Create PolygonOptions
                    PolygonOptions polygonOptions = new PolygonOptions().addAll(latLngList)
                            .clickable(true);
                    polygon = gMap.addPolygon(polygonOptions);
                    //Set Polygon Stroke Color
                    polygon.setStrokeColor(Color.rgb(254, 216, 177));
                    //Fill Polygon Color
                    polygon.setFillColor(Color.rgb(254, 216, 177));
                    markerList.get(0).remove();
                    markerList.get(1).remove();
                    markerList.get(2).remove();
                    markerList.get(3).remove();

                }
            }

        })



        ;}
}
