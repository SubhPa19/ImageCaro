package util

import adapter.ImageTextItem
import com.abc.imagecaro.R

class DataUtil {
    companion object {
        fun getDummyData(position: Int): List<ImageTextItem> {
            val mutableList = mutableListOf<ImageTextItem>()
            for (item in 1..20) {
                mutableList.add(
                    ImageTextItem(
                        R.drawable.baseline_cake_24,
                        "This is text $item on page number $position"
                    )
                )
            }
            return mutableList
        }
    }
}