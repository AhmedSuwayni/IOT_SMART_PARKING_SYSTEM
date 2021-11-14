package com.example.final_project;



import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageView;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserColumnFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@RequiresApi(api = Build.VERSION_CODES.O)
public class UserColumnFragment extends Fragment  {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PID = "PID";
    private static final String ARG_CID1 = "CID1";
    private static final String ARG_CID2 = "CID2";
    private static final String ARG_RID = "RID";;
    public List <ParkingSpots> spotsListc1 = new ArrayList<>();
    public List <ParkingSpots> spotsListc2 = new ArrayList<>();
    public List <ParkingSpotState> stateListc = new ArrayList<>();
    public List <Integer> WaitingTime = new ArrayList<>();
    public double Average;
    public double MinAverage;
    SwipeRefreshLayout swipeRefreshLayout ;
    private RecyclerView myRView ;
    private RecyclerView myRView2 ;
    static int [] images = {R.drawable.a , R.drawable.b , R.drawable.c ,R.drawable.d,R.drawable.e,R.drawable.f,R.drawable.g,R.drawable.h,R.drawable.i,R.drawable.j,R.drawable.k,R.drawable.l,R.drawable.first,R.drawable.last};


    Button Add,AddP;
    // TODO: Rename and change types of parameters
    private String CID1;
    private String CID2;
    private int PID;
    private int RID;



    public UserColumnFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static UserColumnFragment newInstance(String CID,String CID2 ,int PID,int RID) {
        UserColumnFragment fragment = new UserColumnFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CID1, CID);
        args.putString(ARG_CID2, CID2);
        args.putInt(ARG_PID, PID);
        args.putInt(ARG_RID, RID);
        fragment.setArguments(args);


        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            CID1 = getArguments().getString(ARG_CID1);
            CID2 = getArguments().getString(ARG_CID2);
            PID = getArguments().getInt(ARG_PID);
            RID = getArguments().getInt(ARG_RID);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.user_fragment,container,false);


        try {
            //the next line is to to create background and pass the colums IDs also the Parking Area ID to get the data as String for next steps
            String output   = new background().execute(String.valueOf(CID1),String.valueOf(CID2),String.valueOf(PID)).get();
            JSONArray JA = new JSONArray(output);

            //fill the Parking Spot list for spotsListc1
            for(int i =0 ;i < JA.length(); i++){
                JSONObject JO = (JSONObject) JA.get(i);
                if (JO.get("Column").equals(String.valueOf(CID1)) && JO.get("PID").equals(String.valueOf(PID))){

                    ParkingSpots ps = new ParkingSpots();

                    ps.setID(Integer.parseInt(String.valueOf(JO.get("SID"))));

                    ps.setColumn((Integer.parseInt(String.valueOf(JO.get("Column")))));

                    ps.setRow((Integer.parseInt(String.valueOf(JO.get("Row")))));
                    if (JO.get("isEmpty").equals("1")){
                        ps.setEmtpy(false);
                    }else{
                        ps.setEmtpy(true);
                    }


                   spotsListc1.add(ps);

                    //fill the Parking Spot list for spotsListc2
                }else if (JO.get("Column").equals(String.valueOf(CID2)) && JO.get("PID").equals(String.valueOf(PID))){

                    ParkingSpots ps = new ParkingSpots();

                    ps.setID(Integer.parseInt(String.valueOf(JO.get("SID"))));

                    ps.setColumn((Integer.parseInt(String.valueOf(JO.get("Column")))));

                    ps.setRow((Integer.parseInt(String.valueOf(JO.get("Row")))));
                    if (JO.get("isEmpty").equals("1")){
                        ps.setEmtpy(false);
                    }else{
                        ps.setEmtpy(true);
                    }



                    spotsListc2.add(ps);

                }

            }



            //sort the two array list by the Row so it will order from lower to higher
            Collections.sort(spotsListc1);
            Collections.sort(spotsListc2);






        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        try {
            String timeoutput = new backgroundToGetTime().execute(String.valueOf(PID)).get();

            JSONArray JA2 = new JSONArray(timeoutput);

            for(int i =0 ;i < JA2.length(); i++) {
                for (int j =0;j<spotsListc1.size() ||j< spotsListc2.size();j++){
                    JSONObject JO = (JSONObject) JA2.get(i);


                    if (JO.get("SID").equals(String.valueOf(spotsListc1.get(j).getID())) || JO.get("SID").equals(String.valueOf(spotsListc2.get(j).getID()))) { //change

                        Timestamp timestamp = Timestamp.valueOf(String.valueOf(JO.get("Date")));
                        Date d = new Date(timestamp.getTime());

                        if (CompareLastWeekDay(d)){
                            ParkingSpotState ps = new ParkingSpotState();

                            ps.setSID(Integer.parseInt(String.valueOf(JO.get("SID"))));

                            ps.setPID((Integer.parseInt(String.valueOf(JO.get("PID")))));

                            ps.setDate(Timestamp.valueOf(String.valueOf(JO.get("Date"))));

                            ps.setMin(Integer.parseInt(String.valueOf(JO.get("Date")).substring(14,16)));
                            if (JO.get("NewState").equals("1")) {
                                ps.setNewState(false);
                            } else {
                                ps.setNewState(true);
                            }

                            stateListc.add(ps);

                        }
                    }
                }
            }
            //to sort the arraylist by date
            Collections.sort(stateListc);
            int h;
            for (int i = 0;i<stateListc.size();i++) {
                if (stateListc.get(i).NewState == true){
                for (h = 0; h < stateListc.size(); h++) {           //these if for to get waiting time between state that has true - false
                    if (stateListc.get(h).NewState == false){
                        if (stateListc.get(i).getSID() == stateListc.get(h).getSID()) {

                            WaitingTime.add(getWaitingTime(stateListc.get(i).getDate(),stateListc.get(h).getDate()));
                        }

                    }
                }
                }



            }


            //Set the Average of wait Also the Avrege of Cars leaving the Spot out of 60 mins
            setAverage();
            setMinAverage();



        } catch (ExecutionException es) {
            es.printStackTrace();
        } catch (InterruptedException es) {
            es.printStackTrace();
        } catch (JSONException es) {
            es.printStackTrace();
        }
        myRView =view.findViewById(R.id.myRView);
        myRView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        MyAdapter adapter = new MyAdapter();
        myRView.setAdapter(adapter);

        //to swipe refresh i need it to dublicate the code
        swipeRefreshLayout =(SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {


                spotsListc1.clear();
                spotsListc2.clear();
                stateListc.clear();
                WaitingTime.clear();
                Average=0;
                MinAverage=0;

                try {
                    String output   = new background().execute(String.valueOf(CID1),String.valueOf(CID2),String.valueOf(PID)).get();
                    JSONArray JA = new JSONArray(output);

                    for(int i =0 ;i < JA.length(); i++){
                        JSONObject JO = (JSONObject) JA.get(i);
                        if (JO.get("Column").equals(String.valueOf(CID1)) && JO.get("PID").equals(String.valueOf(PID))){

                            ParkingSpots ps = new ParkingSpots();

                            ps.setID(Integer.parseInt(String.valueOf(JO.get("SID"))));

                            ps.setColumn((Integer.parseInt(String.valueOf(JO.get("Column")))));

                            ps.setRow((Integer.parseInt(String.valueOf(JO.get("Row")))));
                            if (JO.get("isEmpty").equals("1")){
                                ps.setEmtpy(false);
                            }else{
                                ps.setEmtpy(true);
                            }


                            spotsListc1.add(ps);

                        }else if (JO.get("Column").equals(String.valueOf(CID2)) && JO.get("PID").equals(String.valueOf(PID))){

                            ParkingSpots ps = new ParkingSpots();

                            ps.setID(Integer.parseInt(String.valueOf(JO.get("SID"))));

                            ps.setColumn((Integer.parseInt(String.valueOf(JO.get("Column")))));

                            ps.setRow((Integer.parseInt(String.valueOf(JO.get("Row")))));
                            if (JO.get("isEmpty").equals("1")){
                                ps.setEmtpy(false);
                            }else{
                                ps.setEmtpy(true);
                            }



                            spotsListc2.add(ps);

                        }

                    }



                    Collections.sort(spotsListc1);
                    Collections.sort(spotsListc2);






                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                try {
                    //background to get time state from the database as string
                    String timeoutput = new backgroundToGetTime().execute(String.valueOf(PID)).get();

                    //into JSONArray
                    JSONArray JA2 = new JSONArray(timeoutput);

                    for(int i =0 ;i < JA2.length(); i++) {
                        for (int j =0;j<spotsListc1.size() ||j< spotsListc2.size();j++){
                            JSONObject JO = (JSONObject) JA2.get(i);


                            if (JO.get("SID").equals(String.valueOf(spotsListc1.get(j).getID())) || JO.get("SID").equals(String.valueOf(spotsListc2.get(j).getID()))) {

                                Timestamp timestamp = Timestamp.valueOf(String.valueOf(JO.get("Date")));
                                Date d = new Date(timestamp.getTime());

                                //to get -7 days so we can compare it with today

                                if (CompareLastWeekDay(d)){
                                    ParkingSpotState ps = new ParkingSpotState();

                                    ps.setSID(Integer.parseInt(String.valueOf(JO.get("SID"))));

                                    ps.setPID((Integer.parseInt(String.valueOf(JO.get("PID")))));

                                    ps.setDate(Timestamp.valueOf(String.valueOf(JO.get("Date"))));


                                    //to get only the out of 60 mins
                                    ps.setMin(Integer.parseInt(String.valueOf(JO.get("Date")).substring(14,16)));

                                    if (JO.get("NewState").equals("1")) {
                                        ps.setNewState(false);
                                    } else {
                                        ps.setNewState(true);
                                    }

                                    stateListc.add(ps);

                                }
                            }
                        }
                    }
                    //to sort the arraylist by date
                    Collections.sort(stateListc);
                    int h;
                    for (int i = 0;i<stateListc.size();i++) {
                        if (stateListc.get(i).NewState == true){
                            for (h = 0; h < stateListc.size(); h++) {           //these if for to get waiting time between state that has true - false
                                if (stateListc.get(h).NewState == false){
                                    if (stateListc.get(i).getSID() == stateListc.get(h).getSID()) {

                                        WaitingTime.add(getWaitingTime(stateListc.get(i).getDate(),stateListc.get(h).getDate()));
                                    }

                                }
                            }
                        }



                    }

                    //set the average waiting time for the parking area
                    setAverage();

                    //set average out of 60 of mins that the parking spots begin empty
                    setMinAverage();

                    myRView2 =view.findViewById(R.id.myRView);
                    myRView2.setLayoutManager(new LinearLayoutManager(view.getContext()));
                    MyAdapter adapter2 = new MyAdapter();
                    myRView2.setAdapter(adapter2);
                    adapter2.notifyDataSetChanged();

                } catch (ExecutionException es) {
                    es.printStackTrace();
                } catch (InterruptedException es) {
                    es.printStackTrace();
                } catch (JSONException es) {
                    es.printStackTrace();
                }


                //for swipe refresh refresh
                UserColumnFragment  frg = new UserColumnFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.setReorderingAllowed(false);
                ft.replace(R.id.content,frg);
                ft.addToBackStack(null);

                swipeRefreshLayout.setRefreshing(false);
            }
        });


        // Inflate the layout for this fragment
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);



    }



    public boolean CompareLastWeekDay(Date d){
        int x = -7;
        Calendar calstate = Calendar.getInstance();
        Calendar cal = GregorianCalendar.getInstance();
        cal.add( Calendar.DAY_OF_YEAR, x);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        calstate.setTime(d);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        if (cal.compareTo(calstate) == 0){
            return true;
        }else {
            return true;
        }
    }

    public int getWaitingTime(Timestamp T1,Timestamp T2){
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(T1.getTime());
        long milliseconds = T2.getTime() - T1.getTime();
        int seconds = (int) milliseconds / 1000;
        int minutes = seconds  / 60;
        return minutes;

    }




    public int getMinInD(){
        java.util.Date d=new java.util.Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        int seconds = (int) d.getTime() / 1000;
        int minutes = (seconds % 3600) / 60;
        return minutes;
    }

    public void setAverage(){
        //compute sum
        int i=0;
        int sum = 0;
        while(i < WaitingTime.size()) {
            sum += WaitingTime.get(i);
            i++;
        }
        if (sum == 0){
            Average = 60;
        }else {
        //compute average
        Average = (sum / WaitingTime.size());
        }
    }

    public void setMinAverage(){
        //compute sum
        int i=0;
        int sum = 0;
        while(i < stateListc.size()) {
            sum += stateListc.get(i).getMin();
            i++;
        }

        if (sum == 0){
            MinAverage = 60;

        }else {

            //compute average
            MinAverage = (sum / stateListc.size());
        }
    }

    public int getBusyTime(ParkingSpots ps){

        int seconds = 0;
        java.util.Date d=new java.util.Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        //to take the newst state of Parking spot using rev loop
        for (int i = stateListc.size() - 1; i >= 0; i--) {

           if (stateListc.get(i).getSID() == ps.getID()){

               Timestamp t1 = stateListc.get(i).getDate();


               Calendar call = Calendar.getInstance();
               call.setTime(t1);
               call.add(Calendar.DAY_OF_MONTH, 7);
               t1.setTime(call.getTime().getTime()); // or
               t1 = new Timestamp(call.getTime().getTime());


               long milliseconds = d.getTime() - t1.getTime();
                seconds = (int) milliseconds / 1000;
               int minutes = seconds  / 60;
               return minutes;
           }
        }

        return seconds;
    }
    public class MyAdapter extends RecyclerView.Adapter<UserColumnFragment.ViewHolder> {


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.parking_slot_card,parent,false);
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            boolean LSide = false,RSide = false;


            if (position == 0){

                holder.imageView.setImageResource(images[12]);


            }else if (position == spotsListc1.size()+1) {
                holder.imageView.setImageResource(images[13]);

            }else {

                if (spotsListc1.get((position-1)).isEmtpy() == false && spotsListc2.get((position-1)).isEmtpy() == false) {
                    //the two parking spots are busy

                    //if it's Tru so it will be yellow


                    int Ps1BusyTime = getBusyTime(spotsListc1.get((position-1)));
                    int Ps2BusyTime = getBusyTime(spotsListc2.get((position-1)));
                    if (Ps1BusyTime <= Average) {
                        //10 min or less than average will be fine
                        if (Math.abs(getMinInD() - MinAverage) <= 10) {
                            //yellow
                            LSide = true;
                        }
                    }
                    //by defualt is Red
                    if (Ps2BusyTime <= Average) {
                        //10 min or less than average will be fine
                        if (Math.abs(getMinInD() - MinAverage) <= 10) {
                            //yellow
                            RSide = true;
                        }
                    }
                    //by defualt is Red

                    //if the both Red
                    if (LSide == false && RSide == false) {
                        holder.imageView.setImageResource(images[2]);
                    }
                    //if both yellow
                    else if (LSide == true && RSide == true) {
                        holder.imageView.setImageResource(images[3]);
                    }
                    //if left side yellow but right Red
                    else if (LSide == true && RSide == false) {
                        holder.imageView.setImageResource(images[10]);
                    }
                    //if Right side yellow but Left Red
                    else if (LSide == false && RSide == true) {
                        holder.imageView.setImageResource(images[11]);
                    }


                } else if (spotsListc1.get((position-1)).isEmtpy() == true && spotsListc2.get((position-1)).isEmtpy() == false) {
                    //the left one emtpy and the second is bussy

                    int Ps2BusyTime = getBusyTime(spotsListc2.get((position-1)));

                    if (Ps2BusyTime <= Average) {
                        //10 min or less than average will be fine
                        if (Math.abs(getMinInD() - MinAverage) <= 10) {
                            //yellow
                            RSide = true;
                        }
                    }
                    //by defualt is Red
                    //if the Right Red
                    if (RSide == false) {
                        holder.imageView.setImageResource(images[6]);
                    }
                    //if Right yellow
                    else if (RSide == true) {
                        holder.imageView.setImageResource(images[7]);
                    }


                } else if (spotsListc1.get((position-1)).isEmtpy() == false && spotsListc2.get((position-1)).isEmtpy() == true) {
                    //the left one bussy and the second is emtpy

                    int Ps1BusyTime = getBusyTime(spotsListc1.get((position-1)));


                    if (Ps1BusyTime <= Average) {
                        //10 min or less than average will be fine
                        if (Math.abs(getMinInD() - MinAverage) <= 10) {
                            //yellow
                            LSide = true;
                        }
                    }
                    //by defualt is Red
                    //if the Left Red
                    if (LSide == false) {
                        holder.imageView.setImageResource(images[4]);
                    }
                    //if Left yellow
                    else if (LSide == true) {
                        holder.imageView.setImageResource(images[5]);
                    }


                } else if (spotsListc1.get((position-1)).isEmtpy() == true && spotsListc2.get((position-1)).isEmtpy() == true) {
                    //the two parking spots are emtpy

                    holder.imageView.setImageResource(images[0]);

                } else {


                }





            }

        }

        @Override
        public int getItemCount() {
            return spotsListc1.size()+2;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}