package com.exercise.recetas.presentation.ui.recipes_detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.exercise.recetas.R
import com.exercise.recetas.databinding.FragmentRecipesDetailBinding
import com.exercise.recetas.presentation.model.Recipe
import com.exercise.recetas.presentation.viewmodel.RecipesDetailViewModel
import com.exercise.recetas.utils.BaseFragment
import com.exercise.recetas.utils.backNavigation

class RecipesDetailFragment : BaseFragment() {

    private lateinit var binding: FragmentRecipesDetailBinding
    private val viewModel: RecipesDetailViewModel by activityViewModels()
    private val args: RecipesDetailFragmentArgs by navArgs()

    override fun getMainView(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = FragmentRecipesDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setUpView() {
        initiateViewModel()
        initiateLiveData()
        initiateListener()
    }

    private fun initiateViewModel() {
        viewModel.loadRecipe(args.recipe)
    }

    private fun initiateLiveData() {
        viewModel.recipeEntity.observe(viewLifecycleOwner) {
            bindViewData(it)
        }
        viewModel.isFavorite.observe(viewLifecycleOwner) {
            if (it) {
                binding.favoriteIconImageView.setImageResource(R.drawable.ic_marcar_solido)
            } else {
                binding.favoriteIconImageView.setImageResource(R.drawable.ic_marcar_lineal)
            }
        }
    }

    private fun initiateListener() {
        binding.favoriteIconImageView.setOnClickListener {
            viewModel.toggleFavorite()
        }
        binding.imgIconBack.setOnClickListener {
            goToMainFragment()
        }
        backNavigation {
            goToMainFragment()
        }
    }

    private fun bindViewData(recipe: Recipe) {
        binding.recipeNameTextView.text = recipe.name
        binding.recipeDescriptionTextView.text = recipe.description
        Glide.with(this)
            .load(recipe.image)
            .transform(RoundedCorners(30))
            .placeholder(R.drawable.ic_loading)
            .into(binding.recipeImageView)
    }

    private fun goToMainFragment() {
        findNavController().navigate(
            RecipesDetailFragmentDirections.actionRecipesDetailFragmentToMainFragment(args.journey)
        )
    }

}