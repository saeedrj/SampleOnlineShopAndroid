package com.example.digikala.Simple_Adaptor;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.example.digikala.R;

public class SizeAdaptor extends LinearLayout {
    boolean aBoolean=true;

    public  int id;
    public static RadioButton title;

    public SizeAdaptor(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inint(context);
    }
    public SizeAdaptor(Context context) {
        super(context);
        inint(context);
    }

    public SizeAdaptor(Context context, AttributeSet attrs) {
        super(context, attrs);
        inint(context);
    }


    public void inint(final Context context){
        LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view =layoutInflater.inflate(R.layout.size_adaptor,this,true);

        ////
        title=view.findViewById(R.id.kish);
        title.setTextSize(12);
        title.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {


            //    if (aBoolean){
//                cardView.setBackgroundResource(R.drawable.nuliiy);
      //          aBoolean=false;}
//                else{
//                    cardView.setBackgroundResource(R.drawable.nuliiy_show_mh_dark);
//                    aBoolean=true;
//                }

            }});


    }
}
