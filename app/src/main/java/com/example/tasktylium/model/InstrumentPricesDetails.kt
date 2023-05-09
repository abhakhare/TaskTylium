package com.example.tasktylium.model

data class InstrumentPricesDetails (

        val _id:String,
        val id:String,
        val assetClass:String,
        val name:String,
        val canonical_symbol:String,
        val source:String,
        val source_symbol:String,
        val priceIncrement:String,
        val quantityIncrement:String ,
        val symbol:String,
        val bid:Double,
        val ask:Double,
)
