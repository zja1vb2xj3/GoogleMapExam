package com.example.user.googlemapexam;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 2017-10-01.
 */

public class ListViewAdapter extends BaseAdapter {

    private ArrayList<ListViewItem> listViewItems;

    public ListViewAdapter(){
        listViewItems = new ArrayList<ListViewItem>();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final Context context = parent.getContext();

        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listview_item, parent, false);
        }

        TextView textView = (TextView) view.findViewById(R.id.list_textview);

        ListViewItem listViewItem = listViewItems.get(position);

        textView.setText(listViewItem.getItemText());

        return view;
    }

    @Override
    public int getCount() {
        return listViewItems.size();
    }

    @Override
    public Object getItem(int position) {
        return listViewItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addItem(String data){
        ListViewItem item = new ListViewItem();

        item.setItemText(data);

        listViewItems.add(item);

    }

    public void clearItem(){
        listViewItems.clear();
    }
}
