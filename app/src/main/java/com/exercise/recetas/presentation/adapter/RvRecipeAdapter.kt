package com.exercise.recetas.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.exercise.recetas.R
import com.exercise.recetas.databinding.ItemRecipeBinding
import com.exercise.recetas.presentation.model.Recipe
import com.exercise.recetas.utils.BaseListAdapter

class RvRecipeAdapter(
    private val recipeListener: RecipeListener
): BaseListAdapter<Recipe, ItemRecipeBinding>(RecipeDiffCallback()) {

    class RecipeDiffCallback : DiffUtil.ItemCallback<Recipe>() {
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem == newItem
        }
    }

    override fun bind(binding: ItemRecipeBinding, item: Recipe, position: Int) {
        binding.recipeName.text = item.name
        binding.recipeDescription.text = item.description

        if (item.isFavorite) {
            binding.favoriteIcon.setImageResource(R.drawable.ic_marcar_solido)
        } else {
            binding.favoriteIcon.setImageResource(R.drawable.ic_marcar_lineal)
        }

        binding.favoriteIcon.setOnClickListener {
            recipeListener.onFavoriteToggle(item)
        }
        binding.root.setOnClickListener {
            recipeListener.onRecipeClick(item)
        }

        Glide.with(binding.recipeImage.context)
            .load(item.image)
            .transform(CircleCrop())
            .placeholder(R.drawable.ic_loading)
            .into(binding.recipeImage)
    }

    override fun createBinding(parent: ViewGroup): ItemRecipeBinding {
        val inflater = LayoutInflater.from(parent.context)
        return ItemRecipeBinding.inflate(inflater, parent, false)
    }

    interface RecipeListener {
        fun onFavoriteToggle(recipe: Recipe)
        fun onRecipeClick(recipe: Recipe)
    }

}