package com.dundxn.dishdazzle.ui.recipedetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dundxn.dishdazzle.R
import com.dundxn.dishdazzle.data.response.Ingredients
import com.dundxn.dishdazzle.data.room.entity.ListShopping
import com.dundxn.dishdazzle.databinding.ItemDetailIngredientsBinding

class DetailIngredientsAdapter(private val onListClick: (ListShopping) -> Unit) :
    ListAdapter<Ingredients, DetailIngredientsAdapter.MyViewHolder>(DIFF_CALLBACK) {
    class MyViewHolder( val binding: ItemDetailIngredientsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(ingredient: Ingredients) {
            binding.tvDetailIngredientsName.text = ingredient.name
            binding.tvDetailIngredientsMeasure.text = ingredient.amount
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemDetailIngredientsBinding.inflate(LayoutInflater.from(parent.context))
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val ingredients = getItem(position)
        holder.bind(ingredients)

        val ivSaveList = holder.binding.btnDetailShopping
        val listShopping = ListShopping()
        if(listShopping.isList) {
            ivSaveList.setImageDrawable(ContextCompat.getDrawable(ivSaveList.context, R.drawable.ic_delete))
        } else {
            ivSaveList.setImageDrawable(ContextCompat.getDrawable(ivSaveList.context, R.drawable.ic_add))
        }

        ivSaveList.setOnClickListener {  }
        onListClick(listShopping)

    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Ingredients>() {
            override fun areItemsTheSame(
                oldItem: Ingredients,
                newItem: Ingredients
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: Ingredients,
                newItem: Ingredients
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}