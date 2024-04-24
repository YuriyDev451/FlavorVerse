package com.example.example



data class ExtendedIngredients (

  var id           : Int?              = null,
  var aisle        : String?           = null,
  var image        : String?           = null,
  var consistency  : String?           = null,
  var name         : String?           = null,
  var nameClean    : String?           = null,
  var original     : String?           = null,
  var originalName : String?           = null,
  var amount       : Int?              = null,
  var unit         : String?           = null,
  var meta         : ArrayList<String> = arrayListOf(),
  var measures     : Measures?         = Measures()

)