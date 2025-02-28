package net.rpcs3

import android.opengl.GLES20
import android.opengl.GLES30
import android.opengl.GLSurfaceView
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class glRenderer : GLSurfaceView.Renderer {
    override fun onSurfaceCreated(p0: GL10?, p1: EGLConfig?) {
        GLES30.glClearColor(0.0f,0.0f,0.0f,1.0f);
    }

    override fun onSurfaceChanged(p0: GL10?, w: Int, h: Int) {
        GLES20.glViewport(0, 0, w, h)
    }

    override fun onDrawFrame(p0: GL10?) {
        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT);
    }
}
