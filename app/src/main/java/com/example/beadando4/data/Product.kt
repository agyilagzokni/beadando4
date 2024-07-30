package com.example.beadando4.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "price")
    val price: Int,
    @ColumnInfo(name = "calories")
    val kcal: Double,
    @ColumnInfo(name = "carbs")
    val carbs: Double,
    @ColumnInfo(name = "fat")
    val fat: Double
)