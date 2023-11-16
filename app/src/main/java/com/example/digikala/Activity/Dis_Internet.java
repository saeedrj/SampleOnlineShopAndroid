package com.example.digikala.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import static com.example.digikala.Activity.MainActivity.HTTP_CODE_SERVER;
import com.example.digikala.AsyncTask.HttpRequest;
import com.example.digikala.R;

public class Dis_Internet extends AppCompatActivity {

    Button button;
    Button azma;
    TextView txt_Dis_Internet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dis__internet);

        txt_Dis_Internet = findViewById(R.id.Txt_Dis_Internet);
        txt_Dis_Internet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Dis_Internet.this, "تکمیل نشده", Toast.LENGTH_SHORT).show(); }});


        azma=findViewById(R.id.Btn_Dis_Internet_refresh);
        azma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Dis_Internet.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        button = findViewById(R.id.Btn_Dis_Internet);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpRequest httpRequest = new HttpRequest(Dis_Internet.this, null);
                httpRequest.execute("http://"+HTTP_CODE_SERVER+"/digikala/timer/exsit.php");
                HttpRequest.http(hrequest);


            }
        });
    }


        HttpRequest.HRequest hrequest=new HttpRequest.HRequest() {
            @Override
            public void onPostExecute(final String s) {
                if (s==null) return;
                Intent intent=new Intent(Dis_Internet.this,icon_Activity.class);
                startActivity(intent);
                finish();
            }};}