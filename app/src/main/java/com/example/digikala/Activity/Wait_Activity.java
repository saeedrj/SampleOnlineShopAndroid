package com.example.digikala.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.example.digikala.AsyncTask.HttpWait_Activity;
import com.example.digikala.R;

import static com.example.digikala.Activity.MainActivity.HTTP_CODE_SERVER;

public class Wait_Activity extends AppCompatActivity {



    String st;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_activity);


        Bundle bundle=getIntent().getExtras();
        int id =bundle.getInt("id");

        st= String.valueOf(id);

        HttpWait_Activity httpWait_activity = new HttpWait_Activity(Wait_Activity.this, null);
        httpWait_activity.execute("http://"+HTTP_CODE_SERVER+"/digikala/wait_pro/indext.php",st);
        HttpWait_Activity.http(finisher);

    }





    HttpWait_Activity.HamazingP finisher=new HttpWait_Activity.HamazingP() {
        @Override
        public void onPostExecute(String s) {


            if (s==null){
                Intent intent=new Intent(Wait_Activity.this,Dis_Internet.class);
                startActivity(intent);
                finish();
            }
            else {
                Intent intent =new Intent(Wait_Activity.this, Show_MH.class);
                intent.putExtra("id",st);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();


            }}};

}
