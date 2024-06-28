package com.example.madcamp01.first

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.madcamp01.R


class AddressTab  : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.address_tab)

        val name = intent.getStringExtra("name")
        val job = intent.getStringExtra("status")
        val number = intent.getStringExtra("phoneNumber")
        val imageResId = intent.getIntExtra("imageResId", 0)

        findViewById<TextView>(R.id.textname).text = name
        findViewById<TextView>(R.id.textPhoneNumber).text = number
        findViewById<TextView>(R.id.textStatus).text = job
        findViewById<ImageView>(R.id.imageView).setImageResource(imageResId)
    }
}