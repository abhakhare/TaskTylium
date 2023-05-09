package com.example.tasktylium.model

import java.sql.Timestamp


data class Prices(
        val symbol:String,
        val bid:Double,
        val ask:Double,
        val mid:Double,
        val bidq:Double,
        val askq:Double,
        val open:Float,
        val change:String,
      //  val timestamp:Timestamp
        )
