package br.com.jaquesprojetos.eletriccarapp.data

import br.com.jaquesprojetos.eletriccarapp.domain.Car
import retrofit2.Call
import retrofit2.http.GET

interface CarApi {
    @GET("cars.json")
    fun getAllCars(): Call<List<Car>>
}