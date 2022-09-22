package com.example.challenge_api_meli

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
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
import java.lang.StringBuilder
import kotlinx.coroutines.withContext as withContext2

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ProductAdapter
    private var TAG = "devErrors"
    private var TAG2 = "usersErrors"
    private var listItemsResponse: MutableList<ItemsResponse> = mutableListOf()
    val connection = NetworkManager()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (!connection.isConected(this)) {
            showExceptionsUser(Utils.CONNECTION_ERROR)
        }
        val btnSearch = binding.btnSearch
        val etSearch = binding.etSeach

        btnSearch.setOnClickListener {
            searchPreditor(etSearch.text.toString())
        }
        val drawer = binding.drawerLayout
        val navView = binding.navView
        val toolbar = binding.ivOptionSidebar
        val header: View = navView.getHeaderView(0)
        val progress = header.findViewById<ProgressBar>(R.id.progress)
        progress.progress = 40
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

    }

    override fun onResume() {
        super.onResume()
        if (!listItemsResponse.isNullOrEmpty()) {
            adapter.notifyDataSetChanged()
        }
        if (connection.isConected(this)) {
            initLayoutErrors()
        } else {
            showExceptionsUser(Utils.CONNECTION_ERROR)
        }

    }

    fun initLayoutErrors() {
        val viewErrors = this.binding.layoutE
        binding.layoutE.layoutE.visibility = View.GONE

    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else
            super.onBackPressed()
    }

    private fun searchPreditor(query: String) {
        var listaCategories: List<Category>?
        if (connection.isConected(this)) {
            CoroutineScope(Dispatchers.IO).launch {
                val call = getRetrofit().create(ProductService::class.java).getCategoryId(1, query)
                val responseCall = call.body()
                if (call.isSuccessful) {
                    listaCategories = responseCall
                    listaCategories?.forEach { requestTop20(it.category_id) }
                    withContext2(Dispatchers.Main) {
                        if (listaCategories.isNullOrEmpty()) {
                            showExceptionsUser(Utils.PREDITOR_ERORR)
                        } else {
                            binding.rvRecicler.visibility = View.VISIBLE
                        }
                    }
                } else {
                    showDevErrors(
                        call.code().toString(),
                        call.errorBody().toString(),
                        "searchPreditor"
                    )
                }
            }
        } else {
            showExceptionsUser(Utils.CONNECTION_ERROR)
        }

    }

    private fun requestTop20(value: String) {
        if (connection.isConected(this)) {
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
                } else {
                    showDevErrors(
                        call2.code().toString(),
                        call2.errorBody().toString(),
                        "requestTop20"
                    )
                    withContext2(Dispatchers.Main) {
                        showExceptionsUser(Utils.PREDITOR_ERORR)
                    }

                }
            }
        } else
            showExceptionsUser(Utils.CONNECTION_ERROR)
    }

    private fun obtainMultiget(query: String) {
        if (connection.isConected(this)) {
            CoroutineScope(Dispatchers.IO).launch {
                val call3 = getRetrofit().create(ProductService::class.java).getMultiGet(query)
                val responseCall3 = call3.body()
                if (call3.isSuccessful) {
                    responseCall3?.let {
                        listItemsResponse = responseCall3
                    }
                    withContext2(Dispatchers.Main) {
                        listItemsResponse?.let {
                            adapter = ProductAdapter(listItemsResponse)
                            binding.rvRecicler.layoutManager = LinearLayoutManager(baseContext)
                            binding.rvRecicler.adapter = adapter
                        }
                        if (listItemsResponse.isNullOrEmpty()) {
                            showExceptionsUser(Utils.MULTIGET_ERORR)
                        } else {
                            binding.rvRecicler.visibility = View.VISIBLE
                        }
                    }
                } else {
                    showDevErrors(
                        call3.code().toString(),
                        call3.errorBody().toString(),
                        "ObtaintMultiget"
                    )
                }
            }

        } else
            showExceptionsUser(Utils.CONNECTION_ERROR)
    }

    fun showDevErrors(code: String, message: String, consumeNameMethod: String) {
        Log.e(TAG, "Code ${code} -> description: ${message} method: ${consumeNameMethod}")
    }

    fun showExceptionsUser(message: String) {
        when (message) {
            Utils.PREDITOR_ERORR -> {
                Log.e(TAG2, "Busqueda sin respuesta")
                showScreenError(message)
            }
            Utils.MULTIGET_ERORR -> {
                Log.e(TAG2, "Producto no encontrado")
                showScreenError(message)
            }
            Utils.CONNECTION_ERROR -> {
                Log.e(TAG2, "Sin internet")
                showScreenError(message)
            }

        }
    }

    fun showScreenError(typeError: String) {
        val viewErrors = this.binding.layoutE
        binding.layoutE.layoutE.visibility = View.VISIBLE
        val linearNoFound = viewErrors.llNoFound
        val relativeNoFound = viewErrors.rlNoFound
        val linearConnection = viewErrors.llWithoutConnection
        val relativeConnection = viewErrors.rlWithoutConnection
        val linearErr = viewErrors.llErrorDetail
        linearErr.visibility = View.GONE

        when (typeError) {
            Utils.PREDITOR_ERORR -> {
                linearNoFound.visibility = View.VISIBLE
                relativeNoFound.visibility = View.VISIBLE
                linearConnection.visibility = View.GONE
                relativeConnection.visibility = View.GONE
                binding.rvRecicler.visibility = View.GONE
            }
            Utils.MULTIGET_ERORR -> {
                linearNoFound.visibility = View.VISIBLE
                relativeNoFound.visibility = View.VISIBLE
                linearConnection.visibility = View.GONE
                relativeConnection.visibility = View.GONE
                binding.rvRecicler.visibility = View.GONE
            }
            Utils.CONNECTION_ERROR -> {
                linearNoFound.visibility = View.GONE
                relativeNoFound.visibility = View.GONE
                linearConnection.visibility = View.VISIBLE
                relativeConnection.visibility = View.VISIBLE
                binding.rvRecicler.visibility = View.GONE
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.favorites -> {
                val goFavoriteActivity = Intent(this, Favorites::class.java)
                startActivity(goFavoriteActivity)
                binding.drawerLayout.closeDrawers()
            }
            R.id.home -> {
                binding.drawerLayout.closeDrawers()
            }
        }
        return true
    }
}