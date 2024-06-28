package com.example.madcamp01.second

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.madcamp01.R
import com.example.madcamp01.databinding.FragmentSecondBinding

class SecondFragment : Fragment(R.layout.fragment_second) {

    private lateinit var binding: FragmentSecondBinding
    private val imageList = mutableListOf<Uri>()
    private val REQUEST_CODE_READ_STORAGE = 100

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("SecondFragment", "onViewCreated called")
        binding = FragmentSecondBinding.bind(view)
        Log.d("SecondFragment", "Binding initialized")

        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {
            Log.d("SecondFragment", "Permission not granted, requesting permission")
            ActivityCompat.requestPermissions(requireActivity(),
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                REQUEST_CODE_READ_STORAGE
            )
        } else {
            Log.d("SecondFragment", "Permission granted, loading images")
            loadImagesFromGallery()
        }

        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.recyclerView.adapter = ImageAdapter(imageList)
        Log.d("SecondFragment", "RecyclerView setup complete")
    }

    private fun loadImagesFromGallery() {
        Log.d("SecondFragment", "Loading images from gallery")
        val uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(MediaStore.Images.Media._ID)
        val sortOrder = "${MediaStore.Images.Media.DATE_ADDED} DESC LIMIT 21"
        val cursor = requireActivity().contentResolver.query(uri, projection, null, null, sortOrder)

        if (cursor == null) {
            Log.d("SecondFragment", "Cursor is null")
            return
        }

        cursor.use {
            val columnIndexId = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            Log.d("SecondFragment", "Column Index ID: $columnIndexId")
            while (cursor.moveToNext()) {
                val id = cursor.getLong(columnIndexId)
                val contentUri = Uri.withAppendedPath(uri, id.toString())
                imageList.add(contentUri)
                Log.d("SecondFragment", "Image loaded: $contentUri")
            }
        }

        Log.d("SecondFragment", "Total images loaded: ${imageList.size}")
        binding.recyclerView.adapter?.notifyDataSetChanged()
        Log.d("SecondFragment", "RecyclerView data updated")
    }


    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_READ_STORAGE) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                Log.d("SecondFragment", "Permission granted, loading images")
                loadImagesFromGallery()
            } else {
                Log.d("SecondFragment", "Permission denied")
                // 권한 거부 시 처리
            }
        }
    }
}
