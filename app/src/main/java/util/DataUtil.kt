package util

import adapter.ImageTextItem
import android.content.Context
import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources
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


        fun getDummyImageData(context: Context): MutableList<Drawable?> {
            val drawable1 = AppCompatResources.getDrawable(
                context,
                R.drawable.image1
            )
            val drawable2 = AppCompatResources.getDrawable(
                context,
                R.drawable.image2
            )
            val drawable3 = AppCompatResources.getDrawable(
                context,
                R.drawable.image3
            )
            val imageArrayList = mutableListOf<Drawable?>()
            imageArrayList.add(drawable1)
            imageArrayList.add(drawable2)
            imageArrayList.add(drawable3)
            imageArrayList.add(drawable1)
            imageArrayList.add(drawable2)
            imageArrayList.add(drawable3)
            return imageArrayList
        }
    }
}

