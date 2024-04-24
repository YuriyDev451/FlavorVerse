package com.example.example



data class Equipment (

  var id            : Int?         = null,
  var name          : String?      = null,
  var localizedName : String?      = null,
  var image         : String?      = null,
  var temperature   : Temperature? = Temperature()

)