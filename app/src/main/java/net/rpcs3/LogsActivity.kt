package net.rpcs3

import android.content.ClipData
import android.content.ClipboardManager
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.File
import java.io.IOException

class LogsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logs)

        val toolbar = findViewById<MaterialToolbar>(R.id.topAppBar)
        setSupportActionBar(toolbar)

        val logs = findViewById<TextView>(R.id.logs_text)
        loadLogs(logs)

        findViewById<FloatingActionButton>(R.id.fab_copy).setOnClickListener {
            copyLogs(logs.text.toString())
        }
    }

    private fun loadLogs(textView: TextView) {
        try {
            val logFile = File("/sdcard/RPCS3/rpcs3_logs.txt")
            if (logFile.exists()) {
                textView.text = logFile.readText()
            } else {
                textView.text = "No errors logged"
            }
        } catch (e: IOException) {
            textView.text = "Error reading logs: ${e.message}"
        }
    }

    private fun copyLogs(text: String) {
        val clipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("RPCS3 Logs", text)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(this, "Logs copied to clipboard", Toast.LENGTH_SHORT).show()
    }
}