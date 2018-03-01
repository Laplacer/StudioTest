package com.itcast.lwq.opengl_0301.widget;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

/**
 * Created by liuwq4 on 2018/3/1.
 */

public class TriangleSurface extends GLSurfaceView {
    public TriangleSurface(Context context) {
        this(context, null);
    }

    public TriangleSurface(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 1. 设置egl的环境参数
        setEGLContextClientVersion(2);

        // 2. 设置用于绘图的render
        TriangleRenderer renderer = new TriangleRenderer();
        setRenderer(renderer);

        // 3. 设置刷新模式-当有新的texture到达时
        setRenderMode(RENDERMODE_WHEN_DIRTY);
    }
}
