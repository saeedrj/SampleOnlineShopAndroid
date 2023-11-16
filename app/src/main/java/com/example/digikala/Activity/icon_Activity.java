package com.example.digikala.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import static com.example.digikala.Activity.MainActivity.HTTP_CODE_SERVER;
import com.example.digikala.AsyncTask.HttpRequest;
import com.example.digikala.R;
import java.util.Timer;
import java.util.TimerTask;

public class icon_Activity extends AppCompatActivity {

    int time=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icon_);


//
//        wifiManager=(WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
//
//        if (wifiManager.disconnect()){
//            Intent intent=new Intent(icon_Activity.this,Dis_Internet.class);
//            startActivity(intent);
//            finish();}


        HttpRequest httpRequest = new HttpRequest(icon_Activity.this, null);
        httpRequest.execute("http://"+HTTP_CODE_SERVER+"/digikala/timer/exsit.php");
        HttpRequest.http(hrequest);



        final Timer timer=new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (time>=1){

                if (time>=1){
                    timer.cancel();
                    Intent intent=new Intent(icon_Activity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Intent intent=new Intent(icon_Activity.this,Dis_Internet.class);
                    startActivity(intent);}


            }}
        },1,1000);



    }


    HttpRequest.HRequest hrequest=new HttpRequest.HRequest() {
        @Override
        public void onPostExecute(final String s) {
            if (s!=null)
        time=s.length();
            else {
                Intent intent=new Intent(icon_Activity.this,Dis_Internet.class);
                startActivity(intent);
                finish(); }}};}
