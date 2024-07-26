package com.dundxn.dishdazzle.ui.recipe

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import com.dundxn.dishdazzle.result.Result
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.dundxn.dishdazzle.R
import com.dundxn.dishdazzle.data.response.MealsItem
import com.dundxn.dishdazzle.databinding.FragmentRecipeBinding
import com.dundxn.dishdazzle.ui.ViewModelFactory
import com.dundxn.dishdazzle.ui.auth.AuthViewModel
import com.dundxn.dishdazzle.ui.favorite.FavoriteViewModel
import com.dundxn.dishdazzle.ui.setting.SettingActivity
import com.dundxn.dishdazzle.utils.showLoading
import com.dundxn.dishdazzle.utils.showToast


class RecipeFragment : Fragment() {
    private var _binding: FragmentRecipeBinding? = null

    private val binding get() = _binding!!

    private val viewModel by viewModels<RecipeViewModel> {
        ViewModelFactory.getInstance(requireActivity().application)
    }

    private val viewModelFav by viewModels<FavoriteViewModel> {
        ViewModelFactory.getInstance(requireActivity().application)
    }

    private val viewModelAuth by viewModels<AuthViewModel> {
        ViewModelFactory.getInstance(requireActivity().application)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRecipeBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.searchRecipe("a")

        recipeSearch()

        setupRecyclerView()

        observeViewModel()

        binding.fabSettings.setOnClickListener {
            startActivity(Intent(requireContext(), SettingActivity::class.java))
        }

        setName()
    }

    private fun recipeSearch() {
        binding.svSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val inputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(binding.svSearch.windowToken, 0)
                if (!query.isNullOrBlank()) {
                    viewModel.searchRecipe(query)

                }
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return true
            }

        })
    }

    private fun observeViewModel() {
        viewModel.resultRecipe.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> showLoading(true, binding.progressBar)

                is Result.Success -> {
                    showLoading(false, binding.progressBar)
                    val listRecipe = result.data.meals
                    setRecipe(listRecipe)


                }

                is Result.Error -> {
                    showLoading(false, binding.progressBar)
                    showToast(requireContext(), result.error)
                }
            }
        }
    }

    private fun setRecipe(recipe: List<MealsItem?>?) {
        val adapter = RecipeAdapter()
        adapter.submitList(recipe)
        binding.rvRecipe.adapter = adapter
    }

    private fun setupRecyclerView() {
        val layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvRecipe.layoutManager = layoutManager
    }

    private fun setName() {
        viewModel.getName().observe(viewLifecycleOwner) {
            val fullText = resources.getString(R.string.greetings)

            val startIndex = fullText.indexOf("John")
            val endIndex = startIndex + "John".length

            val greetingSpannable = SpannableStringBuilder(fullText)

            greetingSpannable.replace(startIndex, endIndex, it)

            binding.tvRecipe.text = greetingSpannable
        }
    }

}