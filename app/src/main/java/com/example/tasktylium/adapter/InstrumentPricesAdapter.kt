package com.example.tasktylium.adapter

import android.content.Intent
import android.view.LayoutInflater

import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tasktylium.databinding.InstrumentItemBinding


import com.example.tasktylium.model.InstrumentPricesDetails
import com.example.tasktylium.view.MainActivity

class InstrumentPricesAdapter(private val onClickListener: OnClickListener) : ListAdapter<Pair<String, InstrumentPricesDetails>, RecyclerView.ViewHolder>(
    DiffCallback()
), Filterable {



    lateinit var mFilteredList: List<Pair<String, InstrumentPricesDetails>>
    lateinit var List: List<Pair<String, InstrumentPricesDetails>>
    fun setPRList(prLst: List<Pair<String, InstrumentPricesDetails>>){
        this.List = prLst as List<Pair<String, InstrumentPricesDetails>>
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  RecyclerView.ViewHolder {
        val binding = InstrumentItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return InstrumentPricesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val PriceInstrument: Pair<String, InstrumentPricesDetails> = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(PriceInstrument)
        }
        (holder as InstrumentPricesViewHolder).bind(PriceInstrument)
    }




    class InstrumentPricesViewHolder(var binding: InstrumentItemBinding) : RecyclerView.ViewHolder(
        binding.root
    ){
        fun bind(PriceInstrument: Pair<String, InstrumentPricesDetails>) {
            //val InstrumentSymbole = PriceInstrument.first
            val instrumentList = PriceInstrument.second

            binding.textname.text = instrumentList.name.toString()
            binding.textsymbole.text = instrumentList.canonical_symbol.toString()
            binding.textassetclass.text = instrumentList.assetClass.toString()
            binding.textpricesbid.text = instrumentList.bid.toString()
            binding.textpricesask.text = instrumentList.ask.toString()
        }
    }
//Filter by name and symbole
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): Filter.FilterResults {

                val charString = charSequence.toString()

                if (charString.isEmpty()) {
                    mFilteredList = List
                } else {

                    val filteredList = List
                            .filter { (it.second.name?.toLowerCase()?.contains(charString)!!)||( it.second.canonical_symbol?.toLowerCase()?.contains(charString)!!)}
                            .toMutableList()

                    mFilteredList = filteredList
                }

                val filterResults = Filter.FilterResults()
                filterResults.values = mFilteredList
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: Filter.FilterResults) {
                submitList(filterResults.values as List<Pair<String, InstrumentPricesDetails>>)
                notifyDataSetChanged()
            }
        }
    }
    class OnClickListener(val clickListener: (item: Pair<String, InstrumentPricesDetails>) -> Unit) {
        fun onClick(item: Pair<String, InstrumentPricesDetails>) = clickListener(item)
    }



}






class DiffCallback : DiffUtil.ItemCallback<Pair<String, InstrumentPricesDetails>>() {
    override fun areItemsTheSame(
        oldItem: Pair<String, InstrumentPricesDetails>,
        newItem: Pair<String, InstrumentPricesDetails>
    ): Boolean {
        return oldItem.first == newItem.first
    }

    override fun areContentsTheSame(
        oldItem: Pair<String, InstrumentPricesDetails>,
        newItem: Pair<String, InstrumentPricesDetails>
    ): Boolean {
        return oldItem.first == newItem.first
    }



}