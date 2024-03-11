package br.com.jaquesprojetos.eletriccarapp.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import br.com.jaquesprojetos.eletriccarapp.ui.CarFragment
import br.com.jaquesprojetos.eletriccarapp.ui.FavoriteFragment
import br.com.jaquesprojetos.eletriccarapp.ui.MainActivity

class TabsAdapter(fragment: MainActivity) : FragmentStateAdapter(
    fragment
) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CarFragment()
            else -> FavoriteFragment()
        }
    }
}