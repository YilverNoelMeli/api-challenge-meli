package com.example.challenge_api_meli

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challenge_api_meli.APIService.ProductService
import com.example.challenge_api_meli.Adapter.ProductAdapter
import com.example.challenge_api_meli.Utils.Companion.getRetrofit
import com.example.challenge_api_meli.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.StringBuilder

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ProductAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val btnSearch = binding.btnSearch
        val etSearch = binding.etSeach
        btnSearch.setOnClickListener {
            searchUrl(etSearch.text.toString())
        }
        val drawer = binding.drawerLayout
        val navView = binding.navView
        val toolbar = binding.ivOptionSidebar
        val header: View = navView.getHeaderView(0)
        val progress = header.findViewById<ProgressBar>(R.id.progress)
        progress.progress = 40
        /*val ivShare = header.findViewById<ImageView>(R.id.ejemplo)
        ivShare.setOnClickListener {
            Toast.makeText(this,"dssds",Toast.LENGTH_SHORT).show()
        }
        val ivKOKORO = header.findViewById<ImageView>(R.id.ejemplo2)
        ivKOKORO.setOnClickListener {
            Toast.makeText(this,"im very happy thanks univers, god, and mamapacha",Toast.LENGTH_SHORT).show()
        }*/
       /* val changeIconsMenu =  navView.menu
        changeIconsMenu.findItem(R.id.favorites).setVisible(true)*/

        setSupportActionBar(toolbar)
        navView.bringToFront()
        val toggle: ActionBarDrawerToggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }else
            super.onBackPressed()
    }

    private fun searchUrl(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(ProductService::class.java).getCategoryId(1, query)
            val responseCall = call.body()
            if (call.isSuccessful) {
                val ls: List<Category>? = responseCall
                ls?.forEach { requestTop20(it.category_id) }

            }

        }

    }

    private fun requestTop20(value: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call2 = getRetrofit().create(ProductService::class.java)
                .getHighlightList(value)
            val responseCall2 = call2.body()
            if (call2.isSuccessful) {
                val idProduct = responseCall2?.items
                var appendId = ""
                idProduct?.forEach {
                    appendId += StringBuilder(it.id).append(",").toString()
                }
                obtainMultiget(appendId)
            }
        }

    }

    private fun obtainMultiget(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call3 = getRetrofit().create(ProductService::class.java).getMultiGet(query)
            val responseCall3 = call3.body()
            runOnUiThread {
                if (call3.isSuccessful) {
                    val listProduct: List<ItemsResponse>? = responseCall3
                    listProduct?.let {
                        adapter = ProductAdapter(listProduct)
                        binding.rvRecicler.layoutManager = LinearLayoutManager(baseContext)
                        binding.rvRecicler.adapter = adapter
                    }
                }

            }
        }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.favorites ->{
                val goFavoriteActivity = Intent(this, Favorites::class.java)
                        startActivity(goFavoriteActivity)
            }
        }
        return true
    }
}