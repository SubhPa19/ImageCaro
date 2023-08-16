package adapter

import android.graphics.drawable.Drawable
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.abc.imagecaro.ScreenSlidePageFragment


class ScreenSlidePagerAdapter(fa: Fragment, var imageArrayList: MutableList<Drawable?>) :
    FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = imageArrayList.size

    override fun createFragment(position: Int): Fragment =
        ScreenSlidePageFragment(position, imageArrayList)
}