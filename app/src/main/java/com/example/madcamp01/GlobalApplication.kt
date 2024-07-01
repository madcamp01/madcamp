package com.example.madcamp01

import android.app.Application
import com.kakao.vectormap.KakaoMapSdk

class GlobalApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // 다른 초기화 코드들

        // Kakao SDK 초기화
        KakaoMapSdk.init(this,"83d9e79df60833ffcbdfbb09199697b8")
    }
}