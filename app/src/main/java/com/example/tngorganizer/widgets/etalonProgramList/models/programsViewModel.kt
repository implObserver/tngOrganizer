import android.content.ClipData.Item
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tngorganizer.services.dao.etalon.ProgramDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {
    private val _items = MutableStateFlow<List<Item>>(emptyList())
    val items: StateFlow<List<Item>> = _items

    fun loadItems() {

    }
}
