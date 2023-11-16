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

import com.example.digikala.Activity.Wait_Activity;
import com.example.digikala.R;

public class Sell extends LinearLayout {

    LinearLayout linearLayout;

    public int id;
    public static ImageView pic;
    public static TextView title;
    public static TextView non_price;

    public Sell(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inint(context);
    }
    public Sell(Context context) {
        super(context);
        inint(context);
    }

    public Sell(Context context, AttributeSet attrs) {
        super(context, attrs);
        inint(context);
    }


    public void inint(final Context context){
        LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view =  layoutInflater.inflate(R.layout.product_sell,this,true);



        pic=view.findViewById(R.id.Lin_product_sell_img);
        title=view.findViewById(R.id.Lin_product_sell_title);
        non_price=view.findViewById(R.id.Lin_product_sell_pprice);
        linearLayout=view.findViewById(R.id.Lin_product_sell_LinearLayout);
        linearLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(context, Wait_Activity.class);
                intent.putExtra("id",id);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });


    }}
