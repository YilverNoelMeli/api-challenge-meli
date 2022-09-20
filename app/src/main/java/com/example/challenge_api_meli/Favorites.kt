package com.example.challenge_api_meli

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challenge_api_meli.APIService.ProductService
import com.example.challenge_api_meli.AdapterFavorites.FavoritesAdapter
import com.example.challenge_api_meli.Interfaces.ClickItem
import com.example.challenge_api_meli.databinding.ActivityFavoritesBinding
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Favorites : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, ClickItem {
    private lateinit var binding: ActivityFavoritesBinding
    private lateinit var adapter: FavoritesAdapter
    private var list: MutableList<ItemsResponse> = mutableListOf()
    private var sharedManager: SharedManager = SharedManager()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val drawer = binding.drawerLayoutFavorites
        val navView = binding.navView
        val toolbar = binding.ivOptionSidebar
        val header: View = navView.getHeaderView(0)

        setSupportActionBar(toolbar)
        navView.bringToFront()
        val toggle: ActionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)

        obtainMultiget(this)
    }

    fun initRecicler() {
        adapter = FavoritesAdapter(list, this)
        binding.rvReciclerFavorites.layoutManager = LinearLayoutManager(this)
        binding.rvReciclerFavorites.adapter = adapter
    }

    private fun obtainMultiget(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = Utils.getRetrofit().create(ProductService::class.java)
                .getMultiGet(sharedManager.getFavoriteItems(context))
            val responseCall = call.body()
            runOnUiThread {
                if (call.isSuccessful) {
                    responseCall?.let {
                        list = it
                    }
                    initRecicler()
                }
            }
        }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favorites -> {
                binding.drawerLayoutFavorites.closeDrawers()
            }
            R.id.home -> {
                val goFavoriteActivity = Intent(this, MainActivity::class.java)
                startActivity(goFavoriteActivity)
                finish()

            }
        }
        return true
    }


    override fun clickItem(position: Int) {
        list.removeAt(position)
        adapter.notifyItemRemoved(position)

    }
}