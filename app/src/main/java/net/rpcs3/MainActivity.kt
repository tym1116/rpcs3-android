package net.rpcs3

import android.content.Intent
import android.opengl.GLSurfaceView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import net.rpcs3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var openGl: GLSurfaceView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        openGl = glView(this)
        // setContentView(openGl) Set instead of activity or SurfaceView
        setContentView(binding.root)
        binding.fabLogs.setOnClickListener {
            val intent = Intent(this, LogsActivity::class.java)
            startActivity(intent)
        }
        // Example of a call to a native method
        // binding.sampleText.text = stringFromJNI()
    }

    /**
     * A native method that is implemented by the 'rpcs3' native library,
     * which is packaged with this application.
     */
    // external fun stringFromJNI(): String

    companion object {
        // Used to load the 'rpcs3-android' library on application startup.
        init {
            System.loadLibrary("rpcs3-android")
        }
    }
}
