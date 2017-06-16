package com.example.user.a61517;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by user on 6/15/2017.
 */

public class MyGLRenderer implements GLSurfaceView.Renderer {
    private Square mSquare;
    private calibrationButton cal;
    float x,y,z;
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClearColor(0,0,0,1);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        cal = new calibrationButton();
        mSquare = new Square();


    }
    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

        GLU.gluLookAt(gl,0,0,-3-getzMovement(),0f,0f,0f,0f,1f,0f);
        gl.glTranslatef(getxMovement(),getyMovement(),0.0f);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        mSquare.draw(gl);
        //cal.draw(gl);


    }
    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

        gl.glViewport(0,0,width,height);

        float ratio = (float)width/height;
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glFrustumf(-ratio,ratio,-1,1,1,7);

    }
    public float getxMovement() {
        return x;
    }
    public float getyMovement() {
        return y;
    }
    public float getzMovement() {
        return z;
    }
    public void setMovement(float ax, float ay, float az) {
        x=ax;
        y=ay;
        z=az;
    }
}
