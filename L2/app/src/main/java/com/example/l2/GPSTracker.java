package com.example.l2;

import android.location.LocationListener;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

public class GPSTracker implements LocationListener {
    Context context;
    public GPSTracker(Context c)
    {
        context = c;
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }

    public Location getLocation(){
        if (ContextCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            Toast.makeText(context, "Permissão não oferecida" , Toast.LENGTH_LONG).show();
            return null ;
        }
        LocationManager locationManager = (LocationManager)
                context.getSystemService(Context.LOCATION_SERVICE);
        boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (isGPSEnabled)
        {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000 , 10 , this );
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            return location;
        } else
        {
            Toast.makeText(context, "Por favor, ligue o GPS" , Toast.LENGTH_LONG).show();
        }
        return null ;
    }
}
