package ro.sapientia.android_9eloadas.viewmodel


import android.content.Context
import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import ro.sapientia.android_9eloadas.R
import java.io.BufferedReader
import java.io.InputStreamReader


class WordViewModel(private val context: Context) : ViewModel() {
    // Observables
    var liveData = MutableLiveData<MutableList<String>>()
    var contentChange = MutableLiveData<Int>()

    val wordList = mutableListOf("")

    private lateinit var timer: CountDownTimer

    init {
        liveData.value = wordList
        contentChange.value = 0
        val result = viewModelScope.launch(Dispatchers.IO) {
            readWordsFromFile()
            Log.i("XXX-Timer-Thread name", Thread.currentThread().name + ", list size: " + liveData.value!!.size)
        }
        startTimer()
    }

    /**
     * Reads data from a file - res/raw/words
     */
    suspend fun readWordsFromFile() {
        val inputStream = context.resources.openRawResource(R.raw.words)
        val reader = BufferedReader(InputStreamReader(inputStream))
        while (true) {
            val line = reader.readLine() ?: break
            wordList.add(line)
        }
        contentChange.postValue(1)
    }

    fun startTimer() {
        timer = object : CountDownTimer(10000, 1000) {
            override fun onTick(p0: Long) {
                liveData.value!!.shuffle()
                contentChange.value = 1 - contentChange.value!!
                Log.i("xxx", wordList!![0])
            }

            override fun onFinish() {
                Log.i("xxx", "Timer has finished")
            }
        }
        timer.start()
    }

}