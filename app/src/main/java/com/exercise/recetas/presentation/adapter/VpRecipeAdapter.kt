package com.exercise.recetas.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.exercise.recetas.presentation.ui.recipes_favorite.RecipesFavoriteFragment
import com.exercise.recetas.presentation.ui.recipes_new.RecipesNewFragment

class VpRecipeAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> RecipesNewFragment()
            1 -> RecipesFavoriteFragment()
            else -> throw IllegalStateException("Error position: $position")
        }
    }
}