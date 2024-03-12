package br.com.jaquesprojetos.eletriccarapp.domain

data class Car(
    val id: Int,
    val preco: String,
    val bateria: String,
    val recarga: String,
    val potencia: String,
    val urlPhoto: String,
    var isFavorite: Boolean = false
)