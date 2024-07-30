package com.example.beadando4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.beadando4.data.Product
import com.example.beadando4.databinding.ProductDetailFragmentBinding

class ProductDetailFragment : Fragment() {
    private val navigationArgs: ProductDetailFragmentArgs by navArgs()
    lateinit var product: Product

    private val viewModel: ProductViewModel by activityViewModels {
        ProductViewModelFactory(
            (activity?.application as BeadandoApplication).database.productDao()
        )
    }

    private var _binding: ProductDetailFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ProductDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun bind(product: Product){
        binding.apply {
            detailName.text = product.name
            detailPrice.text = product.price.toString()
            detailKcal.text = product.kcal.toString()
            detailCarbs.text = product.carbs.toString()
            detailFat.text = product.fat.toString()
        }
    }

    private fun deleteItem(){
        viewModel.deleteProduct(product)
        findNavController().navigateUp()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = navigationArgs.productId
        // Retrieve the item details using the itemId.
        // Attach an observer on the data (instead of polling for changes) and only update the
        // the UI when the data actually changes.
        viewModel.retrieveProduct(id).observe(this.viewLifecycleOwner) { selectedItem ->
            product = selectedItem
            bind(product)
        }
        binding.removeProduct.setOnClickListener {
            deleteItem()
        }
    }

    /**
     * Called when fragment is destroyed.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}