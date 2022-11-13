package ro.sapientia.android_9eloadas.util

import ro.sapientia.android_9eloadas.R
import ro.sapientia.android_9eloadas.model.Item

class Utils {
    fun generateDummyList(size: Int): List<Item> {
        val list = ArrayList<Item>()
        for (i in 0 until size) {
            val drawable = when (i % 3) {
                0 -> R.drawable.alarm
                1 -> R.drawable.android
                else -> R.drawable.backback
            }
            val item = Item(drawable, "Item $i", "Line 2")
            list += item
        }
        return list
    }
}
