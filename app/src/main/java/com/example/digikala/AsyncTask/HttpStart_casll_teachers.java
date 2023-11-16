package com.example.digikala.AsyncTask;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.digikala.Activity.Start_class_teachers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class HttpStart_casll_teachers extends AsyncTask<String, Void, String>{

    private static HamazingP _HamazingP;
    private Context _Context;


    public interface HamazingP {
            void onPostExecute(String s);
                }


    public HttpStart_casll_teachers(Context context, HamazingP hamazingP)
        { _HamazingP=hamazingP; _Context=context; }

        @Override
        protected void onPreExecute () { }


    @Override
        protected String doInBackground (String...params){

        String htmlStream = "";
        try {

//////////////////////

            String data = null;
            if (params.length > 1) {
                data = URLEncoder.encode("name", "UTF8") + "=" + URLEncoder.encode(params[1], "UTF8");
            }
            if (params.length>2){
                data+="&"+URLEncoder.encode("check","UTF8")+"="+URLEncoder.encode(params[2],"UTF8");}


//////////////////////


            URL url = new URL(params[0]);
            URLConnection connection = url.openConnection();

//////////////////////////
            if (params.length > 1) {
                connection.setDoOutput(true);
                OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
                writer.write(data);
                writer.flush();
            }
//////////////////////////
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder builder = new StringBuilder();

            String line = null;

            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

            htmlStream = builder.toString();
        } catch (IOException e) {
            htmlStream = null; }

        return htmlStream;
    }


        public static void http (HamazingP hrequest){
        _HamazingP = hrequest;
    }

        @Override
        protected void onPostExecute (String s){

        if (_HamazingP != null)
            _HamazingP.onPostExecute(s);
    }
    }