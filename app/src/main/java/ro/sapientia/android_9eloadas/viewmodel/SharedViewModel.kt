package ro.sapientia.android_9eloadas.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ro.sapientia.android_9eloadas.model.Item
import ro.sapientia.android_9eloadas.util.Utils

class SharedViewModel : ViewModel() {
    // 1. valtozat - 8. eloadas
//    val list = Utils().generateDummyList(100)
//    var position = 0

    //2. valtozat
    val list: MutableList<Item> = Utils().generateDummyList(100).toMutableList()
    val position = MutableLiveData<Int>(0)

}