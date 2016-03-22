//package com.jijjy.grace.mycam;
//
//import android.app.Activity;
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.ImageView;
//
//import com.jijjy.grace.mycam.R;
//
//import java.util.ArrayList;
//
///**
// * Created by jaykayitare on 3/21/16.
// */
//public class GridViewAdapter extends ArrayAdapter {
//    private Context context;
//    private int layoutResourceId;
//    private ArrayList data = new ArrayList();
//
//    public GridViewAdapter(Context context, int layoutResourceId, ArrayList data){
//        super(context, layoutResourceId, data);
//        this.layoutResourceId = layoutResourceId;
//        this.context = context;
//        this.data = data;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent){
//        View row = convertView;
//        ViewHolder holder = null;
//
//        if(row == null){
//            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
//            row = inflater.inflate(layoutResourceId, parent, false);
//            holder = new ViewHolder();
//            holder.image = (ImageView)row.findViewById(R.id.image);
//            row.setTag(holder);
//            }
//        else {
//            holder = (ViewHolder)row.getTag();
//        }
//        ImageItem item = data.get(position);
//        holder.image.setImageBitmap(item.getImage());
//        return row;
//    }
//    static class ViewHolder{
//        ImageView image;
//    }
//}
