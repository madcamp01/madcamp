package com.example.madcamp01.third

import android.graphics.PointF
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.madcamp01.DB.AppDatabase
import com.example.madcamp01.DB.Entities.Place
import com.example.madcamp01.R
import com.kakao.vectormap.*
import com.kakao.vectormap.camera.CameraUpdateFactory
import com.kakao.vectormap.label.LabelManager
import com.kakao.vectormap.label.LabelOptions
import com.kakao.vectormap.label.LabelStyle
import com.kakao.vectormap.label.LabelStyles
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

                lifecycleScope.launch {
                    val database = AppDatabase.getInstance(requireContext())
                    val places = getPlacesFromDb(database)
                    if (places.isEmpty()) {
                        insertDummyData(database)
                    }
                    loadPlaces(database)
                }

                // 맵 클릭 리스너 설정
                kakaoMap.setOnMapClickListener { map, position, screenPoint, poi ->
                    Log.d("MapClick", "Lat: ${position.latitude}, Lng: ${position.longitude}")
                }
            }
        })

        return view
    }

    private suspend fun insertDummyData(database: AppDatabase) {
        val places = listOf(
            Place(1, "KAIST Main Gate", 36.3726, 127.3605),
            Place(2, "Expo Science Park", 36.3741, 127.3864),
            Place(3, "Hanbat Arboretum", 36.3664, 127.3889),
            Place(4, "Daejeon Museum of Art", 36.3705, 127.3850),
            Place(5, "Gyejoksan Mountain Red Clay Trail", 36.3956, 127.3725)
        )
        withContext(Dispatchers.IO) {
            database.placeDao().apply {
                places.forEach { insertPlace(it) }
            }
        }
    }

    private suspend fun getPlacesFromDb(database: AppDatabase): List<Place> {
        return withContext(Dispatchers.IO) {
            database.placeDao().getAllPlaces()
        }
    }

    private suspend fun loadPlaces(database: AppDatabase) {
        val places = getPlacesFromDb(database)
        places.forEach { place ->
            val markerPoint = LatLng.from(place.latitude, place.longitude)
            val labelStyle = LabelStyles.from("defaultStyle", LabelStyle.from(R.drawable.marker2))
            labelManager.layer?.addLabel(LabelOptions.from(place.placeName, markerPoint).setStyles(labelStyle))
        }

        if (places.isNotEmpty()) {
            val firstPlace = places.first()
            val initialPosition = LatLng.from(firstPlace.latitude, firstPlace.longitude)
            kakaoMap.moveCamera(CameraUpdateFactory.newCenterPosition(initialPosition, 13))
        }
    }
}
