package com.example.madcamp01.second

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.madcamp01.R
import com.example.madcamp01.databinding.FragmentSecondBinding

class SecondFragment : Fragment(R.layout.fragment_second) {

    private lateinit var binding: FragmentSecondBinding
    private val viewModel: ImageViewModel by viewModels()
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
            Log.d("SecondFragment", "Permission granted, setting up RecyclerView")
            setupRecyclerView()
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        viewModel.imageList.observe(viewLifecycleOwner, { images ->
            binding.recyclerView.adapter = ImageAdapter(images)
            Log.d("SecondFragment", "RecyclerView setup complete")
        })
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_READ_STORAGE) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                Log.d("SecondFragment", "Permission granted, setting up RecyclerView")
                setupRecyclerView()
            } else {
                Log.d("SecondFragment", "Permission denied")
                // 권한 거부 시 처리
            }
        }
    }
}
