package br.com.jaquesprojetos.eletriccarapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.jaquesprojetos.eletriccarapp.R
import br.com.jaquesprojetos.eletriccarapp.domain.Car

class CardAdapter(
    private val cars: List<Car>,
    private val isFavoriteScreen: Boolean = false
) : RecyclerView.Adapter<CardAdapter.ViewHolder>() {

    var carItemListener: (Car) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.car_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = cars.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.apply {
            preco.text = cars[position].preco
            bateria.text = cars[position].bateria
            recarga.text = cars[position].recarga
            potencia.text = cars[position].potencia
            if (isFavoriteScreen) {
                holder.favorito.setImageResource(R.drawable.ic_star_selected)
            }
            favorito.setOnClickListener {
                val car = cars[position]
                carItemListener(car)
                setupFavorite(car, holder)
            }
        }
    }

    private fun setupFavorite(car: Car, holder: ViewHolder) {
        car.isFavorite = !car.isFavorite

        if (car.isFavorite) holder.favorito.setImageResource(R.drawable.ic_star_selected)
        else holder.favorito.setImageResource(R.drawable.ic_star)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val preco: TextView
        val bateria: TextView
        val recarga: TextView
        val potencia: TextView
        val favorito: ImageView


        init {
            view.apply {
                preco = findViewById(R.id.tv_price_value)
                bateria = findViewById(R.id.tv_battery_value)
                recarga = findViewById(R.id.tv_refill_value)
                potencia = findViewById(R.id.tv_power_value)
                favorito = findViewById(R.id.iv_favorite)
            }
        }
    }
}




