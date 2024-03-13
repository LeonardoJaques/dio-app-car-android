package br.com.jaquesprojetos.eletriccarapp.data.local

import android.provider.BaseColumns

object CarsContract {


    object CarsEntry : BaseColumns {
        const val TABLE_NAME = "car"
        const val COLUMN_CAR_ID = "car_id"
        const val COLUMN_PRECO = "preco"
        const val COLUMN_BATERIA = "bateria"
        const val COLUMN_POTENCIA = "potencia"
        const val COLUMN_RECARGA = "recarga"
        const val COLUMN_URL_PHOTO = "url_photo"
    }

    const val CAR_CREATE_TABLE: String = "CREATE TABLE ${CarsEntry.TABLE_NAME} (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY," +
            "${CarsEntry.COLUMN_PRECO} TEXT," +
            "${CarsEntry.COLUMN_CAR_ID} TEXT," +
            "${CarsEntry.COLUMN_BATERIA} TEXT," +
            "${CarsEntry.COLUMN_POTENCIA} TEXT," +
            "${CarsEntry.COLUMN_RECARGA} TEXT," +
            "${CarsEntry.COLUMN_URL_PHOTO} TEXT)"


    const val CAR_DROP_TABLE: String = "DROP TABLE IF EXISTS ${CarsEntry.TABLE_NAME}"
}