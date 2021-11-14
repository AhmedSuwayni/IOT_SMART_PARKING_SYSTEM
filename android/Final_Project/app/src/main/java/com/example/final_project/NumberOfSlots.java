package com.example.final_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import androidx.appcompat.app.AppCompatActivity;


public class NumberOfSlots extends AppCompatActivity {
    private RequestQueue requestQueue;
    private static final String URL = "http://carparking1.online/AddParkingArea.php";
    private StringRequest request;
    NumberPicker nc , np;
    Button Done,Cancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.number_of_slots);
        ParkingArea p = getIntent().getParcelableExtra("p");
        nc = findViewById(R.id.picker);
        np = findViewById(R.id.picker2);
        Done = findViewById(R.id.AddPAA);
        Cancel = findViewById(R.id.AddPSS);

        nc.setMinValue(1);
        nc.setMaxValue(1000);
        np.setMinValue(1);
        np.setMaxValue(10000);
//to set number picer for number of parkings and number of slots
        nc.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                p.setColumn(i1);
                System.out.println(p.getColumn());
            }
        });

        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                p.setNumberOfParkingSlots(i1);
            }
        });
        requestQueue = Volley.newRequestQueue(this);
        Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            System.out.println("helo");
                            JSONObject jsonObject = new JSONObject(response);
                            if(jsonObject.names().get(0).equals("success")){

                                Toast.makeText(getApplicationContext(),"The Parking Area ID :  "+jsonObject.getString("success"),Toast.LENGTH_SHORT).show();
                                p.setID(Integer.parseInt(jsonObject.getString("success")));
                                //pass the carparking area and start the new activity
                                Intent intent = new Intent(NumberOfSlots.this,AdminParkingSlotsUI.class);
                                intent.putExtra("p",p);
                                startActivity(intent);
                            }else {
                                Toast.makeText(getApplicationContext(), "Error" +jsonObject.getString("error"), Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                        System.out.println(error.getMessage());
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String,String> hashMap = new HashMap<String, String>();
                        hashMap.put("lat1",p.getLat1());
                        hashMap.put("long1",p.getLong1());

                        hashMap.put("lat2",p.getLat2());
                        hashMap.put("long2",p.getLong2());

                        hashMap.put("lat3",p.getLat3());
                        hashMap.put("long3",p.getLong3());

                        hashMap.put("lat4",p.getLat4());
                        hashMap.put("long4",p.getLong4());

                        hashMap.put("Columns",String.valueOf(p.getColumn()));
                        hashMap.put("Rows",p.Row());

                        return hashMap;
                    }
                };

                requestQueue.add(request);



            }
        });

    }




    }


