package com.dundxn.dishdazzle.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.dundxn.dishdazzle.data.response.MealsItem
import com.dundxn.dishdazzle.databinding.FragmentFavoriteBinding
import com.dundxn.dishdazzle.ui.ViewModelFactory
import com.dundxn.dishdazzle.ui.auth.AuthViewModel
import com.dundxn.dishdazzle.ui.recipe.RecipeAdapter


class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
    val binding get() = _binding

    private val viewModelFavorite by viewModels<FavoriteViewModel> {
        ViewModelFactory.getInstance(requireActivity().application)
    }

    private val viewModelAuth by viewModels<AuthViewModel> {
        ViewModelFactory.getInstance(requireActivity().application)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = GridLayoutManager(requireContext(), 2)
        binding?.rvFavorite?.layoutManager = layoutManager

        setFavorite()

    }

    private fun setFavorite() {
        viewModelAuth.getEmail().observe(viewLifecycleOwner) { email ->
            if (email.isNotEmpty()) {
                viewModelFavorite.getFavFoodByEmail(email).observe(viewLifecycleOwner) { favFood ->
                    val items = arrayListOf<MealsItem>()
                    favFood.map {
                        val item = MealsItem(
                            strMeal = it.title,
                            strMealThumb = it.uri,
                            strArea = it.region
                        )
                        items.add(item)
                    }
                    if (items.size != 0) {
                        val adapter = RecipeAdapter()
                        adapter.submitList(items)
                        binding?.rvFavorite?.adapter = adapter
                    } else {
                        //TODO set view if item null
                    }
                }
            }
        }

    }

}