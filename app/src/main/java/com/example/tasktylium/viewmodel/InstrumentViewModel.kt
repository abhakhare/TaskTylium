package com.example.tasktylium.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tasktylium.databinding.ActivityMainBinding
import com.example.tasktylium.model.Data
import com.example.tasktylium.model.InstrumentPricesDetails
import com.example.tasktylium.model.Instruments
import com.example.tasktylium.model.Prices
import com.example.tasktylium.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InstrumentViewModel:ViewModel() {

    private val Instrumentslivedata: MutableLiveData<List<Pair<String, Instruments>>> = MutableLiveData<List<Pair<String, Instruments>>>()
    private val Priceslivedata: MutableLiveData<List<Pair<String, Prices>>> = MutableLiveData<List<Pair<String, Prices>>>()
    private val InstrumentPriceslivedata: MutableLiveData<List<Pair<String, InstrumentPricesDetails>>> = MutableLiveData<List<Pair<String, InstrumentPricesDetails>>>()

    fun getInstrumentPrices(binding: ActivityMainBinding) {
        binding.progressBar.visibility=View.VISIBLE
        binding.textView.visibility=View.VISIBLE
        binding.layoutProgress.visibility=View.VISIBLE
        binding.layoutRecycle.visibility=View.GONE
        RetrofitInstance.api.getAllPrices().enqueue(object : Callback<Data> {
            override fun onResponse(call: Call<Data>, response: Response<Data>) {
                if (response.body() != null) {
                    Priceslivedata.setValue(response.body()!!.prices.toList())
                    Instrumentslivedata.setValue(response.body()!!.instruments.toList())

                    val instrumentsvalue: Collection<Instruments> = response.body()!!.instruments.values
                    val pricesvalue: Collection<Prices> = response.body()!!.prices.values
                    var Detailslist: MutableMap<String, InstrumentPricesDetails> = mutableMapOf()
                    Detailslist.clear()

                    for (iteminstrument in instrumentsvalue) {
                        // body of loop
                        for (itemprices in pricesvalue) {
                            val symboleinstrument:String=iteminstrument.canonical_symbol
                            val symboleprices:String=itemprices.symbol

                            if (symboleinstrument.equals(symboleprices)) {
                                var instrumentpricedetais = InstrumentPricesDetails(iteminstrument._id,iteminstrument.id,iteminstrument.assetClass,iteminstrument.name,iteminstrument.canonical_symbol,iteminstrument.source,iteminstrument.source_symbol,iteminstrument.priceIncrement,iteminstrument.quantityIncrement,itemprices.symbol,itemprices.bid,itemprices.ask)

                                Detailslist.put(iteminstrument.source_symbol,instrumentpricedetais)


                            } else {

                            }

                        }
                    }
                    // sorted by name and symbole
                    val sorted_list = Detailslist.toList().sortedWith(compareBy( { it.second. canonical_symbol},{ it.second. name}))

                    InstrumentPriceslivedata.setValue(sorted_list)
                    binding.progressBar.visibility=View.GONE
                    binding.textView.visibility=View.GONE
                    binding.layoutProgress.visibility=View.GONE
                    binding.layoutRecycle.visibility=View.VISIBLE
                } else {
                    return
                }
            }

            override fun onFailure(call: Call<Data>, t: Throwable) {
                binding.progressBar.visibility=View.GONE
                binding.textView.visibility=View.GONE
                binding.layoutProgress.visibility=View.GONE
                binding.layoutRecycle.visibility=View.VISIBLE
                // Log.d("TAG",t.message.toString()+"Fail")
            }
        })
    }

  /*  fun observePricesLiveData(): LiveData<List<Pair<String, Prices>>> {
        return Priceslivedata
    }

    fun observeInstrumentLiveData(): LiveData<List<Pair<String, Instruments>>> {
        return Instrumentslivedata
    }*/
    fun observeInstrumentPricesLiveData(): MutableLiveData<List<Pair<String, InstrumentPricesDetails>>> {

        return InstrumentPriceslivedata
    }

}


















