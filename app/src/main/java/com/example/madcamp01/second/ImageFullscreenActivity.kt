package com.example.madcamp01.second

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.madcamp01.databinding.ActivityImageFullscreenBinding

class ImageFullscreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityImageFullscreenBinding
    private lateinit var imageList: List<Uri>
    private var initialPosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        binding = ActivityImageFullscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        imageList = intent.getParcelableArrayListExtra("imageList") ?: emptyList()
        initialPosition = intent.getIntExtra("initialPosition", 0)

        val adapter = FullscreenImageAdapter(imageList)
        binding.viewPager.adapter = adapter
        binding.viewPager.setCurrentItem(initialPosition, false)
    }
}
