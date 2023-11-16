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

public class View_pr extends LinearLayout {


    LinearLayout linearLayout;

    public int id;
    public static ImageView pic;
    public static TextView title;
    public static TextView non_price;

    public View_pr(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inint(context);
    }
    public View_pr(Context context) {
        super(context);
        inint(context);
    }

    public View_pr(Context context, AttributeSet attrs) {
        super(context, attrs);
        inint(context);
    }


    public void inint(final Context context){
        LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view =layoutInflater.inflate(R.layout.product_view,this,true);



        pic=view.findViewById(R.id.Lin_product_view_img);
        title=view.findViewById(R.id.Lin_product_view_title);
        non_price=view.findViewById(R.id.Lin_product_view_pprice);
        linearLayout=view.findViewById(R.id.product_view_linearLayout);
        linearLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(context, Wait_Activity.class);
                intent.putExtra("id",id);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });


    }
}
