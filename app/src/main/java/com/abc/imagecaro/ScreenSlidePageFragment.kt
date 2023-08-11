package com.abc.imagecaro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment

class ScreenSlidePageFragment(private val position: Int) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_screen_slide_page, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageView = view.findViewById<ImageView>(R.id.imageView)
        when (position) {
            0 -> {
                imageView.setImageDrawable(
                    AppCompatResources.getDrawable(
                        view.context,
                        R.drawable.image1
                    )
                )
            }

            1 -> {
                imageView.setImageDrawable(
                    AppCompatResources.getDrawable(
                        view.context,
                        R.drawable.image2
                    )
                )
            }

            2 -> {
                imageView.setImageDrawable(
                    AppCompatResources.getDrawable(
                        view.context,
                        R.drawable.image3
                    )
                )
            }

        }

    }
}