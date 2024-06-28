package com.example.madcamp01.first

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.madcamp01.R
import com.example.madcamp01.DB.contacts.ContactDAO

class ContactUpdatePopup : AppCompatActivity() {
    private lateinit var etName: EditText
    private lateinit var etStatus: EditText
    private lateinit var etPhoneNumber: EditText
    private lateinit var etResImageId: EditText
    private lateinit var btnSave: Button
    private lateinit var contactDAO: ContactDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_update_popup)
        // View 초기화
        etName = findViewById(R.id.etName)
        etStatus = findViewById(R.id.etStatus)
        etPhoneNumber = findViewById(R.id.etPhoneNumber)
        etResImageId = findViewById(R.id.etResImageId)
        btnSave = findViewById(R.id.saveButton)

        val closeButton: Button = findViewById(R.id.close_button)
        // DAO 초기화
        contactDAO = ContactDAO(this)

        btnSave.setOnClickListener {
            saveContact()
        }
        closeButton.setOnClickListener {
            finish()  // 팝업 액티비티 종료
        }
    }
    private fun saveContact(){
        val name = etName.text.toString()
        var status = etStatus.text.toString()
        val phoneNumber = etPhoneNumber.text.toString()
//        var resimageid = etResImageId.text.toString() // TODO: 사진 선택할 수 있게 바꾸기


        if (name.isEmpty() || phoneNumber.isEmpty()) {
            Toast.makeText(this, "모든 필드를 채워주세요.", Toast.LENGTH_SHORT).show()
            return
        }
        if(status.isEmpty()){
            status = "무직"
        }


        val newContact = Contact(
            id = 0, // Auto-increment를 사용한다면 0을 넣어도 됩니다.
            name = name,
            status = status,
            phoneNumber = phoneNumber,
            imageResId = R.drawable.ic_launcher_foreground // 기본 이미지 설정
        )

        contactDAO.insertContact(newContact)
        Toast.makeText(this, "연락처가 저장되었습니다.", Toast.LENGTH_SHORT).show()
        finish() // 액티비티 종료
    }
}
