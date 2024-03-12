package br.com.jaquesprojetos.eletriccarapp.data.local

import android.content.ContentValues
import android.content.Context
import android.util.Log
import br.com.jaquesprojetos.eletriccarapp.domain.Car

class CarRepository(private val context: Context) {
    fun save(car: Car): Boolean {
        var isSaved = false
        try {
            val dbHelper = CarsDBHelper(context)
            val db = dbHelper.writableDatabase
            val values = ContentValues().apply {
                put(CarsContract.CarsEntry.COLUMN_PRECO, car.preco)
                put(CarsContract.CarsEntry.COLUMN_BATERIA, car.bateria)
                put(CarsContract.CarsEntry.COLUMN_POTENCIA, car.potencia)
                put(CarsContract.CarsEntry.COLUMN_RECARGA, car.recarga)
                put(CarsContract.CarsEntry.COLUMN_URL_PHOTO, car.urlPhoto)
            }
            val inserted = db?.insert(CarsContract.CarsEntry.TABLE_NAME, null, values)
            if (inserted != null) isSaved = true

        } catch (ex: Exception) {
            ex.message?.let { Log.e("Erro", it) }
        }
        return isSaved
    }
}