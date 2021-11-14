package com.example.final_project;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AdminUI extends AppCompatActivity {

    TextView popaID ,popc, popr,popPsID;

    private Button AddPA,AddPS,DelPS,DelPA,Addsa,btndel;

    private RequestQueue requestQueue;
    private static final String URL1 = "http://carparking1.online/AddParkingSpot.php";
    private static final String URL2 = "http://carparking1.online/DeleteParkingSpot.php";
    private static final String URL3 = "http://carparking1.online/DeleteParkingArea.php";
    private StringRequest request;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_ui);

        AddPA = findViewById(R.id.AddPA);
        AddPS = findViewById(R.id.AddPS);
        DelPS = findViewById(R.id.DelPS);
        DelPA = findViewById(R.id.DelPA);

        AddPA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //to start the activity of Adding Parking Area
                startActivity(new Intent(getApplicationContext(),AddParkingArea.class));
            }
        });

        AddPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //after Add parking spot button cliced using poput layout will be way to fill the data need it
                TextView txtclose;
                final Dialog myDialog = new Dialog(AdminUI.this,android.R.style.Theme_Black_NoTitleBar);
                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(100, 0, 0, 0)));
                myDialog.setContentView(R.layout.popup_add_spot_ar);
                popaID = myDialog.findViewById(R.id.Areaid);
                popc = myDialog.findViewById(R.id.cnumpop);
                popr = myDialog.findViewById(R.id.rowmpop);
                popPsID = myDialog.findViewById(R.id.psidpop);
                txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
                txtclose.setText("X");
                Addsa = (Button) myDialog.findViewById(R.id.btnAdds);
                txtclose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });
                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                myDialog.show();


                //final button to add the parking spot and add it to the database but we need to make sure of null valeus
                Addsa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (popc == null){
                            Toast.makeText(getApplicationContext(),"Please Enter The Column Number !",Toast.LENGTH_SHORT).show();

                        }else if (popr == null){
                            Toast.makeText(getApplicationContext(),"Please Enter The Row Number !",Toast.LENGTH_SHORT).show();

                        }else if (popPsID == null){
                            Toast.makeText(getApplicationContext(),"Please Enter The Parking Spot ID !",Toast.LENGTH_SHORT).show();

                        }else if (popaID == null){
                            Toast.makeText(getApplicationContext(),"Please Enter The Parking Area ID !",Toast.LENGTH_SHORT).show();

                        }

                        else{


                            requestQueue = Volley.newRequestQueue(getApplicationContext().getApplicationContext());
                            request = new StringRequest(Request.Method.POST, URL1, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {

                                        JSONObject jsonObject = new JSONObject(response);
                                        if(jsonObject.names().get(0).equals("success")){

                                            Toast.makeText(getApplicationContext(),jsonObject.getString("success"),Toast.LENGTH_SHORT).show();


                                        }else {
                                            Toast.makeText(getApplicationContext(),jsonObject.getString("error"), Toast.LENGTH_SHORT).show();
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
                                    hashMap.put("PID", popaID.getText().toString());
                                    hashMap.put("SID", popPsID.getText().toString());
                                    hashMap.put("Row", popr.getText().toString());
                                    hashMap.put("Column", popc.getText().toString());


                                    return hashMap;
                                }
                            };

                            requestQueue.add(request);

                        }

                    }
                });

            }
        });

        //after Delete parking spot button cliced using poput layout will be way to fill the data need it
        DelPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView txtclose;
                final Dialog myDialog = new Dialog(AdminUI.this,android.R.style.Theme_Black_NoTitleBar);
                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(100, 0, 0, 0)));
                myDialog.setContentView(R.layout.popup_delete_spot);
                popPsID = myDialog.findViewById(R.id.psidpop);
                txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
                txtclose.setText("X");
                btndel = (Button) myDialog.findViewById(R.id.btndel);
                txtclose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });
                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                myDialog.show();

                btndel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (popc == null){
                            Toast.makeText(getApplicationContext(),"Please Enter The Column Number !",Toast.LENGTH_SHORT).show();

                        }else if (popr == null){
                            Toast.makeText(getApplicationContext(),"Please Enter The Row Number !",Toast.LENGTH_SHORT).show();

                        }else if (popPsID == null){
                            Toast.makeText(getApplicationContext(),"Please Enter The Parking Spot ID !",Toast.LENGTH_SHORT).show();

                        }else if (popaID == null){
                            Toast.makeText(getApplicationContext(),"Please Enter The Parking Area ID !",Toast.LENGTH_SHORT).show();

                        }

                        else{


                            requestQueue = Volley.newRequestQueue(getApplicationContext().getApplicationContext());
                            request = new StringRequest(Request.Method.POST, URL2, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {

                                        JSONObject jsonObject = new JSONObject(response);
                                        if(jsonObject.names().get(0).equals("success")){

                                            Toast.makeText(getApplicationContext(),jsonObject.getString("success"),Toast.LENGTH_SHORT).show();

                                        }else {
                                            Toast.makeText(getApplicationContext(), jsonObject.getString("error"), Toast.LENGTH_SHORT).show();
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
                                    hashMap.put("SID", popPsID.getText().toString());



                                    return hashMap;
                                }
                            };

                            requestQueue.add(request);

                        }

                    }
                });

            }
        });
//after Delete parking Area button cliced using poput layout will be way to fill the data need it
        DelPA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView txtclose;
                final Dialog myDialog = new Dialog(AdminUI.this,android.R.style.Theme_Black_NoTitleBar);
                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(100, 0, 0, 0)));
                myDialog.setContentView(R.layout.popup_delete_area);
                popaID = myDialog.findViewById(R.id.paidpop);
                txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
                txtclose.setText("X");
                btndel = (Button) myDialog.findViewById(R.id.btndela);
                txtclose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });
                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                myDialog.show();

                btndel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (popaID == null) {
                            Toast.makeText(getApplicationContext(), "Please Enter The Parking Area ID", Toast.LENGTH_SHORT).show();
                        }else{


                            requestQueue = Volley.newRequestQueue(getApplicationContext().getApplicationContext());
                            request = new StringRequest(Request.Method.POST, URL3, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {

                                        JSONObject jsonObject = new JSONObject(response);
                                        if(jsonObject.names().get(0).equals("success")){

                                            Toast.makeText(getApplicationContext(),jsonObject.getString("success"),Toast.LENGTH_SHORT).show();

                                        }else {
                                            Toast.makeText(getApplicationContext(),jsonObject.getString("error"), Toast.LENGTH_SHORT).show();
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
                                    hashMap.put("ID", popaID.getText().toString());



                                    return hashMap;
                                }
                            };

                            requestQueue.add(request);

                        }

                    }
                });


            }
        });



    }

}
