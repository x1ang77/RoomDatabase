package com.joel.todolistfragments.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import com.joel.todolistfragments.R
import com.joel.todolistfragments.adapters.ImageSliderAdapter
import com.joel.todolistfragments.databinding.FragmentImageGalleryBinding
import com.joel.todolistfragments.data.model.Image
import kotlinx.coroutines.delay

class ImageGalleryFragment : Fragment() {
    private lateinit var binding: FragmentImageGalleryBinding
    private lateinit var adapter: ImageSliderAdapter

    val items: List<Image> = listOf(
        Image(R.drawable.image0),
        Image(R.drawable.image1),
        Image(R.drawable.image2),
        Image(R.drawable.image3),
        Image(R.drawable.image4),
        Image(R.drawable.image5),
        Image(R.drawable.image6),
        Image(R.drawable.image7),
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImageGalleryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener {
            NavHostFragment.findNavController(this).popBackStack()
        }

        adapter = ImageSliderAdapter(items.toMutableList())

        binding.vpImages.let { viewPager ->
            viewPager.adapter = adapter
            viewPager.offscreenPageLimit = 3
            viewPager.getChildAt(0)?.overScrollMode = RecyclerView.OVER_SCROLL_NEVER

            val composePageTransformer = CompositePageTransformer()
            composePageTransformer.addTransformer { page, position ->
                val r = 1 - kotlin.math.abs(position)
                page.scaleY = 0.80f + r * 0.20f
            }
            viewPager.setPageTransformer(composePageTransformer)

            lifecycleScope.launchWhenResumed {
                var position = 0
                while (true) {
                    delay(3000)
                    position = (position + 1) % 8
                    viewPager.setCurrentItem(position, true)
                }
            }
        }
    }
}