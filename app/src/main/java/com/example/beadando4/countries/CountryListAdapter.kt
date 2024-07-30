package com.example.beadando4.countries

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.beadando4.databinding.CountryListItemBinding

class CountryListAdapter :
    ListAdapter<Country, CountryListAdapter.CountryViewHolder>(DiffCallback) {
    class CountryViewHolder(private val binding: CountryListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(country: Country) {
            binding.itemCountryName.text = country.name.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder(
            CountryListItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Country>() {
            override fun areItemsTheSame(oldGame: Country, newGame: Country): Boolean {
                return oldGame === newGame
            }

            override fun areContentsTheSame(oldGame: Country, newGame: Country): Boolean {
                return oldGame.name == newGame.name
            }
        }
    }
}