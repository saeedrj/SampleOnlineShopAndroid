package com.example.digikala.Product;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.digikala.Activity.MainActivity;
import com.example.digikala.Activity.Wait_Activity;
import com.example.digikala.R;


public class Amazing extends LinearLayout {



    LinearLayout linearLayout;

    public  int id;
    public static ImageView pic;
    public static TextView title;
    public static TextView off_price;
    public static TextView non_price;
    public static TextView Lin_product_amazing_price_txt;

    public Amazing(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inint(context);
    }
    public Amazing(Context context) {
        super(context);
        inint(context);
    }

    public Amazing(Context context, AttributeSet attrs) {
        super(context, attrs);
        inint(context);
    }


    public void inint(final Context context){
        LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view =layoutInflater.inflate(R.layout.product_amazing,this,true);



        pic=view.findViewById(R.id.Lin_product_amazing_img);
        title=view.findViewById(R.id.Lin_product_amazing_title);
        non_price=view.findViewById(R.id.Lin_product_amazing_price);
        off_price=view.findViewById(R.id.Lin_product_amazing_price);
        Lin_product_amazing_price_txt=view.findViewById(R.id.Lin_product_amazing_price_txt);
        linearLayout=view.findViewById(R.id.Lin_product_amazing_linerLayout);
        linearLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {


            Intent intent =new Intent(context, Wait_Activity.class);
            intent.putExtra("id",id);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);

            }});

    }
}
