package com.example.beadando4

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.beadando4.databinding.AddProductFragmentBinding
import java.lang.Double.parseDouble
import java.lang.Integer.parseInt

class AddProductFragment : Fragment() {

    private val viewModel: ProductViewModel by activityViewModels {
        ProductViewModelFactory(
            (activity?.application as BeadandoApplication).database.productDao()
        )
    }

    private var _binding: AddProductFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AddProductFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun addNewProduct() {
        viewModel.addNewProduct(
            binding.inputName.text.toString(),
            parseInt(binding.inputPrice.text.toString()),
            parseDouble(binding.inputKcal.text.toString()),
            parseDouble(binding.inputCarbs.text.toString()),
            parseDouble(binding.inputFat.text.toString())
        )
        val action = AddProductFragmentDirections.actionAddProductFragmentToProductListFragment()
        findNavController().navigate(action)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addButton.setOnClickListener {
            addNewProduct()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Hide keyboard.
        val inputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as
                InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)
        _binding = null
    }
}