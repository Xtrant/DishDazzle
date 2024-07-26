package com.dundxn.dishdazzle.ui.recipedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dundxn.dishdazzle.data.response.Ingredients
import com.dundxn.dishdazzle.databinding.FragmentDetailIngredientsBinding
import com.dundxn.dishdazzle.ui.ViewModelFactory
import com.dundxn.dishdazzle.ui.shoppinglist.ShoppingListViewModel

class DetailIngredientsFragment : Fragment() {
    private var _binding: FragmentDetailIngredientsBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<ShoppingListViewModel> {
        ViewModelFactory.getInstance(
            requireActivity().application
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailIngredientsBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        @Suppress("DEPRECATION")
        val listIngredients = arguments?.getParcelableArrayList<Ingredients>(LIST_INGREDIENT)

        setupRecyclerView()
        listIngredients?.let { setIngredient(it) }
    }

    private fun setIngredient(listIngredients: List<Ingredients>) {
        val adapter = DetailIngredientsAdapter { list ->
            if (list.isList) {
                viewModel.deleteListShopping(list)
            } else viewModel.insertListShopping(list)
        }
        adapter.submitList(listIngredients)
        binding.rvDetailIngredients.adapter = adapter
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvDetailIngredients.layoutManager = layoutManager
    }

    companion object {
        const val LIST_INGREDIENT = "list_ingredient"

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
