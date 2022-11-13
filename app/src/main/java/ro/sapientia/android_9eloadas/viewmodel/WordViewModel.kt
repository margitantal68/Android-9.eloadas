package ro.sapientia.android_9eloadas.viewmodel


import android.content.Context
import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import java.io.BufferedReader
import java.io.InputStreamReader


class WordViewModel(private val context: Context) : ViewModel() {
    // Observables
    var liveData = MutableLiveData<MutableList<String>>()
    var contentChange = MutableLiveData<Int>()

    val wordList = mutableListOf("")

    //    val wordList = mutableListOf<String>("alma", "korte", "szilva", "eper", "dio", "mogyoro", "szolo",
//        "szeder", "gesztenye", "malna", "citrom", "narancs", "mango", "banan")
    private lateinit var timer: CountDownTimer

    init {
        liveData.value = wordList
        contentChange.value = 0
        readWordsFromFile()
        Log.i(
            "XXX-Thread name ",
            Thread.currentThread().name + ", list size: " + liveData.value!!.size
        )
//        viewModelScope.launch(Dispatchers.IO) {
//            readWordsFromFile()
//            Log.i("XXX-Timer-Thread name", Thread.currentThread().name + ", list size: " + liveData.value!!.size)
//        }

        startTimer()
    }

    /**
     * Reads data from a file - res/raw
     */
//    suspend fun readWordsFromFile() {
    fun readWordsFromFile() {
        val inputStream = context.resources.openRawResource(
            context.resources.getIdentifier(
                "raw/words",
                null,
                "ro.sapientia.android_9eloadas"
            )
        )
        val reader = BufferedReader(InputStreamReader(inputStream))
        while (true) {
            val line = reader.readLine() ?: break
            wordList.add(line)
        }
//        contentChange.setValue(1 - contentChange.value!!)
        // suspend
         contentChange.postValue(1)
    }

    fun startTimer() {
        timer = object : CountDownTimer(100000, 1000) {
            override fun onTick(p0: Long) {
                liveData.value!!.shuffle()
                contentChange.value = 1 - contentChange.value!!
            }

            override fun onFinish() {
            }
        }
        timer.start()
    }

}