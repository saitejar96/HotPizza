package com.example.saiteja.hotpizza;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by sai teja on 16-12-2016.
 */

public class GridViewAdapter2 extends ArrayAdapter {
    private Context context;
    private int layoutResourceId;
    private ArrayList data = new ArrayList();

    public GridViewAdapter2(Context context, int layoutResourceId, ArrayList<Tvshow> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.imageTitle = (TextView) row.findViewById(R.id.text_small);
            holder.image = (ImageView) row.findViewById(R.id.image_small);
            holder.img = (TextView) row.findViewById(R.id.img);
            holder.qual = (TextView) row.findViewById(R.id.qual);
            holder.vidurl = (TextView) row.findViewById(R.id.vidurl);
            holder.bool = (TextView)row.findViewById(R.id.text5);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        Tvshow item = (Tvshow) data.get(position);
        holder.imageTitle.setText(item.getTv_name());
        holder.img.setText(item.getTv_img());
        holder.qual.setText(item.getTv_eps());
        holder.vidurl.setText(item.getTv_url());
        holder.bool.setText("tvshow");
        Picasso.with(context).load(item.getTv_img()).into(holder.image);
        return row;
    }

    static class ViewHolder {
        TextView imageTitle;
        TextView qual;
        TextView img;
        TextView vidurl;
        TextView bool;
        ImageView image;
    }
}