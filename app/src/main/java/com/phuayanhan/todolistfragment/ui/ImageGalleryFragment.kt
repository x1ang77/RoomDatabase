package com.phuayanhan.todolistfragment.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import com.phuayanhan.todolistfragment.R
import com.phuayanhan.todolistfragment.adapter.ImageSliderAdapter
import com.phuayanhan.todolistfragment.databinding.FragmentImageGalleryBinding
import com.phuayanhan.todolistfragment.model.Image
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class ImageGalleryFragment : Fragment() {
    private lateinit var binding:FragmentImageGalleryBinding
    private lateinit var adapter: ImageSliderAdapter

    val items:List<Image> = listOf(
        Image(R.drawable.pic1),
        Image(R.drawable.pic2),
        Image(R.drawable.pic3),
        Image(R.drawable.pic4),
        Image(R.drawable.pic5),
        Image(R.drawable.pic6),
        Image(R.drawable.pic7),
        Image(R.drawable.pic8),
        Image(R.drawable.pic9),
        Image(R.drawable.pic10),

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
            val bundle = Bundle()
            bundle.putString("greeting","Hello from image gallery")
            setFragmentResult("from_image_gallery",bundle)
            NavHostFragment.findNavController(this).popBackStack()
        }

        adapter = ImageSliderAdapter(items)

        binding.vpImages.let { viewPager ->
            viewPager.adapter =adapter
            viewPager.offscreenPageLimit=3
            viewPager.getChildAt(0)?.overScrollMode = RecyclerView.OVER_SCROLL_NEVER

            val composePageTransformer = CompositePageTransformer()
            composePageTransformer.addTransformer{ page, position ->
                val r = 1 - kotlin.math.abs(position)
                page.scaleY = 0.80f + r * 0.20f
            }

            viewPager.setPageTransformer(composePageTransformer)
//            GlobalScope.launch {
//                var position = 0
//                while (true){
//                    delay(5000)
//                    position = (position+1)%11
//                    viewPager.setCurrentItem(position,true)
//                }
//            }
            lifecycleScope.launchWhenResumed {
                var position = 0
                while (true){
                    delay(5000)
                    position = (position+1)%11
                    viewPager.setCurrentItem(position,true)
                }
            }
        }
    }

}