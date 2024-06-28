package com.example.madcamp01.first

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
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
    private lateinit var contactDAO: ContactDAO

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("contactDAO", "hello")
        contactDAO = ContactDAO(requireContext())
        //샘플 데이터 삽입
        if(contactDAO.getAllContacts().isEmpty()) {
            val contacts = listOf(
                Contact(
                    id=0,
                    "김민호",
                    "개발자",
                    phoneNumber = "01012345678",
                    R.drawable.ic_launcher_foreground
                ),
                Contact(
                    id=1,
                    "박지연",
                    "선생님",
                    phoneNumber = "01012345678",
                    R.drawable.ic_launcher_foreground
                ),
                Contact(
                    id=2,
                    "한예지",
                    "교수님",
                    phoneNumber = "01012345678",
                    R.drawable.ic_launcher_foreground
                ),
                Contact(
                    id=3,
                    "소연이",
                    "디자이너",
                    phoneNumber = "01012345678",
                    R.drawable.ic_launcher_foreground
                ),
                Contact(
                    id=4,
                    "면경이",
                    "마케터",
                    phoneNumber = "01012345678",
                    R.drawable.ic_launcher_foreground
                ),
                Contact(id=5,"주영", "작가", phoneNumber = "01012345678", R.drawable.ic_launcher_foreground),
                Contact(id=6,"지혜", "연구원", phoneNumber = "01012345678", R.drawable.ic_launcher_foreground)
            )
            contacts.forEach{contactDAO.insertContact(it)}

        }
        val contacts = contactDAO.getAllContacts()
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
