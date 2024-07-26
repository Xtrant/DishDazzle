package com.dundxn.dishdazzle.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class SearchResponse(

    @field:SerializedName("meals")
    val meals: List<MealsItem?>? = null
)

@Parcelize
data class Ingredients(
    @SerializedName("name")
    val name: String? = null,

    @SerializedName("amount")
    val amount: String? = null

): Parcelable

data class MealsItem(

    @field:SerializedName("strImageSource")
    val strImageSource: Any? = null,

    @field:SerializedName("strIngredient10")
    val strIngredient10: String? = null,

    @field:SerializedName("strIngredient12")
    val strIngredient12: String? = null,

    @field:SerializedName("strIngredient11")
    val strIngredient11: String? = null,

    @field:SerializedName("strIngredient14")
    val strIngredient14: String? = null,

    @field:SerializedName("strCategory")
    val strCategory: String? = null,

    @field:SerializedName("strIngredient13")
    val strIngredient13: String? = null,

    @field:SerializedName("strIngredient16")
    val strIngredient16: String? = null,

    @field:SerializedName("strIngredient15")
    val strIngredient15: String? = null,

    @field:SerializedName("strIngredient18")
    val strIngredient18: String? = null,

    @field:SerializedName("strIngredient17")
    val strIngredient17: String? = null,

    @field:SerializedName("strArea")
    val strArea: String? = null,

    @field:SerializedName("strCreativeCommonsConfirmed")
    val strCreativeCommonsConfirmed: Any? = null,

    @field:SerializedName("strIngredient19")
    val strIngredient19: String? = null,

    @field:SerializedName("strTags")
    val strTags: Any? = null,

    @field:SerializedName("idMeal")
    val idMeal: String? = null,

    @field:SerializedName("strInstructions")
    val strInstructions: String? = null,

    @field:SerializedName("strIngredient1")
    val strIngredient1: String? = null,

    @field:SerializedName("strIngredient3")
    val strIngredient3: String? = null,

    @field:SerializedName("strIngredient2")
    val strIngredient2: String? = null,

    @field:SerializedName("strIngredient20")
    val strIngredient20: String? = null,

    @field:SerializedName("strIngredient5")
    val strIngredient5: String? = null,

    @field:SerializedName("strIngredient4")
    val strIngredient4: String? = null,

    @field:SerializedName("strIngredient7")
    val strIngredient7: String? = null,

    @field:SerializedName("strIngredient6")
    val strIngredient6: String? = null,

    @field:SerializedName("strIngredient9")
    val strIngredient9: String? = null,

    @field:SerializedName("strIngredient8")
    val strIngredient8: String? = null,

    @field:SerializedName("strMealThumb")
    val strMealThumb: String? = null,

    @field:SerializedName("strMeasure20")
    val strMeasure20: String? = null,

    @field:SerializedName("strYoutube")
    val strYoutube: String? = null,

    @field:SerializedName("strMeal")
    val strMeal: String? = null,

    @field:SerializedName("strMeasure12")
    val strMeasure12: String? = null,

    @field:SerializedName("strMeasure13")
    val strMeasure13: String? = null,

    @field:SerializedName("strMeasure10")
    val strMeasure10: String? = null,

    @field:SerializedName("strMeasure11")
    val strMeasure11: String? = null,

    @field:SerializedName("dateModified")
    val dateModified: Any? = null,

    @field:SerializedName("strDrinkAlternate")
    val strDrinkAlternate: Any? = null,

    @field:SerializedName("strSource")
    val strSource: String? = null,

    @field:SerializedName("strMeasure9")
    val strMeasure9: String? = null,

    @field:SerializedName("strMeasure7")
    val strMeasure7: String? = null,

    @field:SerializedName("strMeasure8")
    val strMeasure8: String? = null,

    @field:SerializedName("strMeasure5")
    val strMeasure5: String? = null,

    @field:SerializedName("strMeasure6")
    val strMeasure6: String? = null,

    @field:SerializedName("strMeasure3")
    val strMeasure3: String? = null,

    @field:SerializedName("strMeasure4")
    val strMeasure4: String? = null,

    @field:SerializedName("strMeasure1")
    val strMeasure1: String? = null,

    @field:SerializedName("strMeasure18")
    val strMeasure18: String? = null,

    @field:SerializedName("strMeasure2")
    val strMeasure2: String? = null,

    @field:SerializedName("strMeasure19")
    val strMeasure19: String? = null,

    @field:SerializedName("strMeasure16")
    val strMeasure16: String? = null,

    @field:SerializedName("strMeasure17")
    val strMeasure17: String? = null,

    @field:SerializedName("strMeasure14")
    val strMeasure14: String? = null,

    @field:SerializedName("strMeasure15")
    val strMeasure15: String? = null,

) {
    fun toIngredientsList(): List<Ingredients> {
        val ingredientsList = mutableListOf<Ingredients>()

        for (i in 1..20) {
            val ingredientName = getIngredientName(i)
            val ingredientAmount = getIngredientMeasure(i)

            if (!ingredientName.isNullOrBlank() && !ingredientAmount.isNullOrBlank()) {
                ingredientsList.add(Ingredients(ingredientName, ingredientAmount))
            }
        }

        return ingredientsList
    }

    private fun getIngredientName(index: Int): String? {
        return when (index) {
            1 -> strIngredient1
            2 -> strIngredient2
            3 -> strIngredient3
            4 -> strIngredient4
            5 -> strIngredient5
            6 -> strIngredient6
            7 -> strIngredient7
            8 -> strIngredient8
            9 -> strIngredient9
            10 -> strIngredient10
            11 -> strIngredient11
            12 -> strIngredient12
            13 -> strIngredient13
            14 -> strIngredient14
            15 -> strIngredient15
            16 -> strIngredient16
            17 -> strIngredient17
            18 -> strIngredient18
            19 -> strIngredient19
            20 -> strIngredient20
            else -> null
        }
    }

    private fun getIngredientMeasure(index: Int): String? {
        return when (index) {
            1 -> strMeasure1
            2 -> strMeasure2
            3 -> strMeasure3
            4 -> strMeasure4
            5 -> strMeasure5
            6 -> strMeasure6
            7 -> strMeasure7
            8 -> strMeasure8
            9 -> strMeasure9
            10 -> strMeasure10
            11 -> strMeasure11
            12 -> strMeasure12
            13 -> strMeasure13
            14 -> strMeasure14
            15 -> strMeasure15
            16 -> strMeasure16
            17 -> strMeasure17
            18 -> strMeasure18
            19 -> strMeasure19
            20 -> strMeasure20
            else -> null
        }
    }
}


