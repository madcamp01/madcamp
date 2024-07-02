package com.example.madcamp01.third

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.madcamp01.R
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.KakaoMapReadyCallback
import com.kakao.vectormap.KakaoMapSdk
import com.kakao.vectormap.LatLng
import com.kakao.vectormap.MapLifeCycleCallback
import com.kakao.vectormap.MapView
import com.kakao.vectormap.camera.CameraUpdateFactory
import com.kakao.vectormap.label.LabelManager
import com.kakao.vectormap.label.LabelOptions
import com.kakao.vectormap.label.LabelStyle
import com.kakao.vectormap.label.LabelStyles

class ThirdFragment : Fragment() {

    private lateinit var kakaoMap: KakaoMap
    private lateinit var labelManager: LabelManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize KakaoMapSdk in onCreate
        KakaoMapSdk.init(requireContext(), "d46a37ed9a48a8af47b08b5f5a338bb4")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_third, container, false)
        
        // MapView 객체 생성
        val mapView = view.findViewById<MapView>(R.id.map_view)

        mapView.start(object : MapLifeCycleCallback() {
            override fun onMapDestroy() {
                // Handle map destroy
            }

            override fun onMapError(e: Exception) {
                // Handle map error
            }
        }, object : KakaoMapReadyCallback() {
            override fun onMapReady(map: KakaoMap) {
                kakaoMap = map
                labelManager = kakaoMap.labelManager!!

                // 특정 좌표 설정 (예: 서울 시청)
                val markerPoint = LatLng.from(37.5665, 126.9780)

                // 라벨 스타일 설정
                val labelStyle = LabelStyles.from("defaultStyle", LabelStyle.from(R.drawable.sample_square_image))

                // 라벨 추가
                labelManager.layer?.addLabel(LabelOptions.from("label", markerPoint).setStyles(labelStyle))

                // 특정 위치로 맵 이동
                //kakaoMap.moveCamera(CameraUpdateFactory.newLatLngZoom(markerPoint, 15f))
            }
        })

        return view
    }
}
