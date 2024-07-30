package com.example.beadando4

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.beadando4.data.Product
import com.example.beadando4.data.ProductDao
import kotlinx.coroutines.launch

class ProductViewModel(private val productDao: ProductDao) : ViewModel() {
    val allProducts: LiveData<List<Product>> = productDao.getProducts()

    fun addNewProduct(
        name: String,
        price: Int,
        kcal: Double,
        carbs: Double,
        fat: Double
    ){
        val product = Product(name=name, price=price, kcal=kcal, carbs=carbs, fat=fat)
        viewModelScope.launch {
            productDao.insert(product)
        }
    }

    fun retrieveProduct(id: Int): LiveData<Product>{
        return productDao.getProductById(id)
    }

    fun deleteProduct(product: Product){
        viewModelScope.launch {
            productDao.delete(product)
        }
    }
}

class ProductViewModelFactory(private val productDao: ProductDao) : ViewModelProvider.Factory{
    override fun<T : ViewModel> create(modelClass: Class<T>) : T {
        if(modelClass.isAssignableFrom(ProductViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProductViewModel(productDao) as T
        }
        throw IllegalArgumentException("Unknown viewmodel class")
    }
}