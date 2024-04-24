package com.example.example


data class Recipes(

    var vegetarian: Boolean? = null,
    var vegan: Boolean? = null,
    var glutenFree: Boolean? = null,
    var dairyFree: Boolean? = null,
    var veryHealthy: Boolean? = null,
    var cheap: Boolean? = null,
    var veryPopular: Boolean? = null,
    var sustainable: Boolean? = null,
    var lowFodmap: Boolean? = null,
    var weightWatcherSmartPoints: Int? = null,
    var gaps: String? = null,
    var preparationMinutes: Int? = null,
    var cookingMinutes: Int? = null,
    var aggregateLikes: Int? = null,
    var healthScore: Int? = null,
    var creditsText: String? = null,
    var license: String? = null,
    var sourceName: String? = null,
    var pricePerServing: Double? = null,
    var extendedIngredients: ArrayList<ExtendedIngredients> = arrayListOf(),
    var id: Int? = null,
    var title: String? = null,
    var readyInMinutes: Int? = null,
    var servings: Int? = null,
    var sourceUrl: String? = null,
    var image: String? = null,
    var imageType: String? = null,
    var summary: String? = null,
    var cuisines: ArrayList<String> = arrayListOf(),
    var dishTypes: ArrayList<String> = arrayListOf(),
    var diets: ArrayList<String> = arrayListOf(),
    var occasions: ArrayList<String> = arrayListOf(),
    var instructions: String? = null,
    var analyzedInstructions: ArrayList<AnalyzedInstructions> = arrayListOf(),
    var originalId: String? = null,
    var spoonacularScore: Double? = null,
    var spoonacularSourceUrl: String? = null

)