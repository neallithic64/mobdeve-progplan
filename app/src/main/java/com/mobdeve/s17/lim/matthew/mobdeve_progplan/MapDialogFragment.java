package com.mobdeve.s17.lim.matthew.mobdeve_progplan;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapDialogFragment extends DialogFragment
		implements OnMapReadyCallback{

	GoogleMap mMap;
	Button 	btn_selectLocation;
	private Double latitude, longitude;
	private GpsTracker gpsTracker;
	Context context;
	public MapDialogFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View rootView = inflater.inflate(R.layout.fragment_map_dialog, container, false);
//		View rootView = inflater.inflate(R.layout.fragment_map_temp, container, false);

		SupportMapFragment mapFragment = (SupportMapFragment) getFragmentManager().findFragmentById(R.id.map);
//		mapFragment.getMapAsync(this);
		btn_selectLocation = rootView.findViewById(R.id.btn_selectLocation);
		btn_selectLocation.setOnClickListener(v->{
			dismiss();
		});
		return rootView;
	}

//	TODO : Make it so the marker that leads to current locations work
	@Override
	public void onMapReady(GoogleMap googleMap) {
		gpsTracker = new GpsTracker(context);

		if(gpsTracker.canGetLocation)
		{
			latitude = gpsTracker.getLatitude();
			longitude = gpsTracker.getLongitude();
		}else{
			gpsTracker.showSettingsAlert();
		}
		mMap = googleMap;
		mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

		LatLng latLng = new LatLng(latitude,longitude);
		mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,11));

		MarkerOptions markerOptions = new MarkerOptions();
		markerOptions.position(latLng);
		markerOptions.title("Current Position");
		markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
		mMap.addMarker(markerOptions);
	}

}
