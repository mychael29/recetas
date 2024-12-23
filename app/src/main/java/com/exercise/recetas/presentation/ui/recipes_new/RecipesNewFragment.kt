package com.exercise.recetas.presentation.ui.recipes_new

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.exercise.recetas.databinding.FragmentRecipesNewBinding
import com.exercise.recetas.presentation.adapter.RvRecipeAdapter
import com.exercise.recetas.presentation.model.Recipe
import com.exercise.recetas.presentation.ui.MainFragmentDirections
import com.exercise.recetas.presentation.viewmodel.RecipesViewModel
import com.exercise.recetas.utils.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipesNewFragment : BaseFragment(), RvRecipeAdapter.RecipeListener {

    private lateinit var binding: FragmentRecipesNewBinding

    private val recipeViewModel: RecipesViewModel by activityViewModels()

    private val rvRecipeAdapter: RvRecipeAdapter by lazy {
        RvRecipeAdapter(this)
    }

    override fun getMainView(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = FragmentRecipesNewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setUpView() {
        initiateViewModel()
        initiateLiveData()
        initiateView()
    }

    private fun initiateViewModel() {
        recipeViewModel.fetchRecipes()
    }

    private fun initiateLiveData() {
        recipeViewModel.recipesNew.observe(viewLifecycleOwner) {
            rvRecipeAdapter.submitList(it)
        }
        recipeViewModel.error.observe(viewLifecycleOwner) {
            binding.txtErrorData.text = it.message
            binding.txtErrorData.isVisible = true
        }
        recipeViewModel.progress.observe(viewLifecycleOwner) {
            binding.progress.isVisible = it
        }
    }

    private fun initiateView() {
        binding.rvRecipes.adapter = rvRecipeAdapter
    }

    override fun onFavoriteToggle(recipe: Recipe) {
        recipeViewModel.updateRecipeFavorite(recipe)
    }

    override fun onRecipeClick(recipe: Recipe) {
        findNavController().navigate(
            MainFragmentDirections.actionMainFragmentToRecipesDetailFragment(recipe, "new")
        )
    }
}