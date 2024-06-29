import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.madcamp01.DB.Entities.Contact
import com.example.madcamp01.DB.Entities.Image
import com.example.madcamp01.DB.Entities.Review
import com.example.madcamp01.DB.Repository.DataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ImageDetailViewModel(private val repository: DataRepository) : ViewModel() {

    private val _image = MutableLiveData<Image?>()
    val image: LiveData<Image?> get() = _image

    private val _contact = MutableLiveData<Contact?>()
    val contact: LiveData<Contact?> get() = _contact

    private val _reviews = MutableLiveData<List<Review>?>()
    val reviews: LiveData<List<Review>?> get() = _reviews

    fun loadImageAndContact(imageUri: Uri) {
        viewModelScope.launch(Dispatchers.IO) {
            val image = repository.getImageByUri(imageUri)
            _image.postValue(image)
            image?.let {
                _contact.postValue(repository.getContactByProfilePicture(imageUri))
            }
        }
    }

    fun loadReviews(imageId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _reviews.postValue(repository.getReviewsByImageId(imageId))
        }
    }
}
