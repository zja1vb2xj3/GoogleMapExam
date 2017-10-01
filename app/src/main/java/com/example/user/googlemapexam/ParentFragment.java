package com.example.user.googlemapexam;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.*;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

/**
 * Created by user on 2017-10-01.
 */

public class ParentFragment extends Fragment implements GoogleMap.OnMarkerClickListener {
    private MapView mapView;
    private MarkerOptions markerOptions;
    private Marker marker;
    private Activity activity;
    private SupportMapFragment mapFragment;
    private GoogleMap googleMap;

    private LatLng latLng;

    public static ParentFragment newInstance(LatLng latLng){

        return new ParentFragment(latLng);
    }

    public ParentFragment(LatLng latLng){
        this.latLng = latLng;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_parent, container,false);

        mapView = (MapView) rootView.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);

        try{
            MapsInitializer.initialize(getActivity().getApplicationContext());
        }
        catch (Exception e){
            e.printStackTrace();
        }

        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap map) {
                Log.i("onMapReady", "start");
                googleMap = map;

                showCurrentLocation(latLng);

            }
        });

        return rootView;
    }

    private void showCurrentLocation(LatLng currentLatLng) {
        if (currentLatLng != null) {
            zoomMap(currentLatLng);
            markerOptions = new MarkerOptions();
            markerOptions.position(currentLatLng);
            markerOptions.title("현재위치");
            googleMap.clear();
            googleMap.addMarker(markerOptions);

            markerOptions = new MarkerOptions().position(Constant.JUGONG_APT).title("주공아파트");
            googleMap.addMarker(markerOptions);
            markerOptions = new MarkerOptions().position(Constant.GUNYOUNG_APT).title("건영아파트");
            googleMap.addMarker(markerOptions);
            markerOptions = new MarkerOptions().position(Constant.DUSAN_APT).title("두산아파트");
            googleMap.addMarker(markerOptions);
            markerOptions = new MarkerOptions().position(Constant.DEWOO_APT).title("대우아파트");
            googleMap.addMarker(markerOptions);

            googleMap.setOnMarkerClickListener(this);
        }
        else {
            Toast.makeText(getContext(), "현재위치 정보가 없습니다.", Toast.LENGTH_LONG).show();
        }
    }

    private void zoomMap(LatLng latLng) {
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));
    }

    private void setChildFragment(Fragment child){
        FragmentTransaction childFragment = getChildFragmentManager().beginTransaction();

        if(!child.isAdded()){
            childFragment.replace(R.id.childFragmentContainer, child);
            childFragment.addToBackStack(null);
            childFragment.commit();
        }
    }

    private int count = 0;
    @Override
    public boolean onMarkerClick(Marker marker) {

        ArrayList<String> datas = new ArrayList<>();

        count++;

        if(count > 5){
            count = 0;
            datas.clear();
        }

        else{
            for(int i=0; i<count; i++)
                datas.add(String.valueOf(i));
        }

        Fragment fragment = com.example.user.googlemapexam.ListFragment.newInstance(datas);
        setChildFragment(fragment);

        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mapView.onDestroy();
    }

}
