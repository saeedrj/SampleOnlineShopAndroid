package com.example.digikala.Product;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.digikala.R;

public class Custom_point_class extends LinearLayout{

        public static ProgressBar pb;
        public static TextView txt;


        public Custom_point_class(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            inint(context);
        }
        public Custom_point_class(Context context) {
            super(context);
            inint(context);
        }

        public Custom_point_class(Context context, AttributeSet attrs) {
            super(context, attrs);
            inint(context);
        }


        public void inint(final Context context){
            LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            View view =layoutInflater.inflate(R.layout.custom_point,this,true);
            pb=view.findViewById(R.id.custom_point_PB);
            txt=view.findViewById(R.id.custom_point_txt);


        }
    }
