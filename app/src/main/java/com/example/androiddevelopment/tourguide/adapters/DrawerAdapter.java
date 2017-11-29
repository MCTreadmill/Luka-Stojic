package com.example.androiddevelopment.tourguide.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.androiddevelopment.tourguide.R;
import com.example.androiddevelopment.tourguide.model.NavigationItem;

import java.util.ArrayList;

/**
 * Created by androiddevelopment on 29.11.17..
 */

public class DrawerAdapter extends BaseAdapter {

    Context context;
    ArrayList<NavigationItem> items;

    public DrawerAdapter(Context context, ArrayList<NavigationItem> items) {
        this.context = context;
        this.items = items;
    }

    // Returns the item count
    @Override
    public int getCount() {
        return items.size();
    }

    // Returns an item
    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    // Returns an item ID
    @Override
    public long getItemId(int position) {
        return 0;
    }

    // Returns a view that corresponds with an item
    @Override
    public View getView(int position, View view, ViewGroup parent) {

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.drawer_item, null);
        }

        TextView tvTitle = (TextView) view.findViewById(R.id.tv_drawer_title);

        tvTitle.setText(items.get(position).getTitle());

        return view;
    }
}
