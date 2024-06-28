package com.example.madcamp01.second

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.madcamp01.databinding.ActivityImageFullscreenBinding

class ImageFullscreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityImageFullscreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageFullscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imageUri: Uri? = intent.getParcelableExtra("imageUri")
        binding.fullscreenImageView.setImageURI(imageUri)
    }
}
