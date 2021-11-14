package com.example.final_project;


import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ColumnFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ColumnFragment extends Fragment  implements View.OnClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PID = "PID";
    private static final String ARG_CID1 = "CID1";
    private static final String ARG_CID2 = "CID2";
    private static final String ARG_RID = "RID";
    private static final String DESCRIBABLE_KEY = "describable_key";
    private RequestQueue requestQueue;
    private static final String URL = "http://carparking1.online/AddParkingSpot.php";
    private StringRequest request;
    private ParkingArea P;

    List<ParkingSpots> PsList = new ArrayList<>();
    private  ListView mListView ;
    int [] images = {R.drawable.a , R.drawable.b , R.drawable.c ,R.drawable.d,R.drawable.e,R.drawable.f,R.drawable.g,R.drawable.h,R.drawable.i,R.drawable.j};

    EditText popc,popr,popPsID;
    Button Add,AddP;
    // TODO: Rename and change types of parameters
    private int CID1 , CID2, PID,RID;



    public ColumnFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ColumnFragment newInstance(int CID,int CID2 ,int PID,int RID) {
        ColumnFragment fragment = new ColumnFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_CID1, CID);
        args.putInt(ARG_CID2, CID);
        args.putInt(ARG_PID, PID);
        args.putInt(ARG_RID, RID);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            CID1 = getArguments().getInt(ARG_CID1);
            CID2 = getArguments().getInt(ARG_CID2);
            PID = getArguments().getInt(ARG_PID);
            RID = getArguments().getInt(ARG_RID);



        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.fragment_column,container,false);



        mListView =view.findViewById(R.id.myListView);

        MyAdapter adapter = new MyAdapter();
        mListView.setAdapter(adapter);
        AddP = (Button)view.findViewById(R.id.Addbt);
        AddP.setOnClickListener(this);

        // Inflate the layout for this fragment
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);



    }

    @Override
    public void onClick(View view) {
        TextView txtclose;
     final Dialog myDialog = new Dialog(getContext(),android.R.style.Theme_Black_NoTitleBar);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(100, 0, 0, 0)));
        myDialog.setContentView(R.layout.popup_add_slots);
        popc = myDialog.findViewById(R.id.cnumpop);
        popr = myDialog.findViewById(R.id.rowmpop);
        popPsID = myDialog.findViewById(R.id.psidpop);
        txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
        txtclose.setText("M");
        Add = (Button) myDialog.findViewById(R.id.btnAdd);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popc == null){
                    Toast.makeText(getActivity(),"Please Enter The Column Number !",Toast.LENGTH_SHORT).show();

                }else if (popr == null){
                    Toast.makeText(getActivity(),"Please Enter The Row Number !",Toast.LENGTH_SHORT).show();

                }else if (popPsID == null){
                    Toast.makeText(getActivity(),"Please Enter The Parking Spot ID !",Toast.LENGTH_SHORT).show();

                }else{


                    requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
                    request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {

                                JSONObject jsonObject = new JSONObject(response);
                                if(jsonObject.names().get(0).equals("success")){

                                    Toast.makeText(getActivity(),"The Parking Spot has been added :  "+jsonObject.getString("success"),Toast.LENGTH_SHORT).show();


                                }else {
                                    Toast.makeText(getActivity(), "Error" +jsonObject.getString("error"), Toast.LENGTH_SHORT).show();
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
                            hashMap.put("PID", String.valueOf(PID));
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


    class MyAdapter extends BaseAdapter {

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = getLayoutInflater().inflate(R.layout.parking_slot_card ,parent , false);



        //fill the parking spot on one colum
        for (int h = 0; h< RID ; h =h+2){
            PsList.add(h,new ParkingSpots(CID1,PID,h));
            PsList.add(h+1,new ParkingSpots(CID2,PID,h));

        }
        for (int i = 0; i< PsList.size(); i=i+2){
                for (int j = 1; j<= RID; j++){
                                    if (PsList.get(i).getID() != 0 && PsList.get(i+1).getID() != 0){
                                        //the two parking spots are busy
                                        ImageView imageView = convertView.findViewById(R.id.image);
                                        imageView.setImageResource(images[1]);


                                    }else if (PsList.get(i).getID() != 0 && PsList.get(i+1).getID() == 0){
                                        //the left one bussy and the second is emtpy
                                        ImageView imageView = convertView.findViewById(R.id.image);
                                        imageView.setImageResource(images[8]);


                                    }else if (PsList.get(i).getID() == 0 && PsList.get(i+1).getID() != 0){
                                        //the left one emtpy and the second is bussy
                                        ImageView imageView = convertView.findViewById(R.id.image);
                                        imageView.setImageResource(images[9]);


                                    }else if (PsList.get(i).getID() == 0 && PsList.get(i+1).getID() == 0){
                                        //the two parking spots are emtpy
                                        ImageView imageView = convertView.findViewById(R.id.image);
                                        imageView.setImageResource(images[0]);

                                    }


                                    else{


                        }



                }

            }


        return  convertView;


    }

        @Override
        public int getViewTypeCount() {
            return getCount();
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }
}
}