package br.com.jaquesprojetos.eletriccarapp.ui

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import br.com.jaquesprojetos.eletriccarapp.R
import br.com.jaquesprojetos.eletriccarapp.data.CarApi
import br.com.jaquesprojetos.eletriccarapp.data.local.CarRepository
import br.com.jaquesprojetos.eletriccarapp.domain.Car
import br.com.jaquesprojetos.eletriccarapp.ui.adapter.CardAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CarFragment : Fragment() {
    private lateinit var fabRedirect: FloatingActionButton
    private lateinit var carList: RecyclerView
    private lateinit var carApi: CarApi
    lateinit var progress: ProgressBar
    lateinit var noInternetImage: ImageView
    lateinit var noInternetText: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_car, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews(view)
        setListeners()
        setupRetrofit()
    }

    override fun onResume() {
        super.onResume()
        if (checkForInternet(context)) {
//            callService()
            getAllCars()

        } else emptyState()
    }

    private fun setupRetrofit() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://igorbag.github.io/cars-api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        carApi = retrofit.create(CarApi::class.java)
    }

    private fun getAllCars() {
        carApi.getAllCars().enqueue(object : Callback<List<Car>> {
            override fun onResponse(call: Call<List<Car>>, response: Response<List<Car>>) {
                if (response.isSuccessful) {
                    progress.isVisible = false
                    noInternetImage.isVisible = false
                    noInternetText.isVisible = false
                    response.body()?.let {
                        setupList(it)
                    }
                } else {
                    Toast.makeText(context, R.string.response_error, Toast.LENGTH_LONG).show()
                    Log.e("Erro", "Erro ao buscar dados")
                }
            }

            override fun onFailure(call: Call<List<Car>>, t: Throwable) {
                Log.e("Erro", "Erro ao buscar dados")
                Toast.makeText(context, R.string.response_error, Toast.LENGTH_LONG).show()
            }
        })
    }


    private fun emptyState() {
        progress.isVisible = false
        carList.isVisible = false
        noInternetImage.isVisible = true
        noInternetText.isVisible = true
    }

    private fun setupViews(view: View) {
        view.apply {
            fabRedirect = findViewById(R.id.fab_calculate)
            carList = findViewById(R.id.rv_car_list)
            progress = findViewById(R.id.pd_loader)
            noInternetImage = findViewById(R.id.iv_empty_state)
            noInternetText = findViewById(R.id.tv_no_wifi)
        }
    }

    private fun setListeners() {
        fabRedirect.setOnClickListener {
            val intent = Intent(context, CalculateAutonomyActivity::class.java)
            startActivity(intent)
        }
    }


    fun setupList(lista: List<Car>) {
        val carAdapter = CardAdapter(lista)
        carList.apply {
            isVisible = true
            adapter = carAdapter
        }
        carAdapter.carItemListener = {
//            CarRepository(requireContext()).save(it)
//            CarRepository(requireContext()).findCarById(it.id.toLong())
            CarRepository(requireContext()).saveIfNotExists(it)
//            CarRepository(requireContext()).getAll()
        }
    }

    private fun checkForInternet(context: Context?): Boolean {
        val connectivityManager =
            context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            @Suppress("DEPRECATION")
            val networkInfo = connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")

            return networkInfo.isConnected
        }

    }


}