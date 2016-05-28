package finalproj.com.corun.Fragments;

//import android.app.Fragment;
//import android.app.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import finalproj.com.corun.Activities.MainActivity;
import finalproj.com.corun.Activities.PickDate;
import finalproj.com.corun.Activities.PickTiming;
import finalproj.com.corun.Activities.RunEventCard;
import finalproj.com.corun.Class.DateTime;
import finalproj.com.corun.Class.Manager;
import finalproj.com.corun.Class.RunEvent;
import finalproj.com.corun.Class.Runner;
import finalproj.com.corun.Class.PermissionUtils;
import finalproj.com.corun.R;
import finalproj.com.corun.Activities.RunnerCard;

public class MapFragment extends Fragment
        implements
        GoogleMap.OnMyLocationButtonClickListener,
        OnMapReadyCallback,
        ActivityCompat.OnRequestPermissionsResultCallback,
        GoogleMap.OnMarkerClickListener, View.OnClickListener {


    private static final int REQ_CODE_PICK_TIME = 2;
    private static final int REQ_CODE_PICK_DATE = 3;
    private static final int GPS_LOC = 1;
    private static boolean first_check = true;
    private GoogleMap mMap;
    private boolean mPermissionDenied = false;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    private Button pickTimeBtn = null;
    private Button pickDateBtn = null;
    public MapFragment(){};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        final View rootView = inflater.inflate(R.layout.activity_maps, container, false);
        SupportMapFragment mapFragment  = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

        DateTime dateTime = new DateTime();

        pickTimeBtn = (Button) rootView.findViewById(R.id.btn_time);
        pickTimeBtn.setText(dateTime.timeToString());
        pickTimeBtn.setOnClickListener(this);

        pickDateBtn = (Button) rootView.findViewById(R.id.btn_date);
        pickDateBtn.setText(dateTime.dateToString());
        pickDateBtn.setOnClickListener(this);


        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.FAB_new_run);
        fab.setOnClickListener(this);
        return rootView;
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when i map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMarkerClickListener(this);
        mMap.setPadding(0, 50, 0, 0);

        if (enableMyLocation()) {
            animateUserLocation();
        }
    }

    @Override
    public boolean onMyLocationButtonClick() {

        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
    }

    private boolean enableMyLocation() {

        checkPermissions();

        if (!mPermissionDenied && mMap != null) {
            // Access to the location has been granted to the app.
            mMap.setMyLocationEnabled(true);
            return true;
        }

        showMissingPermissionError();
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            return;
        }

        if (PermissionUtils.isPermissionGranted(permissions, grantResults,
                android.Manifest.permission.ACCESS_FINE_LOCATION)) {

            // Enable the my location layer if the permission has been granted.
            enableMyLocation();
        } else {
            // Display the missing permission error dialog when the fragments resume.
            mPermissionDenied = true;
        }
    }

//    @Override
//    protected void onResumeFragments() {
////        super.onResumeFragments();
//
//        if (mPermissionDenied) {
//            // Permission was not granted, display error dialog.
//            showMissingPermissionError();
//            mPermissionDenied = false;
//        }
//    }


    /**
     * Displays a dialog with error message explaining that the location permission is missing.
     */
    private void showMissingPermissionError() {
        PermissionUtils.PermissionDeniedDialog
                .newInstance(true).show(MainActivity.fragmentManager, "dialog");
    }


    //My functions
    private void checkPermissions() {

        if ((ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) &&
                (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {

//          Permission to access the location is missing.
            PermissionUtils.requestPermission((AppCompatActivity) getActivity(), LOCATION_PERMISSION_REQUEST_CODE,
                    android.Manifest.permission.ACCESS_FINE_LOCATION, true);
        }
    }

    private void animateUserLocation() {

        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        if (ActivityCompat.checkSelfPermission(getActivity(),
                android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getActivity(),
                        android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        Location location = locationManager.getLastKnownLocation(locationManager.PASSIVE_PROVIDER);

        if(location == null)
        {
            if (first_check) {
                Intent gpsOptionsIntent = new Intent(
                        android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(gpsOptionsIntent, GPS_LOC);
                return;
            }

            Toast.makeText(getActivity(), R.string.location_disable_alert, Toast.LENGTH_LONG).show();
            return;
        }

        initManager(location);

        // Animate the camera
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(location.getLatitude(), location.getLongitude()), 13));

        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(latLng)      // Sets the center of the map to location user
                .zoom(14)                   // Sets the zoom
                .bearing(90)                // Sets the orientation of the camera to east
                .tilt(10)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        // Build the coRunners around
        putMarkersOfCoRunnersAroundMe();
    }

    private void initManager(Location location)
    {
        //Set current user location
        Manager.getAppManger().getUserLogedin().setLocation(location);
        Manager.getAppManger().setCurrent_run(new RunEvent());
    }

    private void addEventMarkerToMap(RunEvent event){

        MarkerOptions mrkOpt = new MarkerOptions();
        mrkOpt.position(event.getLatLng()).title(String.valueOf(event.getDistance() + "KM"));

        BitmapDescriptor descriptor = BitmapDescriptorFactory.fromResource(R.drawable.run_map);

        mrkOpt.icon(descriptor);
        Marker newMarker = mMap.addMarker(mrkOpt);

        Manager.getAppManger().addMarkerToHashMap(newMarker, event);
    }

    private void putMarkersOfCoRunnersAroundMe() {

        Manager.getAppManger().clearAllMarkers();
        mMap.clear();

        if(Manager.getAppManger().getUserLogedin().getLocation() != null) {
            MarkerOptions mrkOpt = new MarkerOptions();

            String name = Manager.getAppManger().getUserLogedin().full_name;
            LatLng latLng = new LatLng(Manager.getAppManger().getUserLogedin().getLocation().getLatitude(),
                    Manager.getAppManger().getUserLogedin().getLocation().getLongitude());
            mrkOpt.position(latLng).title(name);
            Marker newMarker = mMap.addMarker(mrkOpt);

            ArrayList<RunEvent> events = Manager.getAppManger().getEvents_around_me();

            // Build the runner table
            for (RunEvent event : events) {
//            TODO

                for (Runner runner : event.getParticipates()) {
                    addRunnerPic(runner);
                }

                addEventMarkerToMap(event);
            }
        }
    }

    private void addRunnerPic(Runner runner) {

        String pic = "";

        if (runner.getUser().full_name.compareTo("Reut") == 0)
        {
            pic = "https://fbcdn-sphotos-b-a.akamaihd.net/hphotos-ak-xta1/v/t1.0-9/12234940_10211458431803460_2785885696829654157_n.jpg?oh=a2365dcc53274f1f9c16df8938e31be9&oe=577A1A32&__gda__=1468140415_cd9a2b95cc7e946fa8fc68d64ecac891";

        }

        if (runner.getUser().full_name.compareTo("Katia") == 0)
        {
            pic = "https://fbcdn-sphotos-h-a.akamaihd.net/hphotos-ak-xta1/v/t1.0-9/12074660_10204546258286689_1743327630608031174_n.jpg?oh=3bf0e47294a533caf0d6a22d1b661580&oe=57BFAC3E&__gda__=1467897715_62d9830871139ab5167edf1edc9cbd3d";

        }

        if (runner.getUser().full_name.compareTo("Mor") == 0)
        {
            pic = "https://fbcdn-sphotos-a-a.akamaihd.net/hphotos-ak-xtl1/v/t1.0-9/11232171_10153222841554504_1582881390191112145_n.jpg?oh=547e363fa2deebb8701dde4075060e33&oe=578C6ABA&__gda__=1469139931_0cf64fca27daee8c273c6470ac66383d";
        }
        if (runner.getUser().full_name.compareTo("Yaron") == 0)
        {
            pic = "https://scontent.xx.fbcdn.net/hphotos-xft1/v/t1.0-9/1455960_10152028098311764_1603614269_n.jpg?oh=b206f30cc73fa5f42df8ebb26edaba74&oe=5790BC3C";

        }

        if (runner.getUser().full_name.compareTo("Dor") == 0)
        {
            pic = "https://fbcdn-sphotos-h-a.akamaihd.net/hphotos-ak-xpt1/v/t1.0-9/12348109_10153755012119197_6344607733530107460_n.jpg?oh=985614f562f7c68b0c92d50a9e75c5c7&oe=57BF741E&__gda__=1467523863_6ab059f2823b0b23f38da3af8825b3c2";
        }

        runner.getUser().setPic_url(pic);
    }


    @Override
    public boolean onMarkerClick(Marker marker) {

        Intent intent = new Intent(getActivity(), RunEventCard.class);
        intent.putExtra("Marker",marker.getId());
        startActivity(intent);
//
        return false;
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        switch (v.getId())
        {
            case R.id.btn_time: {
                Intent time = new Intent(getActivity(), PickTiming.class);
                getActivity().startActivityFromFragment(this, time, REQ_CODE_PICK_TIME);
                break;
            }
            case R.id.btn_date: {
                Intent date = new Intent(getActivity(), PickDate.class);
                getActivity().startActivityFromFragment(this, date, REQ_CODE_PICK_DATE);
                break;
            }
            case R.id.FAB_new_run:{

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                alertDialogBuilder.setMessage("New run will be created in \n " +
                                                Manager.getAppManger().getCurrent_run().getRun_time().dateToString() + " " +
                                                Manager.getAppManger().getCurrent_run().getRun_time().timeToString());

                alertDialogBuilder.setIcon(R.drawable.new_run);
                alertDialogBuilder.setTitle(R.string.create_new_run);
                alertDialogBuilder.setPositiveButton("Create Run", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(MainActivity.this, , Toast.LENGTH_LONG);
                        Snackbar.make(getView(), R.string.new_run_ok_dialog , Snackbar.LENGTH_LONG).show();
                        Manager.getAppManger().addNewRun(Manager.getAppManger().getCurrent_run());
                    }
                });
                alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode){
            case REQ_CODE_PICK_TIME:
               if((data != null) &&
                       (resultCode == MainActivity.RESULT_OK)) {

                   String t = data.getStringExtra("TIME");

                   if (t != null)
                   {
                        DateTime.DAY_TIMES time = DateTime.DAY_TIMES.valueOf(t);
                        Manager.getAppManger().getCurrent_run().setRun_time(time);
                        pickTimeBtn.setText(Manager.getAppManger().getCurrent_run().getRun_time().timeToString());
                        putMarkersOfCoRunnersAroundMe();
                   }
                }
                break;
            case REQ_CODE_PICK_DATE:
                if (resultCode == MainActivity.RESULT_OK) {
                    pickDateBtn.setText(Manager.getAppManger().getCurrent_run().getRun_time().dateToString());
                    putMarkersOfCoRunnersAroundMe();
                }
                break;
            case GPS_LOC:

                first_check = false;
                if (enableMyLocation()) {
                    animateUserLocation();
                }
                break;
        }
    }

}