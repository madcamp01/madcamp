package com.example.madcamp01

import android.app.Application
import android.net.Uri
import android.util.Log
import com.example.madcamp01.DB.AppDatabase
import com.example.madcamp01.DB.Entities.Contact
import com.example.madcamp01.DB.Entities.Image
import com.example.madcamp01.DB.Entities.Place
import com.example.madcamp01.DB.Entities.Review
import com.kakao.vectormap.KakaoMapSdk
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GlobalApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // 다른 초기화 코드들

        // Database 인스턴스 생성
        val database = AppDatabase.getInstance(applicationContext)

        // Dummy data 삽입
        CoroutineScope(Dispatchers.IO).launch {
            insertDummyData(database)
        }
        // Kakao SDK 초기화
        KakaoMapSdk.init(this,"83d9e79df60833ffcbdfbb09199697b8")
    }
    private suspend fun insertDummyData(database: AppDatabase){
        insertDummyReviews(database)
        insertDummyImages(database)
        insertDummyPlaces(database)
        insertDummyContacts(database)
        Log.d("addreview","dummy end")
    }
    private suspend fun insertDummyReviews(database: AppDatabase) {
        val ratings = List(80) { kotlin.random.Random.nextInt(0, 6) }
        val imageIds = List(80) { (it % 20) + 1 }
        val placeIds = List(80) { (it % 10) + 1 }

        val dummyReviews = listOf(
            Review(1, 1, 1, 6, 1, "멋진 장소입니다!", "2023-06-20"),
            Review(2, 9, 1, 15, 4, "평범했어요.", "2023-08-06"),
            Review(3, 7, 1, 9, 2, "기분 좋았습니다.", "2023-08-02"),
            Review(4, 6, 1, 16, 1, "기분 좋았습니다.", "2023-07-05"),
            Review(5, 4, 2, 1, 3, "훌륭한 장소였습니다!", "2023-06-23"),
            Review(6, 10, 2, 10, 4, "나쁘지 않았어요.", "2023-07-09"),
            Review(7, 2, 2, 11, 4, "아주 좋았습니다.", "2023-06-28"),
            Review(8, 7, 2, 8, 1, "좋은 경험이었어요.", "2023-08-05"),
            Review(9, 4, 3, 17, 2, "꽤 좋았어요.", "2023-06-06"),
            Review(10, 2, 3, 4, 4, "기분 좋았습니다.", "2023-07-26"),
            Review(11, 3, 3, 14, 5, "강력 추천합니다!", "2023-07-28"),
            Review(12, 10, 3, 5, 2, "놀라운 경험이었어요!", "2023-08-25"),
            Review(13, 8, 4, 3, 5, "환상적이에요!", "2023-07-24"),
            Review(14, 2, 4, 7, 4, "즐거운 시간이었어요.", "2023-06-24"),
            Review(15, 6, 4, 18, 1, "나쁘지 않았어요.", "2023-08-18"),
            Review(16, 5, 4, 13, 2, "멋진 장소입니다!", "2023-06-09"),
            Review(17, 6, 5, 2, 5, "강력 추천합니다!", "2023-08-12"),
            Review(18, 10, 5, 19, 4, "나쁘지 않았어요.", "2023-07-29"),
            Review(19, 3, 5, 20, 4, "아주 좋았습니다.", "2023-08-13"),
            Review(20, 8, 5, 12, 1, "그럭저럭 괜찮았어요.", "2023-07-21"),
            Review(21, 7, 6, 14, 5, "평범했어요.", "2023-06-13"),
            Review(22, 5, 6, 1, 3, "놀라운 경험이었어요!", "2023-08-03"),
            Review(23, 2, 6, 9, 4, "멋진 곳이었어요!", "2023-07-14"),
            Review(24, 8, 6, 11, 1, "환상적인 경험이었어요!", "2023-06-08"),
            Review(25, 1, 7, 10, 5, "좋은 경험이었어요.", "2023-06-11"),
            Review(26, 9, 7, 4, 2, "평범했어요.", "2023-07-23"),
            Review(27, 10, 7, 15, 4, "환상적이에요!", "2023-08-10"),
            Review(28, 5, 7, 8, 3, "꽤 좋았어요.", "2023-08-28"),
            Review(29, 3, 8, 18, 1, "훌륭한 장소였습니다!", "2023-06-18"),
            Review(30, 4, 8, 20, 3, "멋진 장소입니다!", "2023-07-17"),
            Review(31, 2, 8, 13, 4, "좋은 경험이었어요.", "2023-06-22"),
            Review(32, 7, 8, 7, 2, "놀라운 경험이었어요!", "2023-07-06"),
            Review(33, 10, 9, 17, 1, "환상적인 경험이었어요!", "2023-07-01"),
            Review(34, 8, 9, 2, 5, "평범했어요.", "2023-08-09"),
            Review(35, 5, 9, 14, 4, "기분 좋았어요.", "2023-07-13"),
            Review(36, 1, 9, 3, 3, "즐거운 시간이었어요.", "2023-08-21"),
            Review(37, 1, 10, 19, 2, "그럭저럭 괜찮았어요.", "2023-06-12"),
            Review(38, 9, 10, 5, 4, "나쁘지 않았어요.", "2023-08-23"),
            Review(39, 4, 10, 6, 1, "꽤 좋았어요.", "2023-07-15"),
            Review(40, 3, 10, 11, 5, "아주 좋았습니다.", "2023-08-07"),
            Review(41, 5, 11, 16, 2, "좋은 경험이었어요.", "2023-07-20"),
            Review(42, 9, 11, 10, 1, "훌륭한 장소였습니다!", "2023-07-08"),
            Review(43, 7, 11, 13, 3, "멋진 장소입니다!", "2023-08-15"),
            Review(44, 10, 11, 20, 4, "환상적이에요!", "2023-06-30"),
            Review(45, 8, 12, 8, 1, "강력 추천합니다!", "2023-08-22"),
            Review(46, 5, 12, 9, 5, "꽤 좋았어요.", "2023-06-10"),
            Review(47, 6, 12, 7, 4, "놀라운 경험이었어요!", "2023-06-05"),
            Review(48, 1, 12, 12, 2, "기분 좋았어요.", "2023-07-31"),
            Review(49, 7, 13, 17, 4, "멋진 곳이었어요!", "2023-07-19"),
            Review(50, 2, 13, 1, 1, "즐거운 시간이었어요.", "2023-06-15"),
            Review(51, 3, 13, 14, 3, "강력 추천합니다!", "2023-08-16"),
            Review(52, 9, 13, 16, 5, "환상적이에요!", "2023-07-27"),
            Review(53, 4, 14, 18, 4, "아주 좋았습니다.", "2023-06-27"),
            Review(54, 5, 14, 6, 1, "기분 좋았습니다.", "2023-08-20"),
            Review(55, 8, 14, 19, 3, "꽤 좋았어요.", "2023-06-07"),
            Review(56, 6, 14, 11, 5, "좋은 경험이었어요.", "2023-08-14"),
            Review(57, 7, 15, 10, 2, "나쁘지 않았어요.", "2023-06-26"),
            Review(58, 3, 15, 2, 5, "평범했어요.", "2023-07-04"),
            Review(59, 10, 15, 13, 3, "꽤 좋았어요.", "2023-06-19"),
            Review(60, 1, 15, 4, 4, "훌륭한 장소였습니다!", "2023-08-19"),
            Review(61, 1, 16, 12, 5, "멋진 장소입니다!", "2023-06-21"),
            Review(62, 2, 16, 20, 4, "기분 좋았습니다.", "2023-08-24"),
            Review(63, 4, 16, 15, 3, "환상적이에요!", "2023-07-30"),
            Review(64, 9, 16, 9, 1, "강력 추천합니다!", "2023-07-10"),
            Review(65, 2, 17, 18, 4, "놀라운 경험이었어요!", "2023-08-01"),
            Review(66, 8, 17, 6, 3, "아주 좋았습니다.", "2023-06-25"),
            Review(67, 5, 17, 3, 5, "좋은 경험이었어요.", "2023-07-11"),
            Review(68, 4, 17, 14, 4, "꽤 좋았어요.", "2023-06-16"),
            Review(69, 10, 18, 11, 3, "기분 좋았어요.", "2023-08-11"),
            Review(70, 7, 18, 17, 1, "나쁘지 않았어요.", "2023-06-17"),
            Review(71, 3, 18, 19, 4, "환상적이에요!", "2023-07-25"),
            Review(72, 6, 18, 8, 5, "꽤 좋았어요.", "2023-07-18"),
            Review(73, 9, 19, 20, 1, "놀라운 경험이었어요!", "2023-06-29"),
            Review(74, 1, 19, 10, 4, "아주 좋았습니다.", "2023-08-17"),
            Review(75, 8, 19, 5, 3, "멋진 장소입니다!", "2023-07-03"),
            Review(76, 6, 19, 4, 5, "강력 추천합니다!", "2023-06-14"),
            Review(77, 1, 20, 2, 4, "기분 좋았어요.", "2023-08-04"),
            Review(78, 7, 20, 12, 3, "환상적이에요!", "2023-07-12"),
            Review(79, 2, 20, 16, 1, "멋진 장소입니다!", "2023-06-04"),
            Review(80, 9, 20, 7, 5, "훌륭한 장소였습니다!", "2023-07-07")
        )

        val existingReviews = database.reviewDao().getAllReviews()
        if (existingReviews.isEmpty()) {
            dummyReviews.forEach { database.reviewDao().insertReview(it) }
        }
    }
    private suspend fun insertDummyImages(database: AppDatabase) {
        val dummyImages = listOf(
            Image(1, "android.resource://com.example.madcamp01/" + R.drawable.img1),
            Image(2, "android.resource://com.example.madcamp01/" + R.drawable.img2),
            Image(3, "android.resource://com.example.madcamp01/" + R.drawable.img3),
            Image(4, "android.resource://com.example.madcamp01/" + R.drawable.img4),
            Image(5, "android.resource://com.example.madcamp01/" + R.drawable.img5),
            Image(6, "android.resource://com.example.madcamp01/" + R.drawable.img6),
            Image(7, "android.resource://com.example.madcamp01/" + R.drawable.img7),
            Image(8, "android.resource://com.example.madcamp01/" + R.drawable.img8),
            Image(9, "android.resource://com.example.madcamp01/" + R.drawable.img9),
            Image(10, "android.resource://com.example.madcamp01/" + R.drawable.img10),
            Image(11, "android.resource://com.example.madcamp01/" + R.drawable.img11),
            Image(12, "android.resource://com.example.madcamp01/" + R.drawable.img12),
            Image(13, "android.resource://com.example.madcamp01/" + R.drawable.img13),
            Image(14, "android.resource://com.example.madcamp01/" + R.drawable.img14),
            Image(15, "android.resource://com.example.madcamp01/" + R.drawable.img15),
            Image(16, "android.resource://com.example.madcamp01/" + R.drawable.img16),
            Image(17, "android.resource://com.example.madcamp01/" + R.drawable.img17),
            Image(18, "android.resource://com.example.madcamp01/" + R.drawable.img18),
            Image(19, "android.resource://com.example.madcamp01/" + R.drawable.img19),
            Image(20, "android.resource://com.example.madcamp01/" + R.drawable.img20)
        )
        val existingImages = database.imageDao().getAllImages()
        if (existingImages.isEmpty()) {
            dummyImages.forEach { database.imageDao().insertImage(it) }
        }
    }
    private suspend fun insertDummyPlaces(database: AppDatabase) {
        val places = listOf(
            Place(1, "KAIST Main Gate", 36.3726, 127.3605),
            Place(2, "한밭도서관", 36.3498, 127.3761),
            Place(3, "중앙로지하상가", 36.3287, 127.4301),
            Place(4, "서대전네거리역", 36.3214, 127.4085),
            Place(5, "대전역", 36.3326, 127.4348),
            Place(6, "둔산대공원", 36.3547, 127.3789),
            Place(7, "유성온천역", 36.3550, 127.3350),
            Place(8, "대전시립미술관", 36.3704, 127.3861),
            Place(9, "가수원동", 36.3111, 127.3778),
            Place(10, "대전월드컵경기장", 36.3630, 127.3412)
        )
        val existingPlaces = database.placeDao().getAllPlaces()
        if (existingPlaces.isEmpty()) {
            places.forEach { database.placeDao().insertPlace(it) }
        }
    }
    private suspend fun insertDummyContacts(database: AppDatabase) {
        val dummyContacts = listOf(
            Contact(1, "김철수", "010-1234-5678", "직장 동료", Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.img1)),
            Contact(2, "이영희", "010-2345-6789", "헬스 친구", Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.img2)),
            Contact(3, "박민수", "010-3456-7890", "대학 친구", Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.img3)),
            Contact(4, "최수진", "010-4567-8901", "이웃 주민", Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.img4)),
            Contact(5, "장미영", "010-5678-9012", "독서 모임", Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.img5)),
            Contact(6, "정현우", "010-6789-0123", "사촌", Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.img6)),
            Contact(7, "이수민", "010-7890-1234", "고등학교 친구", Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.img7)),
            Contact(8, "김다은", "010-8901-2345", "팀 멤버", Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.img8)),
            Contact(9, "오하나", "010-9012-3456", "여동생", Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.img9)),
            Contact(10, "이준호", "010-0123-4567", "남동생", Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.img10)),
            Contact(11, "백영준", "010-1111-2222", "대학교 선배", Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.img11)),
            Contact(12, "홍수아", "010-3333-4444", "중학교 친구", Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.img12)),
            Contact(13, "김재민", "010-5555-6666", "회사 상사", Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.img13)),
            Contact(14, "박지수", "010-7777-8888", "대학교 후배", Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.img14)),
            Contact(15, "한나래", "010-9999-0000", "독서 모임", Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.img15)),
            Contact(16, "강지훈", "010-1212-3434", "군대 친구", Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.img16)),
            Contact(17, "서지우", "010-4545-6767", "해외 친구", Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.img17)),
            Contact(18, "최서연", "010-7878-9090", "유학 동기", Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.img18)),
            Contact(19, "임도현", "010-1010-1212", "고등학교 선생님", Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.img19)),
            Contact(20, "유지훈", "010-3434-5656", "대학교 교수", Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.img20))
        )

        val existingContacts = database.contactDao().getAllContacts()
        if (existingContacts.isEmpty()) {
            dummyContacts.forEach { database.contactDao().insertContact(it) }
        }
    }
}