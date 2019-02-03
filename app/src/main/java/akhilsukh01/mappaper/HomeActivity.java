package akhilsukh01.mappaper;

import android.Manifest;
import android.app.WallpaperManager;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

import static android.content.ContentValues.TAG;

public class HomeActivity extends FragmentActivity  implements OnMapReadyCallback{

    private GoogleMap mMap;
    private SupportMapFragment mapFragmentView;
    private TextView header, footer;
    private Spinner mSpinner;
    public TextView titleView;
    public Button saveB, saveW;
    public Spinner locSpinner;

    private static final String TC = "ThemeChanger";
    LocationTrack locationTrack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        header = findViewById(R.id.header);
        footer = findViewById(R.id.footer);

        mSpinner = findViewById(R.id.spinner1);
        titleView =  findViewById(R.id.titleView);
        saveB =  findViewById(R.id.saveButton);
        saveW =  findViewById(R.id.saveWallpaper);
        locSpinner =  findViewById(R.id.locSpinner);

        mapFragmentView = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapView);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        //test change for bash commit


//        //get dimensions of screen
//        DisplayMetrics displayMetrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//        final int screenHeight = displayMetrics.heightPixels;
//        final int screenWidth = displayMetrics.widthPixels;
//        String screenDim = String.valueOf(screenHeight)  + "," + String.valueOf(screenWidth);
////        header.setText(screenDim);
        mapFragmentView.getMapAsync(this);
        //
//
//        //get dimensions of fragment and change width of map to match that of screen
//        mapFragmentView.getView().measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
//        final View mapView = mapFragmentView.getView();
//        mapView.post(new Runnable() {
//            @Override
//            public void run() {
//                int mapWidth = mapView.getMeasuredWidth();
//                int mapHeight = mapView.getMeasuredHeight();
//
//                String widthAndHeight = mapHeight + "," + mapWidth;
//                footer.setText(widthAndHeight);
//
//                Double ratio = (Double.valueOf(mapHeight)/Double.valueOf(screenHeight));
//                int newWidth = (int) (ratio*screenWidth);
//
//                ViewGroup.LayoutParams params = mapFragmentView.getView().getLayoutParams();
//                params.width = newWidth;
//                mapFragmentView.getView().setLayoutParams(params);
//
//                LatLng center = mMap.getCameraPosition().target;
//                header.setText(center.toString());
//            }
//        });


//        List<String> mList = new ArrayList<>();
//        mList.add("Vintage");
//        mList.add("Creed");
//        mList.add("Aqua");
//        mList.add("Pastel");
//        mList.add("Adrenaline");
//        mList.add("Asphalt");
//        mList.add("Aubergine");

//        mList.add("  Graphite");
//        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, mList);
//        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        mSpinner.setAdapter(myAdapter);


        Typeface Light = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Light.ttf");
        titleView.setTypeface(Light);
        Typeface buttonFont = Typeface.createFromAsset(getAssets(), "fonts/sf_pro_display_medium.ttf");
        saveB.setTypeface(buttonFont);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.testArray, R.layout.spinner_item_intro);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        mSpinner.setAdapter(adapter);

        ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this, R.array.locArray, R.layout.spinner_item_intro);
        adapter2.setDropDownViewResource(R.layout.spinner_dropdown_item);
        locSpinner.setAdapter(adapter2);

        //backup now
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    GoogleMap gMap = mMap;
                    gMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(Objects.requireNonNull(mapFragmentView.getContext()), R.raw.style_json_stock));
                    Log.i(TC, "themeStock set");
                } else if (i == 1) {
                    GoogleMap gMap = mMap;
                    gMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(Objects.requireNonNull(mapFragmentView.getContext()), R.raw.style_json_rust));
                    Log.i(TC, "themeRust set");
                } else if (i == 2) {
                    GoogleMap gMap = mMap;
                    gMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(Objects.requireNonNull(mapFragmentView.getContext()), R.raw.style_json_creed));
                    Log.i(TC, "themeCreed set");
                }else if (i == 3) {
                    GoogleMap gMap = mMap;
                    gMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(Objects.requireNonNull(mapFragmentView.getContext()), R.raw.style_json_aqua));
                    Log.i(TC, "themeAqua set");
                }else if (i == 4) {
                    GoogleMap gMap = mMap;
                    gMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(Objects.requireNonNull(mapFragmentView.getContext()), R.raw.style_json_aquamarine));
                    Log.i(TC, "themeAquamarine set");
                }else if (i == 5) {
                    GoogleMap gMap = mMap;
                    gMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(Objects.requireNonNull(mapFragmentView.getContext()), R.raw.style_json_adrenaline));
                    Log.i(TC, "themeAdrenaline set");
                }else if (i == 6) {
                    GoogleMap gMap = mMap;
                    gMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(Objects.requireNonNull(mapFragmentView.getContext()), R.raw.style_json_asphalt));
                    Log.i(TC, "themeAsphalt set");
                }else if (i == 7) {
                    GoogleMap gMap = mMap;
                    gMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(Objects.requireNonNull(mapFragmentView.getContext()), R.raw.style_json_aubergine));
                    Log.i(TC, "themeAubergine set");
                }else if (i == 8) {
                    GoogleMap gMap = mMap;
                    gMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(Objects.requireNonNull(mapFragmentView.getContext()), R.raw.style_json_hay));
                    Log.i(TC, "themeHay set");
                }else if (i == 9) {
                    GoogleMap gMap = mMap;
                    gMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(Objects.requireNonNull(mapFragmentView.getContext()), R.raw.style_json_clementine));
                    Log.i(TC, "themeClementine set");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        //SAVE BUTTON--
        saveB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TC, "SAVED");
                isReadStoragePermissionGranted();
                isWriteStoragePermissionGranted();
                saveToCard();
            }
        });

        saveW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.i(TC, "SAVED");
//                isReadStoragePermissionGranted();
//                isWriteStoragePermissionGranted();
                isWallpaperStoragePermissionGranted();
                saveToWallpaper();
            }
        });
    }




    public boolean isReadStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG, "Permission is granted1");
                return true;
            } else {

                Log.v(TAG, "Permission is revoked1");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 3);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG, "Permission is granted1");
            return true;
        }
    }

    public boolean isWriteStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG, "Permission is granted2");
                return true;
            } else {

                Log.v(TAG, "Permission is revoked2");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG, "Permission is granted2");
            return true;
        }
    }

    public boolean isWallpaperStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.SET_WALLPAPER)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG, "Permission is granted1");
                return true;
            } else {

                Log.v(TAG, "Permission is revoked1");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SET_WALLPAPER}, 3);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG, "Permission is granted1");
            return true;
        }
    }


    public void saveToCard()
    {
        GoogleMap.SnapshotReadyCallback callback = new GoogleMap.SnapshotReadyCallback()
        {

            @Override
            public void onSnapshotReady(Bitmap snapshot)
            {


                String folder_main = "MapPaper";
                //name folder
                File fp = new File(Environment.getExternalStorageDirectory(), folder_main);
                if (!fp.exists()) {
                    fp.mkdirs();
                }




                // TODO Auto-generated method stub
                Bitmap bitmap = snapshot;
                String filePath = String.valueOf(System.currentTimeMillis());

                //Creating name of picture saved to card
                fp.mkdirs();
                String picDirName = (filePath +".jpg");


                //saving pic to card
                File file = new File(fp, picDirName);
                if (file.exists()) file.delete ();
                try {
                    FileOutputStream out = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
                    out.flush();
                    out.close();
                    Toast.makeText(getApplicationContext(),filePath + " saved to " + fp,Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        mMap.snapshot(callback);
    }

    public void saveToWallpaper()
    {
        GoogleMap.SnapshotReadyCallback callback = new GoogleMap.SnapshotReadyCallback()
        {

            @Override
            public void onSnapshotReady(Bitmap snapshot)
            {

                // TODO Auto-generated method stub
//                Bitmap bitmapW = snapshot;
//                String filePath = System.currentTimeMillis() + ".jpeg";

                WallpaperManager wallManager = WallpaperManager.getInstance(getApplicationContext());
                try {
                    wallManager.setBitmap(snapshot);
                    Toast.makeText(HomeActivity.this, "Wallpaper Set Successfully!!", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Toast.makeText(HomeActivity.this, "Setting WallPaper Failed!!", Toast.LENGTH_SHORT).show();
                }
            }
        };

        mMap.snapshot(callback);
    }

//    public void openShareImageDialog(String filePath)
//    {
//        File file = this.getFileStreamPath(filePath);
//
//        if(!filePath.equals(""))
//        {
//            final ContentValues values = new ContentValues(2);
//            values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
//            values.put(MediaStore.Images.Media.DATA, file.getAbsolutePath());
//            final Uri contentUriFile = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
//
//            final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
//            intent.setType("image/jpeg");
//            intent.putExtra(android.content.Intent.EXTRA_STREAM, contentUriFile);
//            startActivity(Intent.createChooser(intent, "Share Image"));
//        }
//        else
//        {
//            //This is a custom class I use to show dialogs...simply replace this with whatever you want to show an error message, Toast, etc.
//            Log.i(TC, "Error Saving");
//        }
//    }

    //theming below---------------------------------------------
    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setMapToolbarEnabled(false);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);

        try {
            boolean success = googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.style_json_rust));

            if (!success) {
                Log.e(TC, "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e(TC, "Can't find style. Error: ", e);
        }


//        LatLng center = mMap.getCameraPosition().target;

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(48.451, -123.285)));

        locSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(42.3334, -71.0495)));
                    Log.i(TC, "South Boston set");
                } else if (i == 1) {
                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(41.3823, 2.1854)));
                    Log.i(TC, "Barcelona set");
                }else if (i == 2) {
                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(44.4358, 26.0165 )));
                    Log.i(TC, "Sector 6 set");
                }else if (i == 3) {
                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(43.7001 , -79.4163 )));
                    Log.i(TC, "Toronto set");
                }else if (i == 4) {
                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(29.7633 , -95.3633)));
                    Log.i(TC, "Houston set");
                }else if (i == 5) {
                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(44.9800 , -93.2638 )));
                    Log.i(TC, "Minneapolis set");
                }else if (i == 6) {
                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(44.8404 , -0.5805)));
                    Log.i(TC, "Bordeaux set");
                }else if (i == 7) {
                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(25.7743 , -80.1937)));
                    Log.i(TC, "Miami set");
                }else if (i == 8) {
                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(45.7485, 4.8467 )));
                    Log.i(TC, "Lyon set");
                }else if (i == 9) {
                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(41.4875 , 2.1854)));
                    Log.i(TC, "Badalona set");
                }else if (i == 10) {
                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(45.4643 , 9.1895 )));
                    Log.i(TC, "Milan set");
                }else if (i == 11) {
                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(32.6401 , -117.0842 )));
                    Log.i(TC, "Chula Vista set");
                }else if (i == 12) {
                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(37.9794 , 23.7162 )));
                    Log.i(TC, "Athens set");
                }else if (i == 13) {
                    //Get location
                    if (ActivityCompat.checkSelfPermission(HomeActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(HomeActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(HomeActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                        ActivityCompat.requestPermissions(HomeActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);

                        return;
                    }
                    else{
//                        LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
//                        assert lm != null;
//                        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//                        uLng = location.getLongitude();
//                        uLat = location.getLatitude()
                            locationTrack = new LocationTrack(HomeActivity.this);
                            if (locationTrack.canGetLocation()) {
                                double uLng = locationTrack.getLongitude();
                                double uLat = locationTrack.getLatitude();
                                googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(uLat , uLng)));
                                Toast.makeText(getApplicationContext(), "Longitude:" + Double.toString(uLng) + "\nLatitude:" + Double.toString(uLat), Toast.LENGTH_SHORT).show();
                            } else {
                                locationTrack.showSettingsAlert();
                                Toast.makeText(getApplicationContext(), "Try Again", Toast.LENGTH_SHORT).show();
                            }
                    }

                    locationTrack = new LocationTrack(HomeActivity.this);
                    if (locationTrack.canGetLocation()) {
                        double uLng = locationTrack.getLongitude();
                        double uLat = locationTrack.getLatitude();
                        googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(uLat, uLng)));
                        Toast.makeText(getApplicationContext(), "Longitude:" + Double.toString(uLng) + "\nLatitude:" + Double.toString(uLat), Toast.LENGTH_SHORT).show();

//                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(uLat , uLng)));
//                    Log.i(TC, "You Location set");
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                    //Get location
                    if (ActivityCompat.checkSelfPermission(HomeActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(HomeActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(HomeActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                        ActivityCompat.requestPermissions(HomeActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);

                        return;
                    }else{
//                        LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
//                        assert lm != null;
//                        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//                        uLng = location.getLongitude();
//                        uLat = location.getLatitude()
                        locationTrack = new LocationTrack(HomeActivity.this);
                        if (locationTrack.canGetLocation()) {
                            double uLng = locationTrack.getLongitude();
                            double uLat = locationTrack.getLatitude();
                            googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(uLat , uLng)));
                            Toast.makeText(getApplicationContext(), "Longitude:" + Double.toString(uLng) + "\nLatitude:" + Double.toString(uLat), Toast.LENGTH_SHORT).show();
                        } else {
                            locationTrack.showSettingsAlert();
                            Toast.makeText(getApplicationContext(), "Try Again", Toast.LENGTH_SHORT).show();
                        }
                    }

                    locationTrack = new LocationTrack(HomeActivity.this);
                    if (locationTrack.canGetLocation()) {
                        double uLng = locationTrack.getLongitude();
                        double uLat = locationTrack.getLatitude();
                        googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(uLat, uLng)));
                        Toast.makeText(getApplicationContext(), "Longitude:" + Double.toString(uLng) + "\nLatitude:" + Double.toString(uLat), Toast.LENGTH_SHORT).show();

//                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(uLat , uLng)));
//                    Log.i(TC, "You Location set");
                }
            }
        });

    }
}