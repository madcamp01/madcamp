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
            // Contact 1
            Review(1, placeIds[0], 1, imageIds[0], ratings[0], "좋은 장소입니다!", "2023-06-01"),
            Review(2, placeIds[1], 1, imageIds[1], ratings[1], "놀라운 경험이었습니다!", "2023-06-02"),
            Review(3, placeIds[2], 1, imageIds[2], ratings[2], "그럭저럭 괜찮았어요.", "2023-06-03"),
            Review(4, placeIds[3], 1, imageIds[3], ratings[3], "조용하고 평화로웠습니다.", "2023-06-04"),
            // Contact 2
            Review(5, placeIds[4], 2, imageIds[4], ratings[4], "정말 좋았어요!", "2023-06-05"),
            Review(6, placeIds[5], 2, imageIds[5], ratings[5], "방문할 가치가 있습니다.", "2023-06-06"),
            Review(7, placeIds[6], 2, imageIds[6], ratings[6], "정말 멋져요!", "2023-06-07"),
            Review(8, placeIds[7], 2, imageIds[7], ratings[7], "평범했습니다.", "2023-06-08"),
            // Contact 3
            Review(9, placeIds[8], 3, imageIds[8], ratings[8], "꽤 좋았어요.", "2023-06-09"),
            Review(10, placeIds[9], 3, imageIds[9], ratings[9], "훌륭했습니다!", "2023-06-10"),
            Review(11, placeIds[10], 3, imageIds[10], ratings[10], "즐거운 시간이었어요.", "2023-06-11"),
            Review(12, placeIds[11], 3, imageIds[11], ratings[11], "환상적이에요!", "2023-06-12"),
            // Contact 4
            Review(13, placeIds[12], 4, imageIds[12], ratings[12], "나쁘지 않았어요.", "2023-06-13"),
            Review(14, placeIds[13], 4, imageIds[13], ratings[13], "기분 좋았습니다.", "2023-06-14"),
            Review(15, placeIds[14], 4, imageIds[14], ratings[14], "놀라워요!", "2023-06-15"),
            Review(16, placeIds[15], 4, imageIds[15], ratings[15], "방문할 만합니다.", "2023-06-16"),
            // Contact 5
            Review(17, placeIds[16], 5, imageIds[16], ratings[16], "강력 추천합니다!", "2023-06-17"),
            Review(18, placeIds[17], 5, imageIds[17], ratings[17], "그럭저럭 괜찮았어요.", "2023-06-18"),
            Review(19, placeIds[18], 5, imageIds[18], ratings[18], "좋았어요.", "2023-06-19"),
            Review(20, placeIds[19], 5, imageIds[19], ratings[19], "아주 좋습니다.", "2023-06-20"),
            // Contact 6
            Review(21, placeIds[20], 6, imageIds[20], ratings[20], "멋진 곳이었습니다!", "2023-06-21"),
            Review(22, placeIds[21], 6, imageIds[21], ratings[21], "환상적인 경험이었어요!", "2023-06-22"),
            Review(23, placeIds[22], 6, imageIds[22], ratings[22], "그럭저럭 괜찮았습니다.", "2023-06-23"),
            Review(24, placeIds[23], 6, imageIds[23], ratings[23], "조용하고 평화로웠습니다.", "2023-06-24"),
            // Contact 7
            Review(25, placeIds[24], 7, imageIds[24], ratings[24], "정말 멋졌습니다!", "2023-06-25"),
            Review(26, placeIds[25], 7, imageIds[25], ratings[25], "방문할 가치가 있었습니다.", "2023-06-26"),
            Review(27, placeIds[26], 7, imageIds[26], ratings[26], "정말 대단해요!", "2023-06-27"),
            Review(28, placeIds[27], 7, imageIds[27], ratings[27], "그냥 그랬어요.", "2023-06-28"),
            // Contact 8
            Review(29, placeIds[28], 8, imageIds[28], ratings[28], "꽤 좋았어요.", "2023-06-29"),
            Review(30, placeIds[29], 8, imageIds[29], ratings[29], "훌륭했습니다!", "2023-06-30"),
            Review(31, placeIds[30], 8, imageIds[30], ratings[30], "즐거운 시간이었어요.", "2023-07-01"),
            Review(32, placeIds[31], 8, imageIds[31], ratings[31], "환상적이에요!", "2023-07-02"),
            // Contact 9
            Review(33, placeIds[32], 9, imageIds[32], ratings[32], "나쁘지 않았어요.", "2023-07-03"),
            Review(34, placeIds[33], 9, imageIds[33], ratings[33], "기분 좋았습니다.", "2023-07-04"),
            Review(35, placeIds[34], 9, imageIds[34], ratings[34], "놀라워요!", "2023-07-05"),
            Review(36, placeIds[35], 9, imageIds[35], ratings[35], "방문할 만합니다.", "2023-07-06"),
            // Contact 10
            Review(37, placeIds[36], 10, imageIds[36], ratings[36], "강력 추천합니다!", "2023-07-07"),
            Review(38, placeIds[37], 10, imageIds[37], ratings[37], "그럭저럭 괜찮았어요.", "2023-07-08"),
            Review(39, placeIds[38], 10, imageIds[38], ratings[38], "좋았어요.", "2023-07-09"),
            Review(40, placeIds[39], 10, imageIds[39], ratings[39], "아주 좋습니다.", "2023-07-10"),
            // Contact 11
            Review(41, placeIds[40], 11, imageIds[40], ratings[40], "멋진 곳이었습니다!", "2023-07-11"),
            Review(42, placeIds[41], 11, imageIds[41], ratings[41], "환상적인 경험이었어요!", "2023-07-12"),
            Review(43, placeIds[42], 11, imageIds[42], ratings[42], "그럭저럭 괜찮았습니다.", "2023-07-13"),
            Review(44, placeIds[43], 11, imageIds[43], ratings[43], "조용하고 평화로웠습니다.", "2023-07-14"),
            // Contact 12
            Review(45, placeIds[44], 12, imageIds[44], ratings[44], "정말 멋졌습니다!", "2023-07-15"),
            Review(46, placeIds[45], 12, imageIds[45], ratings[45], "방문할 가치가 있었습니다.", "2023-07-16"),
            Review(47, placeIds[46], 12, imageIds[46], ratings[46], "정말 대단해요!", "2023-07-17"),
            Review(48, placeIds[47], 12, imageIds[47], ratings[47], "그냥 그랬어요.", "2023-07-18"),
            // Contact 13
            Review(49, placeIds[48], 13, imageIds[48], ratings[48], "꽤 좋았어요.", "2023-07-19"),
            Review(50, placeIds[49], 13, imageIds[49], ratings[49], "훌륭했습니다!", "2023-07-20"),
            Review(51, placeIds[50], 13, imageIds[50], ratings[50], "즐거운 시간이었어요.", "2023-07-21"),
            Review(52, placeIds[51], 13, imageIds[51], ratings[51], "환상적이에요!", "2023-07-22"),
            // Contact 14
            Review(53, placeIds[52], 14, imageIds[52], ratings[52], "나쁘지 않았어요.", "2023-07-23"),
            Review(54, placeIds[53], 14, imageIds[53], ratings[53], "기분 좋았습니다.", "2023-07-24"),
            Review(55, placeIds[54], 14, imageIds[54], ratings[54], "놀라워요!", "2023-07-25"),
            Review(56, placeIds[55], 14, imageIds[55], ratings[55], "방문할 만합니다.", "2023-07-26"),
            // Contact 15
            Review(57, placeIds[56], 15, imageIds[56], ratings[56], "강력 추천합니다!", "2023-07-27"),
            Review(58, placeIds[57], 15, imageIds[57], ratings[57], "그럭저럭 괜찮았어요.", "2023-07-28"),
            Review(59, placeIds[58], 15, imageIds[58], ratings[58], "좋았어요.", "2023-07-29"),
            Review(60, placeIds[59], 15, imageIds[59], ratings[59], "아주 좋습니다.", "2023-07-30"),
            // Contact 16
            Review(61, placeIds[60], 16, imageIds[60], ratings[60], "멋진 곳이었습니다!", "2023-07-31"),
            Review(62, placeIds[61], 16, imageIds[61], ratings[61], "환상적인 경험이었어요!", "2023-08-01"),
            Review(63, placeIds[62], 16, imageIds[62], ratings[62], "그럭저럭 괜찮았습니다.", "2023-08-02"),
            Review(64, placeIds[63], 16, imageIds[63], ratings[63], "조용하고 평화로웠습니다.", "2023-08-03"),
            // Contact 17
            Review(65, placeIds[64], 17, imageIds[64], ratings[64], "정말 멋졌습니다!", "2023-08-04"),
            Review(66, placeIds[65], 17, imageIds[65], ratings[65], "방문할 가치가 있었습니다.", "2023-08-05"),
            Review(67, placeIds[66], 17, imageIds[66], ratings[66], "정말 대단해요!", "2023-08-06"),
            Review(68, placeIds[67], 17, imageIds[67], ratings[67], "그냥 그랬어요.", "2023-08-07"),
            // Contact 18
            Review(69, placeIds[68], 18, imageIds[68], ratings[68], "꽤 좋았어요.", "2023-08-08"),
            Review(70, placeIds[69], 18, imageIds[69], ratings[69], "훌륭했습니다!", "2023-08-09"),
            Review(71, placeIds[70], 18, imageIds[70], ratings[70], "즐거운 시간이었어요.", "2023-08-10"),
            Review(72, placeIds[71], 18, imageIds[71], ratings[71], "환상적이에요!", "2023-08-11"),
            // Contact 19
            Review(73, placeIds[72], 19, imageIds[72], ratings[72], "나쁘지 않았어요.", "2023-08-12"),
            Review(74, placeIds[73], 19, imageIds[73], ratings[73], "기분 좋았습니다.", "2023-08-13"),
            Review(75, placeIds[74], 19, imageIds[74], ratings[74], "놀라워요!", "2023-08-14"),
            Review(76, placeIds[75], 19, imageIds[75], ratings[75], "방문할 만합니다.", "2023-08-15"),
            // Contact 20
            Review(77, placeIds[76], 20, imageIds[76], ratings[76], "강력 추천합니다!", "2023-08-16"),
            Review(78, placeIds[77], 20, imageIds[77], ratings[77], "그럭저럭 괜찮았어요.", "2023-08-17"),
            Review(79, placeIds[78], 20, imageIds[78], ratings[78], "좋았어요.", "2023-08-18"),
            Review(80, placeIds[79], 20, imageIds[79], ratings[79], "아주 좋습니다.", "2023-08-19")
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