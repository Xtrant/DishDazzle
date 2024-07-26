package com.dundxn.dishdazzle.ui.recipedetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dundxn.dishdazzle.data.response.Ingredients

class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    var detailStep: String = ""
    var ingredientsList: List<Ingredients> = emptyList()

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> {
                fragment = DetailStepsFragment()
                fragment.arguments = Bundle().apply {
                    putString(DetailStepsFragment.STEP, detailStep)
                }
            }

            1 -> {
                fragment = DetailIngredientsFragment()
                fragment.arguments = Bundle().apply {
                    putParcelableArrayList(
                        DetailIngredientsFragment.LIST_INGREDIENT,
                        ArrayList(ingredientsList)
                    )
                }
            }
        }

        return fragment ?: throw IllegalArgumentException("Invalid position $position")
    }
}
