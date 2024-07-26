package com.dundxn.dishdazzle.ui.recipe

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dundxn.dishdazzle.R
import com.dundxn.dishdazzle.data.response.MealsItem
import com.dundxn.dishdazzle.databinding.ItemRecipeBinding
import com.dundxn.dishdazzle.ui.auth.AuthViewModel
import com.dundxn.dishdazzle.ui.favorite.FavoriteViewModel
import com.dundxn.dishdazzle.ui.recipedetail.RecipeDetailActivity

class RecipeAdapter() : ListAdapter<MealsItem, RecipeAdapter.MyViewHolder>(DIFF_CALLBACK) {
    class MyViewHolder(val binding: ItemRecipeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(recipe: MealsItem) {
            binding.tvRecipeTitle.text = recipe.strMeal
            binding.tvRecipeRegion.text = recipe.strArea
            Glide.with(binding.root)
                .load(recipe.strMealThumb)
                .into(binding.ivRecipeImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemRecipeBinding.inflate(LayoutInflater.from(parent.context))
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val recipe = getItem(position)
        holder.bind(recipe)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, RecipeDetailActivity::class.java)
            intent.putExtra(RecipeDetailActivity.RECIPE_ID, recipe.idMeal)
            intent.putExtra(RecipeDetailActivity.RECIPE_URI, recipe.strMealThumb)
            intent.putExtra(RecipeDetailActivity.RECIPE_NAME, recipe.strMeal)
            intent.putExtra(RecipeDetailActivity.RECIPE_REGION, recipe.strArea)
            holder.itemView.context.startActivity(intent)
        }

    }

    private fun isFav(isFav: Boolean, ivFav: ImageButton) {
        if (isFav) {
            ivFav.setImageResource(R.drawable.ic_favorite_on)
        } else {
            ivFav.setImageResource(R.drawable.ic_favorite_off)
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MealsItem>() {
            override fun areItemsTheSame(
                oldItem: MealsItem,
                newItem: MealsItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: MealsItem,
                newItem: MealsItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}