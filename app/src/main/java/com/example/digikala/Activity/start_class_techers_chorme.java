package com.example.digikala.Activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.example.digikala.R;

public class start_class_techers_chorme extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_class_techers_chorme);

        Bundle bundle=getIntent().getExtras();
        String link =bundle.getString("link");

        WebView webView =findViewById(R.id.start_ch_webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(link);

    }
    @Override
    public void onBackPressed() {

            AlertDialog.Builder dilog=new AlertDialog.Builder(this);
            dilog.setTitle("آیا مطمعن هستید که میخواهید از این صحفه خارج شوید")
                    .setMessage("شما در روز تنها سه دفه میتوانید درخواست تایین سطح ارسال کنید")
                    .setCancelable(false)
                    .setPositiveButton("خیر", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    })
                    .setNegativeButton("خروج", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    }).show();
        }


    public String name(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(start_class_techers_chorme.this);
        String name=preferences.getString("name","ورود و ثبت نام ");
        return name; }
}
