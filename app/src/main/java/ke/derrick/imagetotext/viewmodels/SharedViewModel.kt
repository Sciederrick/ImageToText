package ke.derrick.imagetotext.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.mlkit.vision.text.Text.TextBlock

class SharedViewModel: ViewModel() {
    val textBlocks = MutableLiveData<List<TextBlock>>()

    fun setTextBlocks(payload: List<TextBlock>) {
        textBlocks.value = payload
    }
}