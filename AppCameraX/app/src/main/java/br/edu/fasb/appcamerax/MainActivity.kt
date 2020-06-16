package br.edu.fasb.appcamerax

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Matrix
import android.os.Bundle
import android.util.Size
import android.view.FocusFinder
import android.view.Surface
import android.view.TextureView
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraX
import androidx.camera.core.Preview
import androidx.camera.core.PreviewConfig
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.concurrent.Executors


private const val REQUEST_CODE_PERMISSION = 10
private val REQUERED_PERMISSION = arrayOf(android.Manifest.permission.CAMERA)

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewFinder = findViewById(R.id.viewImage)

        if (allPerssinionGranted()){
            viewFinder.post{ startCamera() }
        } else {
            ActivityCompat.requestPermissions(this, REQUERED_PERMISSION, REQUEST_CODE_PERMISSION)
        }

        viewFinder.addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ -> updateTransform() }
    }

    private val executor = Executors.newSingleThreadExecutor()
    private lateinit var viewFinder: TextureView

    private fun startCamera() {

        val previewConfig = PreviewConfig.Builder().apply {
            setTargetResolution(Size(640, 480))
        }.build()

        val preview = Preview(previewConfig)

        preview.setOnPreviewOutputUpdateListener{
            val parent = viewFinder.parent as ViewGroup
            parent.removeView(viewFinder);
            parent.addView(viewFinder, 0);

            viewFinder.surfaceTexture = it.surfaceTexture

            updateTransform()
        }
        CameraX.bindToLifecycle(this, preview)
    }
    private fun updateTransform() {

        val matrix = Matrix()

        val centerX = viewFinder.width /2f
        val centerY = viewFinder.height /2f

        val rotationDegrees = when(viewFinder.display.rotation) {
            Surface.ROTATION_0 -> 0
            Surface.ROTATION_90 -> 90
            Surface.ROTATION_180 -> 180
            Surface.ROTATION_270 -> 270
            else -> return
        }

        matrix.postRotate(-rotationDegrees.toFloat(), centerX, centerY)

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (allPerssinionGranted()){
                viewFinder.post{ startCamera() }
            } else {
                Toast.makeText(this, "Permissão negada p/camera", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun allPerssinionGranted() = REQUERED_PERMISSION.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }
}
