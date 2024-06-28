package com.example.madcamp01.first

import android.content.Intent
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
    private lateinit var contactAdapter: ContactAdapter

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
            Contact("김민호", "개발자", phoneNumber = "01012345678", R.drawable.ic_launcher_foreground),
            Contact("박지연", "선생님",phoneNumber = "01012345678", R.drawable.ic_launcher_foreground),
            Contact("한예지", "교수님", phoneNumber = "01012345678",R.drawable.ic_launcher_foreground),
            Contact("소연이", "디자이너",phoneNumber = "01012345678", R.drawable.ic_launcher_foreground),
            Contact("면경이", "마케터",phoneNumber = "01012345678", R.drawable.ic_launcher_foreground),
            Contact("주영", "작가", phoneNumber = "01012345678",R.drawable.ic_launcher_foreground),
            Contact("지혜", "연구원", phoneNumber = "01012345678",R.drawable.ic_launcher_foreground),
            Contact("한성운", "엔지니어",phoneNumber = "01012345678", R.drawable.ic_launcher_foreground),
            Contact("강다연", "기획자",phoneNumber = "01012345678", R.drawable.ic_launcher_foreground),
            Contact("고영민", "매니저",phoneNumber = "01012345678", R.drawable.ic_launcher_foreground),
            Contact("구나현", "프로그래머", phoneNumber = "01012345678",R.drawable.ic_launcher_foreground),
            Contact("권윤아", "기자",phoneNumber = "01012345678", R.drawable.ic_launcher_foreground),
            Contact("김도현", "의사", phoneNumber = "01012345678",R.drawable.ic_launcher_foreground),
            Contact("김서준", "변호사", phoneNumber = "01012345678",R.drawable.ic_launcher_foreground),
            Contact("김수민", "사업가", phoneNumber = "01012345678",R.drawable.ic_launcher_foreground),
            Contact("김아람", "예술가",phoneNumber = "01012345678", R.drawable.ic_launcher_foreground),
            Contact("김재훈", "학생",phoneNumber = "01012345678", R.drawable.ic_launcher_foreground),
            Contact("김현우", "유튜버", phoneNumber = "01012345678",R.drawable.ic_launcher_foreground),
            Contact("남지현", "강사",phoneNumber = "01012345678", R.drawable.ic_launcher_foreground),
            Contact("문지호", "바리스타",phoneNumber = "01012345678", R.drawable.ic_launcher_foreground),
            Contact("박도윤", "정치가", phoneNumber = "01012345678",R.drawable.ic_launcher_foreground)
        )

        val sortedContacts = getSortedContacts(contacts) // ㄱㄴㄷ순 정렬
        contactAdapter = ContactAdapter(sortedContacts) { contact ->
            val intent = Intent(context, AddressTab::class.java).apply {
                putExtra("name", contact.name)
                putExtra("status", contact.status)
                putExtra("phoneNumber", contact.phoneNumber)
                putExtra("imageResId", contact.imageResId)
            }
            startActivity(intent)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = contactAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
