package net.rpcs3

import android.content.Context
import android.opengl.GLSurfaceView

class glView(context: Context) : GLSurfaceView(context) {
    private val renderer: glRenderer
    init {
        // Создаем контекст для 3 версии
        setEGLContextClientVersion(3)

        renderer = glRenderer()

        // Ставим рендерер
        setRenderer(renderer)
    }
}