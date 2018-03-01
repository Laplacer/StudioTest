package com.itcast.lwq.opengl_0301.widget;

import android.opengl.GLES20;

import com.itcast.lwq.opengl_0301.util.Utils;

import java.nio.FloatBuffer;
import java.util.ArrayList;

/**
 * 绘制三角形的步骤：
 * 1. 加载顶点着色器和片元着色器
 * 2. 确定需要绘制的三角形的位置和颜色
 * 3. 创建program对象，链接顶点和片元着色器
 * 4. 创建视图窗口 viewport（用于显示）
 * 5. 将位置和颜色加入OpenGLES中
 * 6. 将颜色缓冲区的数据显示在屏幕上
 */

public class Triangle {

    private FloatBuffer mVertexBuffer;
    private int mProgram;
    private int mPositionHandler;
    private int mColorHandler;

    // 每个顶点的坐标数
    private final int COORDS_PER_VERTEX = 3;
    // 顶点记录需要4 bit大小
    private final int VERTEX_STRIDE = COORDS_PER_VERTEX * 4;

    private final float[] VERTEX_ARRAY = {
            0f, 0.5f, 0f,
            -0.5f, -0.5f, 0f,
            0.5f, -0.5f, 0f
    };

    // 设置三角形的颜色和透明度
    private final float[] COLOE_ARRAY = {1, 1, 0, 1};

    // vertexShader代码
    private final String VERTEX_SHADER_CODE =
            "attribute vec4 vPosition;" +
                    "void main() {" +
                    "  gl_Position = vPosition;" +
                    "}";

    // fragmentShader代码
    private final String FRAGMENT_SHADER_CODE =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "  gl_FragColor = vColor;" +
                    "}";


    public Triangle() {
        // 1、加载顶点着色器和片元着色器
        mVertexBuffer = Utils.getFloatBuffer(VERTEX_ARRAY);

        // 2、获取shader
        int vertexShader = Utils.loadShader(GLES20.GL_VERTEX_SHADER, VERTEX_SHADER_CODE);
        int fragmentShader = Utils.loadShader(GLES20.GL_FRAGMENT_SHADER, FRAGMENT_SHADER_CODE);
        ArrayList<Integer> shaderList = new ArrayList<>();
        shaderList.add(vertexShader);
        shaderList.add(fragmentShader);

        // 3、生成program,将这些shaders加载到program中
        mProgram = Utils.loadProgram(shaderList);
        GLES20.glLinkProgram(mProgram);
    }

    public void draw() {
        // 1.使用program
        GLES20.glUseProgram(mProgram);
        // 2.获取指向vertex shader成员vPosition的handler
        mPositionHandler = GLES20.glGetAttribLocation(mProgram, "vPosition");
        // 3.根据vPosition,启用指向顶点数组的Handler
        GLES20.glEnableVertexAttribArray(mPositionHandler);
        // 4.准备三角形的坐标数据
        GLES20.glVertexAttribPointer(
                mPositionHandler, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                VERTEX_STRIDE, mVertexBuffer
        );

        // 5.获取指向frag shader的成员vColor的handler
        mColorHandler = GLES20.glGetUniformLocation(mProgram, "vColor");
        // 6.添加color
        GLES20.glUniform4fv(mColorHandler, 1, COLOE_ARRAY, 0);

        // 7.绘制三角形
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, VERTEX_ARRAY.length / COORDS_PER_VERTEX);

        // 8.关闭指向vPosition的handler
        GLES20.glDisableVertexAttribArray(mPositionHandler);
    }
}

