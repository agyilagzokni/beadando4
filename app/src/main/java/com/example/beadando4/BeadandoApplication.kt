package com.example.beadando4

import android.app.Application
import com.example.beadando4.data.ProductDatabase

class BeadandoApplication : Application() {

    val database: ProductDatabase by lazy { ProductDatabase.getDatabase(this) }
}