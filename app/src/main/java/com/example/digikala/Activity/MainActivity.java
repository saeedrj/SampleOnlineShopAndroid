package com.example.digikala.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.digikala.AsyncTask.HttpAmazingProduct;
import com.example.digikala.AsyncTask.HttpReques_class_details;
import com.example.digikala.AsyncTask.HttpRequest;
import com.example.digikala.AsyncTask.HttpSellProduct;
import com.example.digikala.AsyncTask.HttpSlider;
import com.example.digikala.AsyncTask.HttpTabliq;
import com.example.digikala.AsyncTask.HttpTimerProduct;
import com.example.digikala.AsyncTask.HttpViewProduct;
import com.example.digikala.Product.Amazing;
import com.example.digikala.Product.Sell;
import com.example.digikala.Product.Timer;
import com.example.digikala.Product.View_pr;
import com.example.digikala.R;
import com.example.digikala.Simple_Adaptor.buyMenuAdapter;
import com.example.digikala.Simple_Adaptor.buyMenuItemList;
import com.squareup.picasso.Picasso;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {

   public static String HTTP_CODE_SERVER ="10.0.2.2";




    LinearLayout management_Lin_sign;
    TextView textView,sign;
    private SliderLayout sliderShow;
    DrawerLayout derwerMenuList;
    public static SharedPreferences preferences;
    LinearLayout.LayoutParams layoutParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drowlayout_main);


        derwerMenuList =findViewById(R.id.drawer);
        sign=findViewById(R.id.btn_sign);
        textView=findViewById(R.id.txtTitleMenuList);
        management_Lin_sign=findViewById(R.id.account_Management_Lin_sign);

        preferences= PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String name=preferences.getString("name","ورود و ثبت نام ");
        sign.setText(name);

        list_Item_NavigationView();
///////////////
        HttpReques_class_details httpReques_class_details = new HttpReques_class_details(MainActivity.this,null);
        httpReques_class_details.execute("http://"+HTTP_CODE_SERVER+"/digikala/");
       // HttpReques_class_details.http(details_class);



        HttpAmazingProduct httpRequest_Product_Amazing = new HttpAmazingProduct(MainActivity.this, null);
        httpRequest_Product_Amazing.execute("http://"+HTTP_CODE_SERVER+"/digikala/shegeft_angiz/readadaptor.php");
        HttpAmazingProduct.http(Product_Amazing);

        HttpRequest httpRequest_Timer_ScrollView = new HttpRequest(MainActivity.this, null);
        httpRequest_Timer_ScrollView.execute("http://"+HTTP_CODE_SERVER+"/digikala/timer/exsit.php");
        HttpRequest.http(Timer_ScrollView);

        HttpTimerProduct httpTimerProduct =new HttpTimerProduct(MainActivity.this,null);
        httpTimerProduct.execute("http://"+HTTP_CODE_SERVER+"/digikala/time_product/readadaptor_time.php");
        HttpTimerProduct.http(Product_Timer);


        HttpViewProduct httpViewProduct =new HttpViewProduct(MainActivity.this,null);
        httpViewProduct.execute("http://"+HTTP_CODE_SERVER+"/digikala/view_product/readadaptor_view.php");
        HttpViewProduct.http(product_View);


        HttpSellProduct httpSellProduct=new HttpSellProduct(MainActivity.this,null);
        httpSellProduct.execute("http://"+HTTP_CODE_SERVER+"/digikala/sell_product/readadaptor_sell.php");
        HttpSellProduct.http(Product_sell);

        HttpTabliq httpTabliq =new HttpTabliq(MainActivity.this,null);
        httpTabliq.execute("http://"+HTTP_CODE_SERVER+"/digikala/img/tabliq/readadaptor_tabliq.php");
        HttpTabliq.http(tabliq);


        HttpSlider httpSlider =new HttpSlider(MainActivity.this,null);
        httpSlider.execute("http://"+HTTP_CODE_SERVER+"/digikala/img/slider/readadaptor_slider.php");
        HttpSlider.http(Slider);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        preferences= PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String name=preferences.getString("name","ورود و ثبت نام ");
        sign.setText(name); }

    HttpSlider.HamazingP Slider =new HttpSlider.HamazingP() {
        @Override
        public void onPostExecute(String s) {
        slider(s);
        }
        private void slider(String s) {


            ArrayList<String> str_url_picasso=new ArrayList();

            try {
                JSONArray jsonArray =new JSONArray(s);
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject object=jsonArray.getJSONObject(i);
                    int id=object.getInt("id");
                    String img= object.getString("text");
                    String stringpicurl="http://"+HTTP_CODE_SERVER+"/digikala/img/slider/"+img;
                    str_url_picasso.add(stringpicurl);
                }

                sliderShow = findViewById(R.id.slider);

            for (int i=0;i<=str_url_picasso.size()-1;i++) {
                TextSliderView textSliderView = new TextSliderView(MainActivity.this);
                textSliderView
                        .image(str_url_picasso.get(i));

                sliderShow.addSlider(textSliderView);

            } } catch (JSONException e) { e.printStackTrace(); } }};

    HttpTabliq.HamazingP tabliq= new HttpTabliq.HamazingP() {
        @Override public void onPostExecute(String s) {
            tabliq(s);
        }
    private void tabliq(String s) {
        ImageView[] t=new ImageView[6];
        t[0]=findViewById(R.id.tabliq1);
        t[1]=findViewById(R.id.tabliq2);
        t[2]=findViewById(R.id.tabliq3);
        t[3]=findViewById(R.id.tabliq4);
        t[4]=findViewById(R.id.tabliq5);
        t[5]=findViewById(R.id.tabliq6);

       ArrayList<String> str_url_picasso=new ArrayList();

        try {
            JSONArray jsonArray =new JSONArray(s);
            for(int i=0;i<jsonArray.length();i++){
                JSONObject object=jsonArray.getJSONObject(i);
                int id=object.getInt("id");
                String img= object.getString("text");
                String stringpicurl="http://"+HTTP_CODE_SERVER+"/digikala/img//tabliq/"+img;
                str_url_picasso.add(stringpicurl);
            }

            Picasso.with(MainActivity.this).load(str_url_picasso.get(0)).into(t[0]);
            Picasso.with(MainActivity.this).load(str_url_picasso.get(1)).into(t[1]);
            Picasso.with(MainActivity.this).load(str_url_picasso.get(2)).into(t[2]);
            Picasso.with(MainActivity.this).load(str_url_picasso.get(3)).into(t[3]);
            Picasso.with(MainActivity.this).load(str_url_picasso.get(4)).into(t[4]);
            Picasso.with(MainActivity.this).load(str_url_picasso.get(5)).into(t[5]);


        } catch (JSONException e) { e.printStackTrace(); }


    }};

    HttpRequest.HRequest Timer_ScrollView= new HttpRequest.HRequest(){
        @Override public void onPostExecute(final String s) {
            Timer_ScrollView_shelve(s);
        }
    private void Timer_ScrollView_shelve(String s) {
        final TextView timer_sec ,timer_min ,timer_hour;

        final int anIntTimer[];
        String[] ma = s.split(":");

        timer_hour=findViewById(R.id.Timer_hour);
        timer_sec=findViewById(R.id.Timer_sec);
        timer_min=findViewById(R.id.Timer_min);

        int  hour= Integer.parseInt(ma[0].trim());
        final int min= Integer.parseInt(ma[1].trim());
        int  sec= Integer.parseInt(ma[2].trim());
        final Handler handler=new Handler();
        anIntTimer=new int[3];
        anIntTimer[0]=hour;
        anIntTimer[1]=min;
        anIntTimer[2]=sec;

        timer_hour.setText(String.valueOf(anIntTimer[0]));
        timer_min.setText(String.valueOf(anIntTimer[1]));
        timer_sec.setText(String.valueOf(anIntTimer[2]));
        final Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true){
                        Thread.sleep(1000);

                        if (anIntTimer[2] == 0) {        //sec
                            if (anIntTimer[1] != 0) {    //min
                                anIntTimer[1]--;         //min
                                anIntTimer[2] = 59;      //sec
                            } else {
                                anIntTimer[0]--;        //hour
                                anIntTimer[1] = 59;     //min
                                anIntTimer[2] = 59;     //sec
                            }
                        } else anIntTimer[2]--;        //sec

                        handler.post(new Runnable() {
                            @Override
                            public void run() {

                                if (anIntTimer[0]<10){     timer_hour.setText("0"+anIntTimer[0]);}                   //hour
                                else{                       timer_hour.setText(String.valueOf(anIntTimer[0]));}      //hour
                                if (anIntTimer[1]<10){     timer_min.setText("0"+anIntTimer[1]);}                    //min
                                else {                      timer_min.setText(String.valueOf(anIntTimer[1]));}       //min
                                if (anIntTimer[2]<10){     timer_sec.setText("0"+anIntTimer[2]);}                    //sec
                                else{                       timer_sec.setText(String.valueOf(anIntTimer[2]));}        //sec
                            }}); } }
                catch (InterruptedException e) { e.printStackTrace(); }
            }});
        thread.start();
    }};

    HttpAmazingProduct.HamazingP Product_Amazing=new HttpAmazingProduct.HamazingP() {
        @Override
        public void onPostExecute(final String s) { try {
                JSONArray jsonArray =new JSONArray(s);
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject object=jsonArray.getJSONObject(i);
                    int id=object.getInt("id");
                    String title =object.getString("text_mahsole");
                    String non_pic =String.valueOf(object.getInt("non_price_samsung_phone"));
                    String off_pic =String.valueOf(object.getInt("off_price_samsung_phone"));
                    String img= object.getString("pic");
                    String stringpicurl="http://"+HTTP_CODE_SERVER+"/digikala/img/"+img;

                    Product_Amazing(id,title,off_pic,non_pic,stringpicurl);
                }
            } catch(JSONException e){e.printStackTrace();}}


    public void Product_Amazing(int id,String title,String pprice,String price,String picurl){

        Amazing amazing=new Amazing(MainActivity.this);
        amazing.id =id;
        amazing.title.setText(title);
        amazing.non_price.setText(price);
        amazing.off_price.setText(pprice);
        Picasso.with(MainActivity.this).load(picurl).into(amazing.pic);
        LinearLayout linearLayout=findViewById(R.id.Lin_product_amazing);

        int a = Integer.parseInt(pprice);
        if (a<=1){
            amazing.off_price.setVisibility(View.GONE);
            amazing.Lin_product_amazing_price_txt.setVisibility(View.GONE);
        }

        layoutParams=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayout.addView(amazing);

    }};

    HttpTimerProduct.HamazingP Product_Timer=new HttpTimerProduct.HamazingP() {
        @Override
        public void onPostExecute(String s) { try {
                JSONArray jsonArray =new JSONArray(s);for(int i=0;i<jsonArray.length();i++){
                    JSONObject object=jsonArray.getJSONObject(i);
                    int id=object.getInt("id");
                    String title =object.getString("text_mahsole");
                    String non_pic =String.valueOf(object.getInt("non_price_samsung_phone"));
                    String img= object.getString("pic");
                    String stringpicurl="http://"+HTTP_CODE_SERVER+"/digikala/img/"+img;

                    Product_timer(id,title,non_pic,stringpicurl);
                } } catch (JSONException e) {
            e.printStackTrace(); } }




    public void Product_timer(int id,String title,String price,String picurl){

        Timer timer=new Timer(MainActivity.this);
        timer.id=id;
        timer.title.setText(title);
        timer.non_price.setText(price);
        Picasso.with(MainActivity.this).load(picurl).into(timer.pic);
        LinearLayout linearLayout=findViewById(R.id.Lin_Timer_product);

        layoutParams=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayout.addView(timer);

    }};

    HttpViewProduct.HamazingP product_View=new HttpViewProduct.HamazingP() {
    @Override
    public void onPostExecute(String s) { try {
            JSONArray jsonArray =new JSONArray(s);
            for(int i=0;i<jsonArray.length();i++){
                JSONObject object=jsonArray.getJSONObject(i);
                int id=object.getInt("id");
                String title =object.getString("text_mahsole");
                String non_pic =String.valueOf(object.getInt("non_price_samsung_phone"));
                String img= object.getString("pic");
                String stringpicurl="http://"+HTTP_CODE_SERVER+"/digikala/img/"+img;

                Product_viewer(id,title,non_pic,stringpicurl);
            } } catch (JSONException e) {
            e.printStackTrace(); } }
    public void Product_viewer(int id ,String title,String price,String picurl){

        View_pr view_pr=new View_pr(MainActivity.this);
        view_pr.id=id;
        view_pr.title.setText(title);
        view_pr.non_price.setText(price);
        Picasso.with(MainActivity.this).load(picurl).into(view_pr.pic);
        LinearLayout linearLayout=findViewById(R.id.Lin_View_product);

        layoutParams=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayout.addView(view_pr);

    }};

    HttpSellProduct.HamazingP Product_sell=new HttpSellProduct.HamazingP() {
        @Override
        public void onPostExecute(String s) { try {
            JSONArray jsonArray =new JSONArray(s);
            for(int i=0;i<jsonArray.length();i++){
                JSONObject object=jsonArray.getJSONObject(i);
                int id=object.getInt("id");
                String title =object.getString("text_mahsole");
                String non_pic =String.valueOf(object.getInt("non_price_samsung_phone"));
                String img= object.getString("pic");
                String stringpicurl="http://"+HTTP_CODE_SERVER+"/digikala/img/"+img;

                Product_sell(id,title,non_pic,stringpicurl);
            } } catch (JSONException e) {
            e.printStackTrace(); } }
    public void Product_sell(int id ,String title,String price,String picurl){

        Sell sell=new Sell(MainActivity.this);
        sell.id=id;
        sell.title.setText(title);
        sell.non_price.setText(price);
        Picasso.with(MainActivity.this).load(picurl).into(sell.pic);
        LinearLayout linearLayout=findViewById(R.id.Lin_sell_product);

        layoutParams=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayout.addView(sell);

    }};

    private void list_Item_NavigationView() {

        ArrayList<buyMenuItemList> itemLists ;
        ArrayList<buyMenuItemList> itemLists1 ;
        ArrayList<buyMenuItemList> itemLists2 ;

        itemLists=new ArrayList<>();
        itemLists.add(new buyMenuItemList(R.drawable.home,"خانه"));
        itemLists.add(new buyMenuItemList(R.drawable.list,"لیست دسته بندی محصولات"));


        ListView  navigateLIstView=findViewById(R.id.navigate_list_view);
        navigateLIstView.setAdapter(new buyMenuAdapter(MainActivity.this,R.layout.buymenulist,itemLists));

        itemLists1=new ArrayList<>();
        itemLists1.add(new buyMenuItemList(R.drawable.star_one,"پیشنهادویژه دیجیکالا"));
        itemLists1.add(new buyMenuItemList(R.drawable.star_nim,"پرفروش ترین ها"));
        itemLists1.add(new buyMenuItemList(R.drawable.star_blank,"پربازدیدترین ها"));
        itemLists1.add(new buyMenuItemList(R.drawable.star_blank,"جدیدترین ها"));


        ListView  navigateLIstView1=findViewById(R.id.navigate_list_view1);
        navigateLIstView1.setAdapter(new buyMenuAdapter(MainActivity.this,R.layout.buymenulist,itemLists1));


        itemLists2=new ArrayList<>();
        itemLists2.add(new buyMenuItemList(R.drawable.settings,"تنظیمات"));
        itemLists2.add(new buyMenuItemList(R.drawable.help,"سوالات متداول"));



        ListView  navigateLIstView2=findViewById(R.id.navigate_list_view2);
        navigateLIstView2.setAdapter(new buyMenuAdapter(MainActivity.this,R.layout.buymenulist,itemLists2));

    }

    public void onclick_method(View view){

        switch (view.getId()){
            case R.id.main_button_start :
                preferences= PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                String name=preferences.getString("name",null);
                if (name!=null){
                   Intent intent = new Intent(MainActivity.this, Start_class_teachers.class);
                    intent.putExtra("id",name);
                   startActivity(intent);
                }
                else {
                    Snackbar snackbar=Snackbar.make(view, "شما هنوز ثبت نام نکرده اید ", Snackbar.LENGTH_LONG);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                        View snack1 = snackbar.getView();
                        snack1.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                    }
                    // View snack =snackbar.getView();
                    //snack.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    snackbar.setActionTextColor(Color.parseColor("#00c4ff"));
                    snackbar.show();

                }
                break;

            case R.id.btn_sign:
                if (sign.getText().toString().contains("ورود و ثبت نام ")){
                Intent intent =new Intent(MainActivity.this,SignActivity .class);
                startActivity(intent);}
                else {
                    if (management_Lin_sign.getVisibility()==View.VISIBLE)
                    management_Lin_sign.setVisibility(View.GONE);
                    else management_Lin_sign.setVisibility(View.VISIBLE);
                    }
                break;
            case R.id.hamberger_menu:
                derwerMenuList.openDrawer(Gravity.RIGHT);
                break;

            case R.id.account_Management_Lin_sign_Exist_Txt:
                derwerMenuList.closeDrawer(Gravity.RIGHT);
                sign.setText("ورود و ثبت نام ");
                SharedPreferences.Editor editor=preferences.edit();
                editor.remove("name");
                editor.commit();
                management_Lin_sign.setVisibility(View.GONE);
                break;
            case R.id.account_Management_Lin_sign_Shop_Txt:
                break;


        } }
        
}
