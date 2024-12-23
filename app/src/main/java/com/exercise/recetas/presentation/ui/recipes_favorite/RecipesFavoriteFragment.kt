package com.exercise.recetas.presentation.ui.recipes_favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.exercise.recetas.databinding.FragmentRecipesFavoriteBinding
import com.exercise.recetas.presentation.adapter.RvRecipeAdapter
import com.exercise.recetas.presentation.model.Recipe
import com.exercise.recetas.presentation.ui.MainFragmentDirections
import com.exercise.recetas.presentation.viewmodel.RecipesViewModel
import com.exercise.recetas.utils.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipesFavoriteFragment : BaseFragment(), RvRecipeAdapter.RecipeListener {

    private lateinit var binding: FragmentRecipesFavoriteBinding

    private val recipeViewModel: RecipesViewModel by activityViewModels()

    private val rvRecipeAdapter: RvRecipeAdapter by lazy {
        RvRecipeAdapter(this)
    }

    override fun getMainView(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = FragmentRecipesFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setUpView() {
        initiateViewModel()
        initiateLiveData()
        initiateView()
    }

    private fun initiateViewModel() {
        recipeViewModel.getRecipesFavorite()
    }

    private fun initiateLiveData() {
        recipeViewModel.recipesFav.observe(viewLifecycleOwner) {
            rvRecipeAdapter.submitList(it)
            bindViewData(it)
        }
    }

    private fun initiateView() {
        binding.rvRecipes.adapter = rvRecipeAdapter
    }

    private fun bindViewData(recipes: List<Recipe>) {
        binding.txtNoData.isVisible = recipes.isEmpty()

    }
    override fun onFavoriteToggle(recipe: Recipe) {
        recipeViewModel.updateRecipeFavorite(recipe)
    }

    override fun onRecipeClick(recipe: Recipe) {
        findNavController().navigate(
            MainFragmentDirections.actionMainFragmentToRecipesDetailFragment(recipe, "favorite")
        )
    }

}