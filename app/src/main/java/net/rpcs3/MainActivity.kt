package net.rpcs3

import android.content.Intent
import android.net.Uri
import android.opengl.GLSurfaceView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import androidx.activity.result.contract.ActivityResultContracts
import net.rpcs3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var openGl: GLSurfaceView

    private val storagePermission = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (Environment.isExternalStorageManager()) {
            appInit()
        } else {
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!Environment.isExternalStorageManager()) {
            requestStoragePermission()
        } else {
            initializeApp()
        }
    }

    private fun requestStoragePermission() {
        val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION).apply {
            data = Uri.parse("package:$packageName")
        }
        storagePermission.launch(intent)
    }

    private fun appInit() {
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