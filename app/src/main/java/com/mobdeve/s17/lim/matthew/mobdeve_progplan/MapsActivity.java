package com.mobdeve.s17.lim.matthew.mobdeve_progplan;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mobdeve.s17.lim.matthew.mobdeve_progplan.databinding.ActivityMapsBinding;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity
		implements OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationClickListener, ActivityCompat.OnRequestPermissionsResultCallback {
	private GoogleMap mMap;
	private ActivityMapsBinding binding;
	private Double latitude, longitude;
	private GpsTracker gpsTracker;
	private LocationManager locationManager;
	private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
	private boolean permissionDenied = false;
	private static final int LOCATION_MIN_UPDATE_TIME = 10;
	private static final int LOCATION_MIN_UPDATE_DISTANCE = 1000;
	private Location location = null;
	private LatLng markerLoc;
	private LatLng defaultLoc;

	private LocationListener locationListener = new LocationListener() {
		@Override
		public void onLocationChanged(Location location) {
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

	public MapsActivity() {
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		binding = ActivityMapsBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());

		// Obtain the SupportMapFragment and get notified when the map is ready to be used.
		SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map);
		mapFragment.getMapAsync(this);
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		binding.btnSelectLocation.setOnClickListener(v -> {
			Geocoder geocoder = new Geocoder(this);
			List<Address> listAddr;
//			Toast.makeText(getApplicationContext(),markerLoc.toString(),Toast.LENGTH_LONG).show();
			try {
				listAddr = geocoder.getFromLocation(markerLoc.latitude, markerLoc.longitude, 1);
				String locName = "";
				if (listAddr != null && listAddr.size() > 0) {
					Address addr = listAddr.get(0);
					String city = addr.getLocality();
					if(city.indexOf("City of") != -1)
						city = city.substring(city.indexOf("City of ") + 7);
					locName += addr.getThoroughfare() + ", " + city + " City";
//					Toast.makeText(getApplicationContext(),locName,Toast.LENGTH_LONG).show();
				}
				// not sure if this should be put here
				Intent data = new Intent();

				Bundle bundleloc = new Bundle();
				bundleloc.putString("locationinput", locName);
				bundleloc.getString("locationinput");

				Toast.makeText(getApplicationContext(),locName, Toast.LENGTH_LONG).show();

				data.putExtras(bundleloc);
				setResult(RESULT_OK, data);

				finish();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}


	/**
	 * Manipulates the map once available.
	 * This callback is triggered when the map is ready to be used.
	 * This is where we can add markers or lines, add listeners or move the camera. In this case,
	 * we just add a marker near Sydney, Australia.
	 * If Google Play services is not installed on the device, the user will be prompted to install
	 * it inside the SupportMapFragment. This method will only be triggered once the user has
	 * installed Google Play services and returned to the app.
	 */
	@Override
	public void onMapReady(GoogleMap googleMap) {
		Log.i("onMapReady", "CALLING ON MAP READY");
		mMap = googleMap;
		mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
		gpsTracker = new GpsTracker(this);

		if (gpsTracker.canGetLocation) {
			latitude = gpsTracker.getLatitude();
			longitude = gpsTracker.getLongitude();
		} else {
			gpsTracker.showSettingsAlert();
		}

		enableMyLocation();
		mMap.setOnMyLocationButtonClickListener(this);
		mMap.setOnMyLocationClickListener(this);

		LatLng loc = new LatLng(latitude, longitude);
		mMap.addMarker(new MarkerOptions()
				.position(loc)
				.title("Location")
				.draggable(true)
				.snippet("Set Location here")
				.icon(BitmapDescriptorFactory
						.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));
		mMap.moveCamera(CameraUpdateFactory.newLatLng(loc));

		markerLoc = loc;

//		mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
//			@Override
//			public void onMarkerDragStart(Marker marker) {
//				// drag start
//			}
//			@Override
//			public void onMarkerDrag(Marker marker) {
//				// drag
//			}
//			@Override
//			public void onMarkerDragEnd(Marker marker) {
//				// drag end
//				markerLoc = new LatLng(marker.getPosition().latitude, marker.getPosition().longitude);
//
//			}
//		});

		mMap.setOnCameraMoveStartedListener(new GoogleMap.OnCameraMoveStartedListener() {
			@Override
			public void onCameraMoveStarted(int i) {
//				mDragTimer.start();
//				mTimerIsRunning = true;
				Log.d("Map", "user is moving map");
			}
		});

		mMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
			@Override
			public void onCameraIdle() {
				// Cleaning all the markers.
				if (mMap != null) {
					mMap.clear();
				}

				markerLoc = mMap.getCameraPosition().target;

				mMap.addMarker(new MarkerOptions()
						.position(markerLoc)
						.title("Location")
						.draggable(true)
						.snippet("Set Location here")
						.icon(BitmapDescriptorFactory
								.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));

			}
		});
	}


	@Override
	public boolean onMyLocationButtonClick() {
		Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
		return false;
	}

	@Override
	public void onMyLocationClick(Location location) {
		Toast.makeText(this, "Current location:\n" + location, Toast.LENGTH_LONG).show();
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
			return;
		}
		if (PermissionUtils.isPermissionGranted(permissions, grantResults, Manifest.permission.ACCESS_FINE_LOCATION)) {
			// Enable the my location layer if the permission has been granted.
			enableMyLocation();
		} else {
			// Permission was denied. Display an error message about
			// the missing permission error dialog when the fragments resume.
			permissionDenied = true;
		}
	}

	private void enableMyLocation() {
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
				== PackageManager.PERMISSION_GRANTED) {
			if (mMap != null) {
				mMap.setMyLocationEnabled(true);
				boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
				boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
				if (!isGPSEnabled && !isNetworkEnabled) {
					Toast.makeText(this, "failed", Toast.LENGTH_LONG).show();
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
			PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
					Manifest.permission.ACCESS_FINE_LOCATION, true);
		}
	}
}
