package com.example.digikala.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.digikala.AsyncTask.HttpDetalis;
import com.example.digikala.AsyncTask.HttpRequest;
import com.example.digikala.AsyncTask.HttpSellProduct;
import com.example.digikala.AsyncTask.HttpTimerProduct;
import com.example.digikala.AsyncTask.HttpWait_Activity;
import com.example.digikala.Product.Custom_point_class;
import com.example.digikala.Product.Sell;
import com.example.digikala.Product.Timer;
import com.example.digikala.R;
import com.example.digikala.Simple_Adaptor.SizeAdaptor;
import com.sackcentury.shinebuttonlib.ShineButton;
import com.squareup.picasso.Picasso;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import static com.example.digikala.Activity.MainActivity.preferences;
import static com.example.digikala.Activity.MainActivity.HTTP_CODE_SERVER;

public class Show_MH extends AppCompatActivity {

    LinearLayout.LayoutParams layoutParams;
    TextView show_mh_naqd_main;
    LinearLayout show_mh_details_main;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show__mh);
        show_mh_naqd_main=findViewById(R.id.show_mh_naqd_main);
        show_mh_details_main=findViewById(R.id.lin_details1);

        Bundle bundle=getIntent().getExtras();
        String id =bundle.getString("id");


        //photo
        HttpWait_Activity httpWait_activity = new HttpWait_Activity(Show_MH.this, null);
        httpWait_activity.execute("http://"+HTTP_CODE_SERVER+"/digikala/wait_pro/indext.php",id);
        HttpWait_Activity.http(Slider);


        //mahsoalt nemone
        HttpTimerProduct httpTimerProduct =new HttpTimerProduct(Show_MH.this,null);
        httpTimerProduct.execute("http://"+HTTP_CODE_SERVER+"/digikala/time_product/readadaptor_time.php");
        HttpTimerProduct.http(Product_Timer);

        HttpSellProduct httpSellProduct=new HttpSellProduct(Show_MH.this,null);
        httpSellProduct.execute("http://"+HTTP_CODE_SERVER+"/digikala/sell_product/readadaptor_sell.php");
        HttpSellProduct.http(Product_sell);

        // timer
        HttpRequest httpRequest_Timer_ScrollView = new HttpRequest(Show_MH.this, null);
        httpRequest_Timer_ScrollView.execute("http://"+HTTP_CODE_SERVER+"/digikala/timer/exsit.php");
        HttpRequest.http(Timer_ScrollView);

        //namyesh tozihat
        HttpDetalis httpDetalis =new HttpDetalis(Show_MH.this,null);
        httpDetalis.execute("http://"+HTTP_CODE_SERVER+"/digikala/img/details/details.php",id);
        HttpDetalis.http(httpDetali);



        Gender();
        preferences_code_sign();
       // TabLayout_Code();





    }

    @Override
    protected void onRestart() {
        super.onRestart();
        preferences_code_sign(); }

    private void preferences_code_sign() {

        preferences= PreferenceManager.getDefaultSharedPreferences(Show_MH.this);
        String name=preferences.getString("name",null);

        LinearLayout linearLayout=findViewById(R.id.Llt_CreateAccunt_Sign);

        if (name==null){
            linearLayout.setVisibility(View.VISIBLE);
        }
        else {
            linearLayout.setVisibility(View.GONE);
        }

    }

    private void Gender(){
        CoordinatorLayout coordinatorLayout;
        LinearLayout linearLayout;
        coordinatorLayout=findViewById(R.id.coordinator_show_MH);
        linearLayout=findViewById(R.id.Lin_Show_MH_Gender);
        //woman
        boolean b=false;
        if (b){
            coordinatorLayout.setBackgroundColor(Color.argb(150,200,100,200));
            linearLayout.setBackgroundColor(Color.argb(150,200,100,200));
        }
    }


    //Error    *************
    private void TabLayout_Code() {
       TabLayout tabLayout;

        final NestedScrollView nestedScrollView=findViewById(R.id.NestedScrollView_Show_MH);
        tabLayout=findViewById(R.id.TabLayout_Show_MH);


        final TabLayout finalTabLayout = tabLayout;
        nestedScrollView.setOnScrollChangeListener(
                new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView nestedScrollView, int i, int i1, int i2, int i3) {

                LinearLayout naqd = findViewById(R.id.naqd);
                LinearLayout show_mh_lin_detail = findViewById(R.id.show_mh_lin_detail);
                LinearLayout show_mh_nazarat_afarad_LL = findViewById(R.id.show_mh_nazarat_afarad_LL);
                 CardView show_mh_mahsolatmoshabeh_card = findViewById(R.id.show_mh_mahsolatmoshabeh_card);

                int naqd_I[] = new int[2];
                naqd.getLocationInWindow(naqd_I);

                int[] there = new int[2];
                show_mh_lin_detail.getLocationOnScreen(there);

                int[] four = new int[2];
                show_mh_nazarat_afarad_LL.getLocationOnScreen(four);

                int[] five = new int[2];
                show_mh_mahsolatmoshabeh_card.getLocationOnScreen(four);



           if     ( i1<=naqd_I[1]+700    &&   i1>= 0 )          finalTabLayout.setScrollPosition(0,0,true);
           else if (i1>= naqd_I[1]   &&   i1<= there[1]+700)   finalTabLayout.setScrollPosition(1,0,true);
           else if (i1>= there[1]+700 &&  i1<= four[1])   finalTabLayout.setScrollPosition(2,0,true);
           else if (i1>= four[1]+700  &&   i1<= five[1])     finalTabLayout.setScrollPosition(3,0,true);
           else if (i1>= five[1])                      finalTabLayout.setScrollPosition(4,0,true);





            }
        });




        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                switch (tab.getPosition()) {
                    case 0:
                        nestedScrollView.fullScroll(View.FOCUS_UP);

                        break;
                    case 1:
                        nestedScrollView.fullScroll(View.resolveSize(3600,3600));
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    // Continue for each tab in TabLayout
                     }}
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });





        tabLayout=findViewById(R.id.TabLayout_Show_MH);

        int aa =2;
        int a =tabLayout.getTabCount();
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {}

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}

        }); }






    public void click_wait(View view){
        switch (view.getId()){
            case R.id.show_back_img:
                Intent intent=new Intent(Show_MH.this,MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.Llt_CreateAccunt_Sign:
                Intent intent1=new Intent(Show_MH.this,SignActivity.class);
                startActivity(intent1);
                break;
            case  R.id.sshow_mh_star_teach:

                Snackbar snackbar=Snackbar.make(view, "اساتید برگذیده \uD83D\uDE0D", Snackbar.LENGTH_LONG);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    View snack1 = snackbar.getView();
                    snack1.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                }
                // View snack =snackbar.getView();
                //snack.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                snackbar.setActionTextColor(Color.parseColor("#00c4ff"));
                snackbar.show();


                break;
            case R.id.Llt_txt_activity_show_mh:
                Intent intent11=new Intent(Show_MH.this,SignActivity.class);
                startActivity(intent11);
                break;
            case R.id.Llt_Img_activity_show_mh:
                Intent intent111=new Intent(Show_MH.this,SignActivity.class);
                startActivity(intent111);
                break;
            case R.id.show_mh_naqd_txt: show_mh_naqd_main.setVisibility(View.VISIBLE);break;
            case R.id.show_mh_naqd_img: show_mh_naqd_main.setVisibility(View.VISIBLE);break;
            case R.id.show_mh_naqd_lin: show_mh_naqd_main.setVisibility(View.VISIBLE);break;


//            case R.id.show_mh_details_txt:
//                TextView textView=findViewById(R.id.show_mh_details_txt);
//                textView.setVisibility(View.GONE);
//                TextView textView1=findViewById(R.id.show_mh_details_top_txt);
//                textView1.setTextSize(15);
//
//                show_mh_details_main.setVisibility(View.VISIBLE)
//            ;break;

        }
    }

    HttpWait_Activity.HamazingP Slider =new HttpWait_Activity.HamazingP() {
        @Override
        public void onPostExecute(String s) {
            slider(s);
        }
        private void slider(String s) {
            ImageView show_mh_img =findViewById(R.id.show_MH_Image_View);


            try {
                JSONArray jsonArray =new JSONArray(s);
                JSONObject object=jsonArray.getJSONObject(0);
                String stringpicurl="http://"+HTTP_CODE_SERVER+"/digikala/img/"+object.getString("img");
                Picasso.with(Show_MH.this).load(stringpicurl).into(show_mh_img);

            } catch (JSONException e) { e.printStackTrace(); } }};


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

            Timer timer=new Timer(Show_MH.this);
            timer.id=id;
            timer.title.setText(title);
            timer.non_price.setText(price);
            Picasso.with(Show_MH.this).load(picurl).into(timer.pic);
            LinearLayout linearLayout=findViewById(R.id.Lin_mahsolatmoshabeh_Show_MH);

            layoutParams=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            linearLayout.addView(timer);

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

            Sell sell=new Sell(Show_MH.this);
            sell.id=id;
            sell.title.setText(title);
            sell.non_price.setText(price);
            Picasso.with(Show_MH.this).load(picurl).into(sell.pic);
            LinearLayout linearLayout=findViewById(R.id.Lin_mahsolatmoshabeh_Show_TEACH);

            layoutParams=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            linearLayout.addView(sell);

        }};

    HttpRequest.HRequest Timer_ScrollView= new HttpRequest.HRequest(){
        @Override public void onPostExecute(final String s) {
            Timer_ScrollView_shelve(s);
        }
        private void Timer_ScrollView_shelve(String s) {
            final TextView timer_sec ,timer_min ,timer_hour;

            final int anIntTimer[];
            String[] ma = s.split(":");

            timer_hour=findViewById(R.id.Timer_hour_mh);
            timer_sec=findViewById(R.id.Timer_sec_mh);
            timer_min=findViewById(R.id.Timer_min_mh);

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


    final HttpDetalis.HamazingP httpDetali = new HttpDetalis.HamazingP() {
        @Override
        public void onPostExecute(String s) {
            try {
                JSONArray jsonArray = new JSONArray(s);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    String title = object.getString("text_mahsole");
                    String non_pic = String.valueOf(object.getInt("non_price_samsung_phone"));
                    String off_pic = String.valueOf(object.getInt("off_price_samsung_phone"));
                    String color = object.getString("color");
                    String Description = object.getString("Description");
                    String brand = object.getString("brand");
                    String details = object.getString("details");
                    String garanti = object.getString("garanti");
                    String fast = object.getString("fast");
                    String size = object.getString("size");
                    String details_pro = object.getString("details_pro");

                    String nazarat = object.getString("nazarat");
                    String majmoh_nazarat = object.getString("majmoh_nazarat");



                   // String nazarat_pro =object.getString("tag_id");


                    //صحفه کامنت
                   // String coment = object.getString("coment");


                    TextView offpayin, offbala, textmahsolt, textmahsolt2, descriptiont, brandt, majmoh_nazaratt
                            ,show_mh_qeyat_batakhfifi, show_mh_qeyat_qabltakhfifi_t, details_txt, garanti_txt
                            ;
                    LinearLayout show_mh_takhfif_lin, show_mh_takhfif1_lin, show_mh_color_lin,
                            show_mh_color_lin1, fast_lin0, fast_lin1, show_mh_size_lin1, show_mh_garantt_lin;
                    RadioGroup radioGroup;



                    ImageButton shine_bb_teach_star=findViewById(R.id.sshow_mh_star_teach);


                            offpayin = findViewById(R.id.show_mh_off_payin_t);
                    offbala = findViewById(R.id.show_mh_off_safe_t);
                    textmahsolt = findViewById(R.id.show_mh_textmahsol_t);
                    descriptiont = findViewById(R.id.show_mh_Description_t);
                    brandt = findViewById(R.id.show_mh_brand_t);
                    majmoh_nazaratt = findViewById(R.id.show_mh_nazarat_majmohe_t);
                    textmahsolt2 = findViewById(R.id.show_mh_textmahsol_t2);
                    details_txt = findViewById(R.id.show_mh_details);
                    show_mh_qeyat_batakhfifi = findViewById(R.id.show_mh_qeyat_batakhfifi_t);
                    show_mh_qeyat_qabltakhfifi_t = findViewById(R.id.show_mh_qeyat_qabltakhfifi_t);
                    garanti_txt = findViewById(R.id.show_mh_garantt);
                    show_mh_takhfif_lin = findViewById(R.id.show_mh_takhfif_lin);
                    show_mh_takhfif1_lin = findViewById(R.id.show_mh_takhfif1_lin);
                    show_mh_color_lin = findViewById(R.id.show_mh_color_lin);
                    show_mh_color_lin1 = findViewById(R.id.show_mh_color_lin1);
                    fast_lin0 = findViewById(R.id.show_mh_fast0);
                    fast_lin1 = findViewById(R.id.show_mh_fast1);
                    show_mh_size_lin1 = findViewById(R.id.show_mh_size_lin1);
                    radioGroup = findViewById(R.id.show_mh_size_lin);
                    show_mh_garantt_lin = findViewById(R.id.show_mh_garantt_lin);
                    RatingBar ratingBar =findViewById(R.id.show_mh_rating);


                    show_mh_naqd_main.setText(details_pro);
                    garanti_txt.setText(garanti);
                    details_txt.setText(details);
                    textmahsolt.setText(title);
                    textmahsolt2.setText(title);
                    descriptiont.setText(Description);
                    brandt.setText(brand);
                    majmoh_nazaratt.setText("(" + majmoh_nazarat + ")");
                    show_mh_qeyat_qabltakhfifi_t.setText(non_pic);

                    int ratanig_int =Integer.parseInt(majmoh_nazarat);
                    ratingBar.setRating(ratanig_int);

                    int off, non;
                    off = Integer.parseInt(off_pic);
                    non = Integer.parseInt(non_pic);



                    int fast_i;
                    fast_i = Integer.parseInt(fast);
                    //گارانتی
                    if (fast_i == 0) {
                        fast_lin0.setVisibility(View.GONE);
                    } else {
                        fast_lin1.setVisibility(View.GONE); }



                    //یعنی تخفیف داره
                    if (off != 0) {
                        show_mh_takhfif_lin.setVisibility(View.VISIBLE);
                        show_mh_takhfif1_lin.setVisibility(View.VISIBLE);

                        int jame = (non - ((non * off) / 100));
                        show_mh_qeyat_batakhfifi.setText(String.valueOf(jame));
                        offbala.setText(off_pic + "%");
                        offpayin.setText(off_pic + "%");
                    } else {
                        TextView ttt = findViewById(R.id.show_mh_qeyat_batakhfifi_tt);
                        ttt.setTextSize(22);
                        show_mh_qeyat_batakhfifi.setTextSize(22);
                        show_mh_qeyat_qabltakhfifi_t.setVisibility(View.GONE);
                        offpayin.setVisibility(View.GONE);
                        show_mh_takhfif_lin.setVisibility(View.GONE);
                        show_mh_takhfif1_lin.setVisibility(View.GONE); }


                    //رنگش
                    if (color.equals("0") || color.equals(null)) {

                        show_mh_color_lin.setVisibility(View.GONE);
                        show_mh_color_lin1.setVisibility(View.GONE);
                    } else {
                        show_mh_color_lin.setVisibility(View.VISIBLE);
                        show_mh_color_lin1.setVisibility(View.VISIBLE);
                    }

                    //سایزش
                    if (size.equals("0") || size.equals("")) {
                        radioGroup.setVisibility(View.GONE);
                        show_mh_size_lin1.setVisibility(View.GONE);
                    } else {
                        radioGroup.setVisibility(View.VISIBLE);
                        show_mh_size_lin1.setVisibility(View.VISIBLE);
                        radioGroup.removeAllViewsInLayout();
                        String[] ss = size.split(" ");
                        for (int ii = 0; ii < ss.length; ii++) {

                            SizeAdaptor sizeAdaptor = new SizeAdaptor(Show_MH.this);
                            sizeAdaptor.id = Integer.parseInt(ss[ii]);
                            sizeAdaptor.title.setText(ss[ii]);

                            layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                            radioGroup.addView(sizeAdaptor);
                            radioGroup.focusableViewAvailable(sizeAdaptor);
                                        ////// moshkel injast
                        }
                    }
                    //گارانتی
                    if (garanti.length() == 0) {
                        show_mh_garantt_lin.setVisibility(View.GONE); }


                    //مشخصات استاد
                   // shine_bb_teach_star.setBtnColor(Color.RED);


                    //مشخصات سری جدید
                    //progress bar
                    LinearLayout linearLayout=findViewById(R.id.lin_details);


                    //صفحه مشخصات
                    JSONArray lin_jason =object.getJSONArray("tag_id");
                    int tag = lin_jason.length();
                    Toast.makeText(Show_MH.this, tag+"", Toast.LENGTH_SHORT).show();
                    for (int jj = 0 ; jj<lin_jason.length();jj++){
                        String string_txt = (String) lin_jason.get(jj);
                        Custom_point_class custom_point_class =new Custom_point_class(Show_MH.this);
                        custom_point_class.txt.setText(string_txt);

                        layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        linearLayout.addView(custom_point_class);}


                    //مشخصات استاد


                    //مشخصات سری جدید حرفه ای
                    //progress bar
                    LinearLayout linearLayout2=findViewById(R.id.lin_details1);

                    //صفحه مشخصات
                    JSONArray lin_jason2 =object.getJSONArray("tag_id");
                    for (int jj = 0 ; jj<lin_jason2.length();jj++){
                        String string_txt = (String) lin_jason2.get(jj);
                        Custom_point_class custom_point_class =new Custom_point_class(Show_MH.this);
                        custom_point_class.txt.setText(string_txt);

                        layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        linearLayout2.addView(custom_point_class);
                    }
                }}catch (JSONException e) {e.printStackTrace();} }};
}
