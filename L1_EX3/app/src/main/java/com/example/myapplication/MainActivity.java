package com.example.myapplication;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    SensorManager sensorManager;
    Sensor accelerometer;

    EditText textX;
    EditText textY;
    EditText textZ;

    float sensorX = 0.0f;
    float sensorY = 0.0f;
    float sensorZ = 0.0f;

    String message = "";

    private SensorEventListener SensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {

            if(isDifferenceSignificant(sensorX,event.values[0], "X") ||
                    isDifferenceSignificant(sensorY,event.values[1], "Y") ||
                    isDifferenceSignificant(sensorZ,event.values[2], "Z")
            ) beginSecondActivity();


            float sensorX = event.values[0];
            float sensorY = event.values[1];
            float sensorZ = event.values[2];

            textX.setText(String.valueOf(sensorX));
            textY.setText(String.valueOf(sensorY));
            textZ.setText(String.valueOf(sensorZ));
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textX = findViewById(R.id.textInputLayout);
        textY = findViewById(R.id.textInputLayout2);
        textZ = findViewById(R.id.textInputLayout3);
    }

    public void beginSecondActivity() {
        Intent intent = new Intent(this, SecondaryActivity.class);
        intent.putExtra("message", message);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(SensorListener, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(SensorListener);
    }

    private boolean isDifferenceSignificant(float value1, float value2, String axis) {
        float diff = Math.abs(value1 - value2);
        boolean isSignificant = diff >= 12.0f;
        if(isSignificant) {
            message = "Movimento detectado no eixo " + axis + "! Diferença de " + diff;
        }
        return isSignificant;
    }
}