package com.example.user.a61517;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.opengl.GLSurfaceView;

import java.util.ArrayList;

import static android.content.Context.SENSOR_SERVICE;

/**
 * Created by user on 6/15/2017.
 */

public class MyGLSurfaceView extends GLSurfaceView implements SensorEventListener {
    ArrayList<acPacket> history = new ArrayList<acPacket>();
    SensorManager mySensorManager;
    Sensor mySensor;
    float netX,netY,netZ,oX,oY,oZ=0;
    private final MyGLRenderer mRenderer;
    public MyGLSurfaceView(Context context) {
        super(context);
        mySensorManager = (SensorManager) context.getSystemService(SENSOR_SERVICE);
        mySensor = mySensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mySensorManager.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_FASTEST);
        mRenderer = new MyGLRenderer();
        setRenderer(mRenderer);

        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);

    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        history.add(0,new acPacket(event.values[0],event.values[1],event.values[2]));
        if(history.size()>50) {
            history.remove(50);
        }

        netX = xfix()/5;
        netY = -yfix()/5;
        netZ = zfix()/5;
        mRenderer.setMovement(netX,netY,netZ);
        requestRender();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    public float xfix() {
        double sum = 0;
        for(int i = 0; i<history.size(); i++) {
            sum = sum+ history.get(i).getX();
        }
        return (float)sum/50;
    }
    public float yfix() {
        double sum = 0;
        for(int i = 0; i<history.size(); i++) {
            sum = sum+ history.get(i).getY();
        }
        return (float)sum/50;
    }
    public float zfix() {
        double sum = 0;
        for(int i = 0; i<history.size(); i++) {
            sum = sum+ history.get(i).getZ();
        }
        return (float)sum/50;
    }
}
