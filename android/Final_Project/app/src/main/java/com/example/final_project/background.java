package com.example.final_project;


import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



public class background extends AsyncTask <String,Void,String> {
    String data ="";



    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected String doInBackground(String... params) {
        try {
            String column1 = params[0];
            String column2 = params[1];
            String PID =  params[2];
            URL url = new URL("http://carparking1.online/GetParkingSpots.php");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while(line != null){
                line = bufferedReader.readLine();
                data = data + line;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String final_result = (data);

        return final_result;


    }



    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);



    }
}