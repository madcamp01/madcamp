import android.net.Uri
import com.example.madcamp01.DB.Entities.Contact
import com.example.madcamp01.DB.Entities.Image
import com.example.madcamp01.DB.Entities.Place
import com.example.madcamp01.DB.Entities.Review
import com.example.madcamp01.R


val images = listOf(
    Image(1, Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.sample_square_image)),
    Image(2, Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.sample_square_image)),
    Image(3, Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.sample_square_image)),
    Image(4, Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.sample_square_image)),
    Image(5, Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.sample_square_image)),
    Image(6, Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.sample_square_image)),
    Image(7, Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.sample_square_image)),
    Image(8, Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.sample_square_image)),
    Image(9, Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.sample_square_image)),
    Image(10, Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.sample_square_image)),
    Image(11, Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.sample_square_image)),
    Image(12, Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.sample_square_image)),
    Image(13, Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.sample_square_image)),
    Image(14, Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.sample_square_image)),
    Image(15, Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.sample_square_image)),
    Image(16, Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.sample_square_image)),
    Image(17, Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.sample_square_image)),
    Image(18, Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.sample_square_image)),
    Image(19, Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.sample_square_image)),
    Image(20, Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.sample_square_image))
)

val contacts = listOf(
    Contact(1, "John Doe", "010-1234-5678", "Friend from work", Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.sample_square_image)),
    Contact(2, "Jane Smith", "010-2345-6789", "Gym buddy", Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.sample_square_image)),
    Contact(3, "Mike Johnson", "010-3456-7890", "College friend", Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.sample_square_image)),
    Contact(4, "Emily Davis", "010-4567-8901", "Neighbor", Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.sample_square_image)),
    Contact(5, "Sarah Brown", "010-5678-9012", "Book club member", Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.sample_square_image)),
    Contact(6, "Chris Wilson", "010-6789-0123", "Cousin", Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.sample_square_image)),
    Contact(7, "Jessica Lee", "010-7890-1234", "High school friend", Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.sample_square_image)),
    Contact(8, "David Kim", "010-8901-2345", "Team member", Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.sample_square_image)),
    Contact(9, "Laura Martinez", "010-9012-3456", "Sister", Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.sample_square_image)),
    Contact(10, "James Anderson", "010-0123-4567", "Brother", Uri.parse("android.resource://com.example.madcamp01/" + R.drawable.sample_square_image))
)

val places = listOf(
    Place(1, "Daejeon Station", "36.3323,127.4347"),
    Place(2, "Expo Science Park", "36.3741,127.3864"),
    Place(3, "Hanbat Arboretum", "36.3664,127.3889"),
    Place(4, "Daejeon Museum of Art", "36.3705,127.3850"),
    Place(5, "Gyejoksan Mountain Red Clay Trail", "36.3956,127.3725")
)

val reviews = listOf(
    Review(1, 1, 1, 1, 4, "Great place!", "2023-06-01"),
    Review(2, 2, 2, 2, 5, "Amazing experience!", "2023-06-02"),
    Review(3, 3, 3, 3, 3, "It was okay.", "2023-06-03"),
    Review(4, 4, 4, 4, 4, "Nice and peaceful.", "2023-06-04"),
    Review(5, 5, 5, 5, 5, "Loved it!", "2023-06-05"),
    Review(6, 1, 6, 6, 4, "Good place to visit.", "2023-06-06"),
    Review(7, 2, 7, 7, 5, "Wonderful!", "2023-06-07"),
    Review(8, 3, 8, 8, 3, "Average.", "2023-06-08"),
    Review(9, 4, 9, 9, 4, "Quite good.", "2023-06-09"),
    Review(10, 5, 10, 10, 5, "Excellent!", "2023-06-10"),
    Review(11, 1, 1, 11, 4, "Enjoyed it.", "2023-06-11"),
    Review(12, 2, 2, 12, 5, "Fantastic!", "2023-06-12"),
    Review(13, 3, 3, 13, 3, "Not bad.", "2023-06-13"),
    Review(14, 4, 4, 14, 4, "Pleasant.", "2023-06-14"),
    Review(15, 5, 5, 15, 5, "Amazing!", "2023-06-15"),
    Review(16, 1, 6, 16, 4, "Worth a visit.", "2023-06-16"),
    Review(17, 2, 7, 17, 5, "Highly recommend!", "2023-06-17"),
    Review(18, 3, 8, 18, 3, "It's okay.", "2023-06-18"),
    Review(19, 4, 9, 19, 4, "Good.", "2023-06-19"),
    Review(20, 5, 10, 20, 5, "Nice", "2023-06-28")
)