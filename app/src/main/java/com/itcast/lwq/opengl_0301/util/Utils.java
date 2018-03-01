package com.itcast.lwq.opengl_0301.util;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;

/**
 * Created by liuwq4 on 2018/3/1.
 */

public class Utils {

    /**
     * 将float[]数组加载到内存中
     *
     * @param bufferArray
     * @return
     */
    public static FloatBuffer getFloatBuffer(float[] bufferArray) {
        FloatBuffer fbf = null;
        ByteBuffer bbf = ByteBuffer.allocateDirect(bufferArray.length * 4);
        bbf.order(ByteOrder.nativeOrder());
        fbf = bbf.asFloatBuffer();
        fbf.put(bufferArray);
        fbf.position(0);
        return fbf;
    }

    /**
     * 生成shader
     *
     * @param shaderType shader类型
     * @param shaderCode shader代码
     * @return
     */
    public static int loadShader(int shaderType, String shaderCode) {
        // 1、根据shaderType创建shader模板
        int shader = GLES20.glCreateShader(shaderType);
        // 2、根据code,实例化shader
        GLES20.glShaderSource(shader, shaderCode);
        // 3、编译该shader
        GLES20.glCompileShader(shader);

        return shader;
    }

    /**
     * 生成program
     *
     * @param shaderList shader集合
     * @return
     */
    public static int loadProgram(ArrayList<Integer> shaderList) {
        int program = GLES20.glCreateProgram();
        for (int shader : shaderList) {
            GLES20.glAttachShader(program, shader);
        }
        return program;
    }
}
