package com.example.digikala.Simple_Adaptor;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.digikala.R;

import java.util.ArrayList;




public class buyMenuAdapter extends ArrayAdapter {

    public int rescorseId;
    public Activity context;
    public ArrayList<buyMenuItemList> object;


    public buyMenuAdapter(Activity context, int resource, ArrayList objects) {
        super(context, resource, objects);
        this.rescorseId=resource;
        this.context=context;
        this.object=objects;

    }


    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
       View view=convertView;
       view=this.context.getLayoutInflater().inflate(this.rescorseId,null);

        ImageView imageView = view.findViewById(R.id.imgTitleMenuList);
        TextView textView = view.findViewById(R.id.txtTitleMenuList);

       buyMenuItemList buyMenuItemList =  object.get(position);
       textView.setText(buyMenuItemList.title);
       imageView.setImageResource(buyMenuItemList.img);
       return  view;
    }
}
