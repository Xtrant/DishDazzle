package com.dundxn.dishdazzle.ui.recipedetail

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.dundxn.dishdazzle.R
import com.dundxn.dishdazzle.data.response.Ingredients
import com.dundxn.dishdazzle.data.response.MealsItem
import com.dundxn.dishdazzle.data.room.entity.FavoriteFood
import com.dundxn.dishdazzle.data.room.entity.ListShopping
import com.dundxn.dishdazzle.databinding.ActivityRecipeDetailBinding
import com.dundxn.dishdazzle.result.Result
import com.dundxn.dishdazzle.ui.ViewModelFactory
import com.dundxn.dishdazzle.ui.auth.AuthViewModel
import com.dundxn.dishdazzle.ui.favorite.FavoriteViewModel
import com.dundxn.dishdazzle.ui.recipe.RecipeViewModel
import com.dundxn.dishdazzle.ui.shoppinglist.ShoppingListViewModel
import com.dundxn.dishdazzle.utils.showLoading
import com.dundxn.dishdazzle.utils.showToast
import com.google.android.material.tabs.TabLayoutMediator

class RecipeDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecipeDetailBinding
    private val viewModelRecipe by viewModels<RecipeViewModel> {
        ViewModelFactory.getInstance(this.application)
    }

    private val viewModelAuth by viewModels<AuthViewModel> {
        ViewModelFactory.getInstance(this.application)
    }

    private val viewModelFavorite by viewModels<FavoriteViewModel> {
        ViewModelFactory.getInstance(this.application)
    }

    private val viewModelListShopping by viewModels<ShoppingListViewModel> {
        ViewModelFactory.getInstance(this.application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRecipeDetailBinding.inflate(layoutInflater)

        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val idMeal = intent.getStringExtra(RECIPE_ID)

        if (idMeal != null) {
            viewModelRecipe.detailRecipe(idMeal)
        }

        observeViewModel()

        viewModelAuth.getEmail().observe(this@RecipeDetailActivity) { email ->
            if (email.isNotEmpty()) {

                val foodRegion = intent.getStringExtra(RECIPE_REGION).toString()
                val foodName = intent.getStringExtra(RECIPE_NAME).toString()
                val foodImg = intent.getStringExtra(RECIPE_URI).toString()


                var favFood: FavoriteFood = FavoriteFood().apply {
                    this.uri = foodImg
                    this.title = foodName
                    this.region = foodRegion
                    this.userEmail = email
                    this.favFoodId = idMeal.toString()
                }

                viewModelFavorite.getFavFoodByEmail(email).observe(this) {
                    it.forEach { fav ->
                        if (fav.title == foodName) {
                            viewModelFavorite.isFav(true)
                            favFood = fav
                        }
                    }
                }


                viewModelFavorite.isFav.observe(this) {
                    isFav(it)
                }

                binding.fabDetailFavorite.setOnClickListener {
                    favFood.let { viewModelFavorite.updateFavFood(it, this) }
                }
            }

            supportActionBar?.hide()
        }
    }

    private fun setDetailMeal(recipe: MealsItem) {
        viewModelAuth.getEmail().observe(this) { email ->
            if (email.isNotEmpty()) {
                binding.tvDetailTitle.text = recipe.strMeal
                binding.tvDetailRegion.text = recipe.strArea
                Glide.with(this)
                    .load(recipe.strMealThumb)
                    .into(binding.ivDetailImage)

                val ingredientsList = recipe.toIngredientsList()

                val listShopping: ListShopping = ListShopping().apply {
                    val ingredients = Ingredients()
                    this.userEmail = email
                    this.name = ingredients.name.toString()
                    this.amount = ingredients.amount.toString()
                }



                recipe.strInstructions?.let { setupViewPager(it, ingredientsList) }
            }
        }
    }

    private fun setupViewPager(detailStep: String, ingredientsList: List<Ingredients>) {
        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        binding.viewPager.adapter = sectionsPagerAdapter

        sectionsPagerAdapter.detailStep = detailStep
        sectionsPagerAdapter.ingredientsList = ingredientsList

        TabLayoutMediator(binding.tlDetailTabs, binding.viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
    }

    private fun observeViewModel() {
        viewModelRecipe.resultRecipe.observe(this) { result ->
            when (result) {
                is Result.Loading -> showLoading(true, binding.progressBar)

                is Result.Success -> {
                    showLoading(false, binding.progressBar)
                    val listRecipe = result.data.meals
                    if (listRecipe != null) {
                        listRecipe.firstOrNull()?.let {
                            setDetailMeal(it)
                        }
                    }
                }

                is Result.Error -> {
                    showLoading(false, binding.progressBar)
                    showToast(this, result.error)
                }
            }
        }
    }

    private fun isFav(isWish: Boolean) {
        if (isWish) {
            binding.fabDetailFavorite.setImageResource(R.drawable.ic_favorite_on)
        } else binding.fabDetailFavorite.setImageResource(R.drawable.ic_favorite_off)
    }

    companion object {
        const val RECIPE_ID = "recipe_id"
        const val RECIPE_NAME = "recipe_name"
        const val RECIPE_URI = "recipe_uri"
        const val RECIPE_REGION = "recipe_region"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.food_steps_title,
            R.string.food_ingredients_title
        )

    }
}