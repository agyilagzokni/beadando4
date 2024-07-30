package com.example.beadando4

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.beadando4.data.Product
import com.example.beadando4.databinding.ProductListItemBinding

class ProductListAdapter(private val onProductClicked: (Product) -> Unit):
    ListAdapter<Product, ProductListAdapter.ProductViewHolder>(DiffCallback){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            ProductListItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener {
            onProductClicked(current)
        }
        holder.bind(current)
    }

    class ProductViewHolder(private var binding: ProductListItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(product: Product){
            binding.itemProductName.text = product.name
            binding.itemProductPrice.text = product.price.toString()
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(oldGame: Product, newGame: Product): Boolean {
                return oldGame === newGame
            }

            override fun areContentsTheSame(oldGame: Product, newGame: Product): Boolean {
                return oldGame.name == newGame.name
            }
        }
    }

}