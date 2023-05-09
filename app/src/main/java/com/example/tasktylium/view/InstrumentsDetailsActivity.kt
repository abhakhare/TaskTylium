package com.example.tasktylium.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tasktylium.databinding.ActivityInstrumentdetailsBinding

import com.example.tasktylium.model.InstrumentPricesDetails

class InstrumentsDetailsActivity : AppCompatActivity() {
    private lateinit var binding : ActivityInstrumentdetailsBinding
    lateinit var name: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInstrumentdetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle =intent.extras
        if (bundle != null) {
           var name=bundle.get("name")
            var symbol=bundle.get("symbol")
            var id=bundle.get("id")
            var assetclass=bundle.get("assetclass")
            var pricesincrement=bundle.get("priceincrement")
            var qualtiincrement=bundle.get("qualtiincrement")
            binding.instrumentName.text= name.toString()
            binding.instrumentSymbol.text= symbol.toString()
            binding.instrumentId.text= id.toString()
            binding.assetclass.text= assetclass.toString()
            binding.priceincrement.text= pricesincrement.toString()
            binding.quantityincremen.text= qualtiincrement.toString()

        }

    }
}

