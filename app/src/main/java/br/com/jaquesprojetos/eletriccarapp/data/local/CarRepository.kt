package br.com.jaquesprojetos.eletriccarapp.data.local

import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns
import android.util.Log
import br.com.jaquesprojetos.eletriccarapp.data.local.CarsContract.CarsEntry.COLUMN_BATERIA
import br.com.jaquesprojetos.eletriccarapp.data.local.CarsContract.CarsEntry.COLUMN_CAR_ID
import br.com.jaquesprojetos.eletriccarapp.data.local.CarsContract.CarsEntry.COLUMN_POTENCIA
import br.com.jaquesprojetos.eletriccarapp.data.local.CarsContract.CarsEntry.COLUMN_PRECO
import br.com.jaquesprojetos.eletriccarapp.data.local.CarsContract.CarsEntry.COLUMN_RECARGA
import br.com.jaquesprojetos.eletriccarapp.data.local.CarsContract.CarsEntry.COLUMN_URL_PHOTO
import br.com.jaquesprojetos.eletriccarapp.data.local.CarsContract.CarsEntry.TABLE_NAME
import br.com.jaquesprojetos.eletriccarapp.domain.Car

class CarRepository(private val context: Context) {
    fun save(car: Car): Boolean {
        var isSaved = false
        try {
            val dbHelper = CarsDBHelper(context)
            val db = dbHelper.writableDatabase
            val values = ContentValues().apply {
                put(COLUMN_CAR_ID, car.id)
                put(COLUMN_PRECO, car.preco)
                put(COLUMN_BATERIA, car.bateria)
                put(COLUMN_POTENCIA, car.potencia)
                put(COLUMN_RECARGA, car.recarga)
                put(COLUMN_URL_PHOTO, car.urlPhoto)
            }
            val inserted = db?.insert(TABLE_NAME, null, values)
            if (inserted != null) isSaved = true

        } catch (ex: Exception) {
            ex.message?.let { Log.e("Erro", it) }
        }
        return isSaved
    }

    fun findCarById(id: Long): Car {
        val dbHelper = CarsDBHelper(context)
        val db = dbHelper.readableDatabase
        val columns = arrayOf(
            BaseColumns._ID,
            COLUMN_CAR_ID,
            COLUMN_PRECO,
            COLUMN_BATERIA,
            COLUMN_POTENCIA,
            COLUMN_RECARGA,
            COLUMN_URL_PHOTO
        )
        val filter = "$COLUMN_CAR_ID = ?"
        val cursor = db.query(
            TABLE_NAME,
            columns,
            filter,
            arrayOf(id.toString()),
            null,
            null,
            null
        )
        var itemId: Long = 0
        var itemPreco = ""
        var itemBateria = ""
        var itemPotencia = ""
        var itemRecarga = ""
        var itemUrlPhoto = ""

        with(cursor) {
            while (moveToNext()) {
                itemId = getLong(getColumnIndexOrThrow(COLUMN_CAR_ID))
                itemPreco = getString(getColumnIndexOrThrow(COLUMN_PRECO))
                itemBateria = getString(getColumnIndexOrThrow(COLUMN_BATERIA))
                itemPotencia = getString(getColumnIndexOrThrow(COLUMN_POTENCIA))
                itemRecarga = getString(getColumnIndexOrThrow(COLUMN_RECARGA))
                itemUrlPhoto = getString(getColumnIndexOrThrow(COLUMN_URL_PHOTO))

            }
        }
        cursor.close()
        return Car(
            itemId.toInt(),
            itemPreco,
            itemBateria,
            itemRecarga,
            itemPotencia,
            itemUrlPhoto,
            true
        )
    }

    fun saveIfNotExists(car: Car) {
        val carFound = findCarById(car.id.toLong())
        if (car.id != carFound.id) save(car)
    }

    fun getAll(): List<Car>{

        val dbHelper = CarsDBHelper(context)
        val db = dbHelper.readableDatabase
        val columns = arrayOf(
            BaseColumns._ID,
            COLUMN_CAR_ID,
            COLUMN_PRECO,
            COLUMN_BATERIA,
            COLUMN_POTENCIA,
            COLUMN_RECARGA,
            COLUMN_URL_PHOTO
        )
        val cursor = db.query(
            TABLE_NAME,
            columns,
            null,
            null,
            null,
            null,
            null
        )
        val cars = mutableListOf<Car>()
        with(cursor) {
            while (moveToNext()) {
                val itemId = getLong(getColumnIndexOrThrow(COLUMN_CAR_ID))
                val itemPreco = getString(getColumnIndexOrThrow(COLUMN_PRECO))
                val itemBateria = getString(getColumnIndexOrThrow(COLUMN_BATERIA))
                val itemPotencia = getString(getColumnIndexOrThrow(COLUMN_POTENCIA))
                val itemRecarga = getString(getColumnIndexOrThrow(COLUMN_RECARGA))
                val itemUrlPhoto = getString(getColumnIndexOrThrow(COLUMN_URL_PHOTO))
                cars.add(Car(itemId.toInt(), itemPreco, itemBateria, itemRecarga, itemPotencia, itemUrlPhoto, true))
            }
        }
        cursor.close()
         return cars
    }

    fun delete(car: Car) {
        val dbHelper = CarsDBHelper(context)
        val db = dbHelper.writableDatabase
        val filter = "$COLUMN_CAR_ID = ?"
        val deleted = db.delete(TABLE_NAME, filter, arrayOf(car.id.toString()))
        if (deleted > 0) Log.i("Deletado", "Carro deletado com sucesso")
    }



}