package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import com.android.volley.RequestQueue;

import com.android.volley.toolbox.StringRequest;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;



public class MainActivity extends AppCompatActivity  implements OnMapReadyCallback {


    GoogleMap gMap;
    private static MainActivity mInstance;
    public static final String TAG = MainActivity.class
            .getSimpleName();

    private RequestQueue requestQueue;
    private static final String URL = "http://carparking1.online/GetParkingAreas.php";
    private StringRequest request;
    private ParkingArea P;

    Button logbtn ;
    List <Polygon> polygonList = new ArrayList<>();
    List <LatLng> latLngList = new ArrayList<>();
    List <Marker> markerList = new ArrayList<>();
    public static List <ParkingArea> ParkingAreaList = new ArrayList<>();
    int red=0,green=0,blue=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mInstance = this;
        logbtn = findViewById(R.id.AdminLogin);
        //initialize SupportMapFragment
        SupportMapFragment supportMapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.google_map);
        supportMapFragment.getMapAsync(this);

        logbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
            }
        });



    }



    @Override
    public void onMapReady(GoogleMap googleMap) {


    gMap = googleMap;

    //to move camera frame to KAU Frame by latlng
        LatLng KAU = new LatLng(21.493806568005414,39.238739604235434);

        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom((KAU), 15));


        //starting thread to fill the parking areas list
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try  {

                    try {
                        String data ="";
                        URL url = new URL("http://carparking1.online/GetParkingAreas.php");
                        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                        InputStream inputStream = httpURLConnection.getInputStream();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                        String line = "";
                        while(line != null){
                            line = bufferedReader.readLine();
                            data = data + line;
                        }

                        JSONArray JA = new JSONArray(data);
                        for(int i =0 ;i < JA.length(); i++){
                            JSONObject JO = (JSONObject) JA.get(i);
                            ParkingArea p =new ParkingArea();
                            p.setID(Integer.parseInt((String) JO.get("ID")));

                            p.setLat1(Double.parseDouble((String) JO.get("lat1")));
                            p.setLong1(Double.parseDouble((String) JO.get("long1")));

                            p.setLat2(Double.parseDouble((String) JO.get("lat2")));
                            p.setLong2(Double.parseDouble((String) JO.get("long2")));

                            p.setLat3(Double.parseDouble((String) JO.get("lat3")));
                            p.setLong3(Double.parseDouble((String) JO.get("long3")));

                            p.setLat4(Double.parseDouble((String) JO.get("lat4")));
                            p.setLong4(Double.parseDouble((String) JO.get("long4")));

                            p.setColumn(Integer.parseInt((String) JO.get("Columns")));
                            p.setRows(Integer.parseInt((String) JO.get("Rows")));
                            ParkingAreaList.add(p);



                        }







                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();





        //Timer for Synchronized fetshing data and it can't be Asynchronized
        new CountDownTimer(3000, 500) {
            public void onFinish() {

                for(int i = 0; i < ParkingAreaList.size(); i++){
                    Polygon polygon = null;
                    double latitude1 = Double.parseDouble(ParkingAreaList.get(i).getLat1());
                    double longitude1 = Double.parseDouble(ParkingAreaList.get(i).getLong1());

                    double latitude2 = Double.parseDouble(ParkingAreaList.get(i).getLat2());
                    double longitude2 = Double.parseDouble(ParkingAreaList.get(i).getLong2());

                    double latitude3 = Double.parseDouble(ParkingAreaList.get(i).getLat3());
                    double longitude3 = Double.parseDouble(ParkingAreaList.get(i).getLong3());

                    double latitude4 = Double.parseDouble(ParkingAreaList.get(i).getLat4());
                    double longitude4 = Double.parseDouble(ParkingAreaList.get(i).getLong4());
                    LatLng l1 = new LatLng(latitude1,longitude1);
                    LatLng l2 = new LatLng(latitude2,longitude2);
                    LatLng l3 = new LatLng(latitude3,longitude3);
                    LatLng l4 = new LatLng(latitude4,longitude4);
                    latLngList.add(l1);
                    latLngList.add(l2);
                    latLngList.add(l3);
                    latLngList.add(l4);

                    //Create PolygonOptions
                    PolygonOptions polygonOptions = new PolygonOptions().addAll(latLngList)
                            .clickable(true);

                    polygonList.add(i,gMap.addPolygon(polygonOptions));
                    //Set Polygon Stroke Color
                    polygonList.get(i).setStrokeColor(Color.rgb(254, 216, 177));
                    //Fill Polygon Color
                    polygonList.get(i).setFillColor(Color.rgb(254, 216, 177));
                    latLngList.clear();

                }
            }

            public void onTick(long millisUntilFinished) {
                // millisUntilFinished    The amount of time until finished.
            }
        }.start();





    gMap.setOnPolygonClickListener(new GoogleMap.OnPolygonClickListener() {
        @Override
        public void onPolygonClick(Polygon polygon) {
            Intent intent = new Intent(MainActivity.this,UserParkingSlotsUI.class);
            ParkingArea p =ParkingAreaList.get(Integer.parseInt(polygon.getId().substring(2)));
            intent.putExtra("p",p);
            startActivity(intent);







        }
    });
    }


}
