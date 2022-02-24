package com.vadymex.movixapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.vadymex.movixapp.databinding.ActivityMainBinding
import com.vadymex.movixapp.presentation.detail.DetailMovieFragment
import com.vadymex.movixapp.presentation.favorite.FavoriteFragment
import com.vadymex.movixapp.presentation.home.HomeFragment
import com.vadymex.movixapp.presentation.search.SearchFragment
import com.vadymex.movixapp.presentation.utils.Navigator
import com.vadymex.movixapp.presentation.utils.OnItemShow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), Navigator {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        setUpNavDrawer()
        if(savedInstanceState == null){
            openFragment(HomeFragment())
        }


    }

    private fun setUpNavDrawer() {
        binding.apply {
            topAppBar.setNavigationOnClickListener {
                drawerLayout.open()
            }

            navView.setNavigationItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.searchItem -> {
                        openFragment(SearchFragment())
                        true
                    }
                    R.id.favoriteItem -> {
                        openFragment(FavoriteFragment())
                        true
                    }
                    R.id.homeItem -> {
                        openFragment(HomeFragment())
                        true
                    }
                    else -> {
                        true
                    }
                }
            }
        }
    }

    private fun openFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    override fun onItemClicked(onItemShow: OnItemShow) {
        when(onItemShow){
            is OnItemShow.ShowMovie ->{
                val bundle = Bundle()
                bundle.putString("key", onItemShow.data)
                val fragment = DetailMovieFragment()
                fragment.arguments = bundle
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, fragment)
                    .commit()
            }
            is OnItemShow.ShowMovies ->{}
            is OnItemShow.ShowPeople ->{}
            is OnItemShow.ShowPerson ->{}
        }
    }

}