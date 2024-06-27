package com.example.madcamp01.first

import android.os.Bundle
import com.example.madcamp01.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.madcamp01.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val contacts = listOf(
            Contact("김민호", "Minho Kim", R.drawable.ic_launcher_foreground),
            Contact("박지연", "다정한 사람", R.drawable.ic_launcher_foreground),
            Contact("한예지", "교수님", R.drawable.ic_launcher_foreground),
            Contact("소연이", "24/7", R.drawable.ic_launcher_foreground),
            Contact("면경이", "24/7", R.drawable.ic_launcher_foreground),
            Contact("주영", "24/7", R.drawable.ic_launcher_foreground),
            Contact("지혜", "Do the Next things", R.drawable.ic_launcher_foreground),
            Contact("한성운", "행복", R.drawable.ic_launcher_foreground)
        )

        val adapter = ContactAdapter(contacts)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
