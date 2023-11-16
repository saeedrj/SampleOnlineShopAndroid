package com.example.digikala.AsyncTask;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class HttpReques_class_details extends AsyncTask<String, Void, String>  {

private Context _context;


private static HRequest _HRequest;

public interface HRequest{ void onPostExecute(String s);}


    public HttpReques_class_details(Context context, HRequest HRequest)
    {
        _context=context;
        _HRequest=HRequest;
    }

    @Override
    protected void onPreExecute() {}

    @Override
    protected String doInBackground(String... params) {

        String htmlStream="";
        try {
//////////////////////
            String data = null;
            if (params.length>1){
            data= URLEncoder.encode("email","UTF8")+"="+URLEncoder.encode(params[1],"UTF8");
            data+="&"+URLEncoder.encode("pass","UTF8")+"="+URLEncoder.encode(params[2],"UTF8");}
//            if (params.length>3){
//                data+="&"+URLEncoder.encode("cell","UTF8")+"="+URLEncoder.encode(params[3],"UTF8");
//                data+="&"+URLEncoder.encode("name","UTF8")+"="+URLEncoder.encode(params[4],"UTF8");
//             }
//////////////////////
            URL url = new URL(params[0]);
            URLConnection connection=url.openConnection();
//////////////////////////
            if (params.length>1){
            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(data);
            writer.flush();}
//////////////////////////
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder builder=new StringBuilder();

            String line =null;

            while ((line=reader.readLine())!=null){
                builder.append(line);
            }

            htmlStream=builder.toString();
        }
        catch (IOException e)
        {
            Log.e(e.toString(),"");
            //Toast.makeText(_context, e.toString(), Toast.LENGTH_SHORT).show();
            htmlStream= null; }
       return htmlStream;
    }

    public static void http(HRequest hrequest){
        _HRequest=hrequest;
    }

    @Override
    protected void onPostExecute(String s) {

        if (_HRequest!=null)
            _HRequest.onPostExecute(s);
    }
}

