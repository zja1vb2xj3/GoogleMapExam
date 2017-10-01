package com.example.user.googlemapexam;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by user on 2017-10-01.
 */

public class ListFragment extends Fragment {

    public static ListFragment newInstance(ArrayList datas) {
        //        listView.setAdapter(listViewAdapter);
//        listViewAdapter.clearItem();
//        listViewAdapter.addItem(marker.getTitle());
        return new ListFragment(datas);
    }

    private ArrayList<String> datas;
    private ListView listView;
    private ListViewAdapter listViewAdapter;

    public ListFragment(ArrayList<String> datas) {
        this.datas = datas;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);

        listView = (ListView) rootView.findViewById(R.id.listview);

        listViewAdapter = new ListViewAdapter();

        listViewAdapter.clearItem();

        for (int i = 0; i < datas.size(); i++) {
            listViewAdapter.addItem(datas.get(i));
        }


        listView.setAdapter(listViewAdapter);

        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
}
