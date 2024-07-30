package com.example.beadando4.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ProductDao {
    @Query("select * from product")
    fun getProducts(): LiveData<List<Product>>

    @Query("select * from product where id=:id")
    fun getProductById(id: Int): LiveData<Product>

    @Query("select * from product where name=:name")
    fun getProductByName(name: String): LiveData<Product>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(product: Product)

    @Update
    suspend fun update(product: Product)

    @Delete
    suspend fun delete(product: Product)
}