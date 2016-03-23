package com.td.faisal.sensor.readingthecordinate;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SensorManager sensorManager = null;
    private TextView tv;
    private List list;

    private SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            float[] cood = sensorEvent.values;
            tv.setText("x:" + cood[0] + "\ny:" + cood[1] + "\nz:" + cood[2]);

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {
            /**
             * Do nothing
             */
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewReference();
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        list = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if (list.size() > 0) {
            sensorManager.registerListener(sensorEventListener, (Sensor) list.get(0), SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            Toast.makeText(getBaseContext(), "No ACCELEROMETER Found !!", Toast.LENGTH_SHORT).show();
        }

    }

    private void viewReference() {
        tv = (TextView) findViewById(R.id.tv_cood);
    }

    @Override
    protected void onStop() {
        super.onStop();
        sensorManager.unregisterListener(sensorEventListener);
    }
}
