package com.example.myapplication;

import android.Manifest;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {
    SensorManager sensorManager;
    Sensor sensor1, sensor2;

    EditText textLight, textGyro;
    Button GPSButton;

    private SensorEventListener SensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            Sensor sensor = event.sensor;
            if (sensor.getType() == Sensor.TYPE_LIGHT) {
                textLight.setText("Intensidade da luz: " + event.values[0]);
            } else if (sensor.getType() == Sensor.TYPE_GYROSCOPE) {
                textGyro.setText("Rotação Eixo X: " + event.values[0] + " Eixo Y: " + event.values[1] + " Eixo Z: " + event.values[2]);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensor1 = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensor2 = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textLight = findViewById(R.id.textInputLayout);
        textGyro = findViewById(R.id.textInputLayout2);
        GPSButton = findViewById(R.id.button);

        if (sensor1 != null) {
            sensorManager.registerListener(SensorListener , sensor1, SensorManager.SENSOR_DELAY_NORMAL );
        } else textLight.setText("Light sensor not supported");


        if(sensor2 != null) {
            sensorManager.registerListener(SensorListener , sensor2, SensorManager.SENSOR_DELAY_NORMAL );
        } else textGyro.setText("Sensor not supported");


        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123);
        GPSButton.setOnClickListener(v -> {
            GPSTracker gpsTracker = new GPSTracker(getApplicationContext());
            Location location = gpsTracker.getLocation();
            if (location != null) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                Toast.makeText(getApplicationContext(), "Latitude: " + latitude + "\nLongitude: " +
                        longitude, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(SensorListener, sensor1, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(SensorListener);
    }
}