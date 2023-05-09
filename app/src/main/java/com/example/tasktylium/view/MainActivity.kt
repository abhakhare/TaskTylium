package com.example.tasktylium.view

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.appcompat.app.AppCompatActivity


import androidx.lifecycle.ViewModelProvider

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tasktylium.R


import com.example.tasktylium.adapter.InstrumentPricesAdapter

import com.example.tasktylium.databinding.ActivityMainBinding
import com.example.tasktylium.model.InstrumentPricesDetails

import com.example.tasktylium.viewmodel.InstrumentViewModel



class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel: InstrumentViewModel
    private lateinit var dataAdapter: InstrumentPricesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

       prepareRecyclerView()
        viewModel = ViewModelProvider(this)[InstrumentViewModel::class.java]
        viewModel.getInstrumentPrices(binding)

        viewModel.observeInstrumentPricesLiveData().observe(this, {


            dataAdapter.submitList(it)
            dataAdapter.setPRList(it)
            dataAdapter.notifyDataSetChanged()

      })



    }



    private fun prepareRecyclerView() {
        dataAdapter = InstrumentPricesAdapter(InstrumentPricesAdapter.OnClickListener{item ->
            val bundle = Bundle()
            bundle.putString("name",item.second.name)
            bundle.putString("symbol",item.second.canonical_symbol)
            bundle.putString("id",item.second.id)
            bundle.putString("assetclass",item.second.assetClass)
            bundle.putString("priceincrement",item.second.priceIncrement)
            bundle.putString("qualtiincrement",item.second.quantityIncrement)
            val intent= Intent(applicationContext,InstrumentsDetailsActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        })
        binding.rvInstrument.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = dataAdapter
        }
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // below line is to get our inflater
        val inflater = menuInflater

        // inside inflater we are inflating our menu file.
        inflater.inflate(R.menu.menu_main, menu)

        // below line is to get our menu item.
        val searchItem: MenuItem = menu.findItem(R.id.actionSearch)

        // getting search view of our item.
        val searchView: SearchView = searchItem.getActionView() as SearchView

        // below line is to call set on query text listener method.
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(msg: String): Boolean {
                // inside on query text change method we are
                // calling a method to filter our recycler view.
              dataAdapter.filter.filter(msg)
                return false
            }
        })
        return true
    }


}









