package br.com.jaquesprojetos.eletriccarapp.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import br.com.jaquesprojetos.eletriccarapp.R
import br.com.jaquesprojetos.eletriccarapp.data.local.CarRepository
import br.com.jaquesprojetos.eletriccarapp.domain.Car
import br.com.jaquesprojetos.eletriccarapp.ui.adapter.CardAdapter

class FavoriteFragment : Fragment() {

    private lateinit var carListFavorite: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onResume() {
        super.onResume()
        setupList()


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews(view)
        setupList()
    }

    private fun getCarsLocalDb(): List<Car> {
        val repository = CarRepository(requireContext())
        return repository.getAll()
    }

    private fun setupViews(view: View) {
        view.apply {
            carListFavorite = findViewById(R.id.rv_favorite_car)
        }
    }

    private fun setupList() {
        val cars = getCarsLocalDb()
        val carAdapter = CardAdapter(cars, true)
        carListFavorite.apply {
            isVisible = true
            adapter = carAdapter
        }

        carAdapter.carItemListener = {
            val repository = CarRepository(requireContext())
            repository.delete(it)
            setupList()
        }


    }
}