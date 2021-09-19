package com.mobdeve.s17.lim.matthew.mobdeve_progplan;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
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
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapDialogFragment extends DialogFragment
		implements OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationClickListener, ActivityCompat.OnRequestPermissionsResultCallback {
	GoogleMap mMap;
	Button 	btn_selectLocation;
	private Double latitude, longitude;
	private GpsTracker gpsTracker;
	private Context context;
	private LocationManager locationManager;
	private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
	private boolean permissionDenied = false;
	private static final int LOCATION_MIN_UPDATE_TIME = 10;
	private static final int LOCATION_MIN_UPDATE_DISTANCE = 1000;
	private Location location = null;
	private CreateProgActivity createProgActivity;

	private LocationListener locationListener = new LocationListener() {
		@Override
		public void onLocationChanged(Location location) {
            // drawMarker(location, "I am here");
			// locationManager.removeUpdates(locationListener);
		}
		@Override
		public void onStatusChanged(String s, int i, Bundle bundle) {
		}
		@Override
		public void onProviderEnabled(String s) {
		}
		@Override
		public void onProviderDisabled(String s) {
		}
	};

	public MapDialogFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View rootView = inflater.inflate(R.layout.fragment_map_dialog, container, false);
//		View rootView = inflater.inflate(R.layout.fragment_map_temp, container, false);

		createProgActivity = new CreateProgActivity();
		SupportMapFragment mapFragment = (SupportMapFragment) createProgActivity.getSupportFragmentManager()
				.findFragmentById(R.id.map);
		mapFragment.getMapAsync(this::onMapReady);
		locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
		// flpClient = LocationServices.getFusedLocationProviderClient(getActivity());
		btn_selectLocation = rootView.findViewById(R.id.btn_selectLocation);
		btn_selectLocation.setOnClickListener(v -> {
			dismiss();
		});
		return rootView;
	}

//	TODO : Make it so the marker that leads to current locations work
	@Override
	public void onMapReady(GoogleMap googleMap) {
		Log.i("onMapReady", "CALLING ON MAP READY");
		gpsTracker = new GpsTracker(context);

		if (gpsTracker.canGetLocation) {
			latitude = gpsTracker.getLatitude();
			longitude = gpsTracker.getLongitude();
		} else {
			gpsTracker.showSettingsAlert();
		}
		mMap = googleMap;
		mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
		enableMyLocation();
		mMap.setOnMyLocationButtonClickListener(this);
		mMap.setOnMyLocationClickListener(this);

		LatLng latLng = new LatLng(latitude,longitude);
		mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,11));

		MarkerOptions markerOptions = new MarkerOptions();
		markerOptions.position(latLng);
		markerOptions.title("Current Position");
		markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
		mMap.addMarker(markerOptions);
	}

	@Override
	public boolean onMyLocationButtonClick() {
		Toast.makeText(getActivity().getApplicationContext(), "MyLocation button clicked", Toast.LENGTH_SHORT).show();
		return false;
	}

	@Override
	public void onMyLocationClick(@NonNull Location location) {
		Toast.makeText(getActivity().getApplicationContext(), "Current location:\n" + location, Toast.LENGTH_LONG).show();
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
			return;
		}
		if (PermissionUtils.isPermissionGranted(permissions, grantResults, Manifest.permission.ACCESS_FINE_LOCATION)) {
			// Enable the my location layer if the permission has been granted.
			enableMyLocation();
		} else {
			// Permission was denied. Display an error message
			// Display the missing permission error dialog when the fragments resume.
			permissionDenied = true;
		}
	}

	private void enableMyLocation() {
		if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)
				== PackageManager.PERMISSION_GRANTED) {
			if (mMap != null) {
				mMap.setMyLocationEnabled(true);
				boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
				boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
				if (!isGPSEnabled && !isNetworkEnabled) {
					Toast.makeText(getActivity().getApplicationContext(), "failed", Toast.LENGTH_LONG).show();
				} else {
					location = null;
					if (isGPSEnabled) {
						locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_MIN_UPDATE_TIME, LOCATION_MIN_UPDATE_DISTANCE, locationListener);
						location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
					}
					if (isNetworkEnabled) {
						locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, LOCATION_MIN_UPDATE_TIME, LOCATION_MIN_UPDATE_DISTANCE, locationListener);
						location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
					}
					if (location != null) {
						if (this.mMap != null) {
							LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
							mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
						}
					}
				}
			}
		} else {
			// Permission to access the location is missing. Show rationale and request permission
			// PermissionUtils.requestPermission(createProgActivity, LOCATION_PERMISSION_REQUEST_CODE,
			// 		Manifest.permission.ACCESS_FINE_LOCATION, true);
		}
	}
}
