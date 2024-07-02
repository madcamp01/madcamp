package com.example.madcamp01.third

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.madcamp01.R
import com.kakao.vectormap.*
import com.kakao.vectormap.camera.CameraPosition
import com.kakao.vectormap.camera.CameraUpdateFactory
import com.kakao.vectormap.label.LabelManager
import com.kakao.vectormap.label.LabelOptions
import com.kakao.vectormap.label.LabelStyle
import com.kakao.vectormap.label.LabelStyles

class ThirdFragment : Fragment() {
    private var mapView: MapView? = null
    private lateinit var kakaoMap: KakaoMap
    private lateinit var labelManager: LabelManager
    private lateinit var tvMapViewLatLng: TextView

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
        mapView = view.findViewById(R.id.map_view)

        mapView?.start(object : MapLifeCycleCallback() {
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

                // 특정 좌표 설정 (예: 대전역)
                val markerPoint = LatLng.from(36.3319, 127.4346)

                // 라벨 스타일 설정
                val labelStyle = LabelStyles.from("defaultStyle", LabelStyle.from(R.drawable.sample_square_image))

                // 라벨 추가
                labelManager.layer?.addLabel(LabelOptions.from("label", markerPoint).setStyles(labelStyle))

                // 특정 위치로 맵 이동
                kakaoMap.moveCamera(CameraUpdateFactory.newCenterPosition(markerPoint, 20))

                // Update latitude and longitude text
                updateLatLngText()

                // 카메라 이동 완료 리스너 설정
                kakaoMap.setOnCameraMoveEndListener(object : KakaoMap.OnCameraMoveEndListener {
                    override fun onCameraMoveEnd(kakaoMap: KakaoMap, cameraPosition: CameraPosition, gestureType: GestureType) {
                        updateLatLngText()
                    }
                })
            }
        })

        return view
    }

    private fun updateLatLngText() {
        val viewport = kakaoMap.viewport
        val x = viewport.width() / 2
        val y = viewport.height() / 2

        val centerPosition = kakaoMap.fromScreenPoint(x, y)
        tvMapViewLatLng.text = "Lat = ${centerPosition?.latitude}\nLng = ${centerPosition?.longitude}"
    }
}
