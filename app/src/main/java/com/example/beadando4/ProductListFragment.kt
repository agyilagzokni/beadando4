package com.example.beadando4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.beadando4.databinding.ProductListFragmentBinding

class ProductListFragment : Fragment() {
    private val viewModel: ProductViewModel by activityViewModels {
        ProductViewModelFactory(
            (activity?.application as BeadandoApplication).database.productDao()
        )
    }

    private var _binding: ProductListFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ProductListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ProductListAdapter {
            val action =
                ProductListFragmentDirections.actionProductListFragmentToProductDetailFragment(it.id)
            this.findNavController().navigate(action)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
        binding.recyclerView.adapter = adapter

        viewModel.allProducts.observe(this.viewLifecycleOwner) { products ->
            products.let {
                adapter.submitList(it)
            }
        }
        binding.floatingActionButton.setOnClickListener {
            val action =
                ProductListFragmentDirections.actionProductListFragmentToAddProductFragment()
            this.findNavController().navigate(action)
        }

        binding.goToCountriesBut.setOnClickListener {
            val action =
                ProductListFragmentDirections.actionProductListFragmentToCountryListFragment()
            this.findNavController().navigate(action)
        }
    }
}