package com.example.flashlight_

import android.content.Context
import android.graphics.Camera
import android.graphics.Color
import android.hardware.camera2.CameraManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.flashlight_.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    var flashLight: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.power.setOnClickListener {
            flashLight = !flashLight
            if (flashLight){
                try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                        var camManager = this.getSystemService(Context.CAMERA_SERVICE) as CameraManager
                        camManager.setTorchMode(camManager.cameraIdList[0], true)
                    }else{
                        var camera = android.hardware.Camera.open()
                        var params = camera.parameters
                        params.flashMode = android.hardware.Camera.Parameters.FLASH_MODE_TORCH
                        camera.parameters = params
                        camera.startPreview()
                    }
                    binding.textDeveloper.setTextColor(Color.GREEN)
                    binding.textName.setTextColor(Color.GREEN)
                    binding.power.setImageResource(R.drawable.on)
                }catch (e: Exception){

                }




            }else{
                try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                        var camManager = this.getSystemService(Context.CAMERA_SERVICE) as CameraManager
                        camManager.setTorchMode(camManager.cameraIdList[0], false)
                    }else{
                        var camera = android.hardware.Camera.open()
                        var params = camera.parameters
                        params.flashMode = android.hardware.Camera.Parameters.FLASH_MODE_OFF
                        camera.parameters = params
                        camera.startPreview()
                    }
                    binding.textDeveloper.setTextColor(Color.RED)
                    binding.textName.setTextColor(Color.RED)
                    binding.power.setImageResource(R.drawable.off)
                }catch (e: Exception){

                }

            }
        }
    }
}