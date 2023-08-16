package com.abc.imagecaro

import adapter.ImageTextAdapter
import adapter.ImageTextItem
import adapter.ScreenSlidePagerAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.abc.imagecaro.databinding.FragmentImageCaroBinding
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import util.DataUtil
import util.DataUtil.Companion.getDummyData


class ImageCaroFragment : Fragment() {

    private var _binding: FragmentImageCaroBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewPager: ViewPager2
    private lateinit var recycleView: RecyclerView
    private lateinit var currentDataSet: List<ImageTextItem>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentImageCaroBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager = binding.pager
        val pagerAdapter =
            ScreenSlidePagerAdapter(this, DataUtil.getDummyImageData(requireContext()))
        viewPager.adapter = pagerAdapter
        TabLayoutMediator(binding.indicator, viewPager) { _, _ -> }.attach()

        recycleView = binding.recycleView
        recycleView.apply {
            this.layoutManager = LinearLayoutManager(requireContext())
            currentDataSet = getDummyData(1)
            this.adapter = ImageTextAdapter(requireContext(), currentDataSet)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        lifecycleScope.launch(Dispatchers.Main) {
            delay(10)
            viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                    currentDataSet = getDummyData(position + 1)
                    recycleView.adapter = ImageTextAdapter(requireContext(), currentDataSet)
                }
            })
        }

        binding.searchBar.apply {
            this.queryHint = getString(R.string.search)
            this.setOnCloseListener {
                recycleView.adapter = ImageTextAdapter(requireContext(), currentDataSet)
                binding.holderImageCaro.visibility = View.VISIBLE
                return@setOnCloseListener false
            }
            this.setOnSearchClickListener {
                binding.holderImageCaro.visibility = View.GONE
            }
            this.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
                androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {

                    val filterItem =
                        currentDataSet.filter { it.text.contains("$query", ignoreCase = true) }
                    if (filterItem.isEmpty()) {
                        Toast.makeText(requireContext(), "Not found", Toast.LENGTH_LONG)
                            .show()
                    } else {
                        recycleView.adapter = ImageTextAdapter(requireContext(), filterItem)
                    }
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    val filterItem =
                        currentDataSet.filter { it.text.contains("$query", ignoreCase = true) }
                    if (filterItem.isEmpty()) {
                        Toast.makeText(requireContext(), "Not found", Toast.LENGTH_LONG)
                            .show()
                    } else {
                        recycleView.adapter = ImageTextAdapter(requireContext(), filterItem)
                    }
                    return false
                }
            })
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}