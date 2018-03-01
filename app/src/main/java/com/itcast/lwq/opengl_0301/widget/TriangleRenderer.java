package com.itcast.lwq.opengl_0301.widget;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by liuwq4 on 2018/3/1.
 */

public class TriangleRenderer implements GLSurfaceView.Renderer {
    private Triangle mTriangle;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        // 设置背景色
        GLES20.glClearColor(0, 0, 1, 1);
        mTriangle = new Triangle();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        // 设置surface的位置和尺寸
        GLES20.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        // 重绘背景色
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        mTriangle.draw();
    }
}
