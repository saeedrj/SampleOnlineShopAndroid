package com.example.digikala.Activity;
import static com.example.digikala.Activity.MainActivity.HTTP_CODE_SERVER;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.opengl.Visibility;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.digikala.AsyncTask.HttpStart_casll_teachers;
import com.example.digikala.R;

public class Start_class_teachers extends AppCompatActivity {

    CardView cardView , card_error;
    TextView textView ;
    Button button , clickshow_card;
    TextView  textView1 ;
    ImageButton imageButton;
    String Link=null;
   boolean error_b = true ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_class_teachers);

        card_error=findViewById(R.id.Start_class_teach_card_error);
        imageButton=findViewById(R.id.Start_class_details_img);
        cardView=findViewById(R.id.Start_class_teach_card_show);
        textView=findViewById(R.id.Start_class_teach_card_text);
        button=findViewById(R.id.Start_class_teach_card_bu);
        textView1= findViewById(R.id.Start_class_details_txt);
        clickshow_card =findViewById(R.id.Start_class_teach_start_find_bu);
 }

    public String name(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Start_class_teachers.this);
        String name=preferences.getString("name","ورود و ثبت نام ");
        return name; }

    public void start_class_techaers(View view) {
        switch (view.getId()){
            case R.id.Start_class_brand:

                Snackbar snackbar=Snackbar.make(view, "تهیه شده با ❤️ برای شما دوست عزیز ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null);
                ViewCompat.setLayoutDirection(snackbar.getView(),ViewCompat.LAYOUT_DIRECTION_RTL);
                snackbar.show();
                break;

            case R.id.Start_class_teach_back_bu:
                if (cardView.getVisibility()==View.VISIBLE)
                {
                   exit(error_b);
                }
                else
                finish();
                break;
            case R.id.Start_class_teach_start_find_bu:
                if (cardView.getVisibility()==View.GONE)
                sendHttp(true);


                break;

            case R.id.Start_class_teach_card_bu:
                if (Link != null){
                Intent intent = new Intent(Start_class_teachers.this,start_class_techers_chorme.class);
                intent.putExtra("link",Link);
                startActivity(intent);
                finish();}
                break;

            case  R.id.Start_class_details_img:
            case R.id.Start_class_details_txt:
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("zaban", textView1.getText());
                clipboard.setPrimaryClip(clip);

                Snackbar snackbar1=Snackbar.make(view, "لینک کپی شد , در مرور گر خود کپی کنید ", Snackbar.LENGTH_LONG)
                        .setAction(getString(R.string.app_name), null);
                ViewCompat.setLayoutDirection(snackbar1.getView(),ViewCompat.LAYOUT_DIRECTION_RTL);
                snackbar1.show();

                break;
        }
    }

    HttpStart_casll_teachers.HamazingP http_exit = new HttpStart_casll_teachers.HamazingP() {
        @Override
        public void onPostExecute(String s) {

        }
    };

    HttpStart_casll_teachers.HamazingP http_n =new HttpStart_casll_teachers.HamazingP() {
        @Override
        public void onPostExecute(final String s) {
            final String[] bb = s.split("WADAWD");
            textView.setText(bb[0]+" استاد انلاین  ,  لطفا صبر کنید !");

           int error = Integer.parseInt(bb[1]);
            if (error <= 3){    cardView.setVisibility(View.VISIBLE);
            if (bb.length == 3){
           if (bb[2]!=null){
               button.setVisibility(View.VISIBLE);
               textView1.setVisibility(View.VISIBLE);
               imageButton.setVisibility(View.VISIBLE);
               textView1.setText(bb[2]);
                Link =bb[2]; }}
           else {
                button.setVisibility(View.GONE);
               textView1.setVisibility(View.GONE);
               imageButton.setVisibility(View.GONE);
               Link = null;}

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    if (bb.length!=3)
                    sendHttp(true);
                    else
                    handler.removeMessages(0);
                }},30000); }
        else{
                card_error.setVisibility(View.VISIBLE);
                error_b = false;
        }

        }};



    private void sendHttp(boolean b ){
        if (error_b){
        if (b){
        HttpStart_casll_teachers httpStart_casll_teachers =new HttpStart_casll_teachers(Start_class_teachers.this,null);
        httpStart_casll_teachers.execute("http://"+HTTP_CODE_SERVER+"/digikala/start_find_teachers_online/exsit.php" , name());
        HttpStart_casll_teachers.http(http_n);}
        else {
            HttpStart_casll_teachers httpStart_casll_teachers =new HttpStart_casll_teachers(Start_class_teachers.this,null);
            httpStart_casll_teachers.execute("http://"+HTTP_CODE_SERVER+"/digikala/start_find_teachers_online/exsit.php" , name(),"1");
            HttpStart_casll_teachers.http(http_exit);
        }
        }
    }


    private void exit(boolean b){
        if (b) {
            AlertDialog.Builder dilog = new AlertDialog.Builder(this);
            dilog.setTitle("آیا مطمعن هستید که میخواهید از این صحفه خارج شوید")
                    .setMessage("شما در روز طول تنها سه دفه میتوانید درخواست تایین سطح ارسال کنید")
                    .setCancelable(false)
                    .setPositiveButton("خیر", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    })
                    .setNegativeButton("خروج", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            sendHttp(false);
                            finish();
                        }
                    }).show();
        }
    }

    @Override
    public void onBackPressed() {
        if (cardView.getVisibility()==View.VISIBLE)
        { exit(error_b); sendHttp(false); }
        else super.onBackPressed();
    }


}