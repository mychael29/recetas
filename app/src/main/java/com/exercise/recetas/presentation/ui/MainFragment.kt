package com.exercise.recetas.presentation.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.exercise.recetas.R
import com.exercise.recetas.databinding.FragmentMainBinding
import com.exercise.recetas.presentation.adapter.VpRecipeAdapter
import com.exercise.recetas.utils.BaseFragment
import com.exercise.recetas.utils.backNavigation

class MainFragment: BaseFragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var vpRecipeAdapter: VpRecipeAdapter
    private val args: MainFragmentArgs by navArgs()

    override fun getMainView(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setUpView() {
        vpRecipeAdapter = VpRecipeAdapter(this)
        binding.viewPager.adapter = vpRecipeAdapter

        binding.navView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.recipesNewFragment -> binding.viewPager.currentItem = 0
                R.id.recipesFavoriteFragment -> binding.viewPager.currentItem = 1
            }
            true
        }

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> binding.navView.selectedItemId = R.id.recipesNewFragment
                    1 -> binding.navView.selectedItemId = R.id.recipesFavoriteFragment
                }
            }
        })

        when (args.journey) {
            "new" -> {
                binding.viewPager.post {
                    binding.viewPager.currentItem = 0
                }
            }
            "favorite" -> {
                binding.viewPager.post {
                    binding.viewPager.currentItem = 1
                }
            }
        }

        backNavigation {
            activity?.finish()
        }

    }
}