package com.example.mapper;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView.Renderer;
 
public class MyRender implements Renderer{
 
   // Constructor
   public MyRender(Context context) {
	   
   }


   @Override
   public void onSurfaceCreated(GL10 gl, EGLConfig config) {
	   
   }
   
   @Override
   public void onSurfaceChanged(GL10 gl, int width, int height) {
      
   }


   public void onDrawFrame(GL10 gl) {
      GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
      
   }
}
 
