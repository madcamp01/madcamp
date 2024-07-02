package com.example.madcamp01.third

import android.graphics.BitmapFactory
import android.graphics.Point
import android.graphics.Rect
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.madcamp01.R
import com.google.gson.Gson
import com.kakao.vectormap.*
import com.kakao.vectormap.camera.CameraPosition
import com.kakao.vectormap.camera.CameraUpdateFactory
import com.kakao.vectormap.label.LabelManager
import com.kakao.vectormap.label.LabelOptions
import com.kakao.vectormap.label.LabelStyle
import com.kakao.vectormap.label.LabelStyles
import java.io.InputStreamReader

class ThirdFragment : Fragment(), KakaoMap.OnCameraMoveEndListener {

    private lateinit var kakaoMap: KakaoMap
    private lateinit var labelManager: LabelManager
    private lateinit var customStyle: LabelStyles
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
        val mapView = view.findViewById<MapView>(R.id.map_view)
        //tvMapViewLatLng = view.findViewById(R.id.tv_map_view_latlng)

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

                // JSON 파일 읽기
                val inputStream = resources.openRawResource(R.raw.animationlabel)
                val reader = InputStreamReader(inputStream)
                val gson = Gson()
                val labelStyleJson = gson.fromJson(reader, LabelStyleJson::class.java)

                // Base64 인코딩된 이미지 데이터를 디코딩하여 Bitmap으로 변환
                val decodedString = Base64.decode(labelStyleJson.imageData, Base64.DEFAULT)
                val bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)

                // 라벨 스타일 설정
                customStyle = labelManager.addLabelStyles(
                    LabelStyles.from(labelStyleJson.id, LabelStyle.from(bitmap))
                )!!

                // 라벨 추가
                labelManager.layer?.addLabel(LabelOptions.from("label", markerPoint).setStyles(customStyle))

                // 특정 위치로 맵 이동
                kakaoMap.moveCamera(CameraUpdateFactory.newCenterPosition(markerPoint))

                // Update latitude and longitude text
                updateLatLngText()

                kakaoMap.setOnCameraMoveEndListener(this@ThirdFragment)
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

    override fun onCameraMoveEnd(kakaoMap: KakaoMap, cameraPosition: CameraPosition, gestureType: GestureType) {
        updateLatLngText()
    }

    data class LabelStyleJson(
        val id: String,
        val imageData: String
    )
}
