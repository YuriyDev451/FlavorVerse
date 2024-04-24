package com.example.example


data class Steps(

    var number: Int? = null,
    var step: String? = null,
    var ingredients: ArrayList<String> = arrayListOf(),
    var equipment: ArrayList<Equipment> = arrayListOf()

)