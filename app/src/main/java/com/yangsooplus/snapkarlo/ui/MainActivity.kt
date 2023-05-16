package com.yangsooplus.snapkarlo.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.yangsooplus.snapkarlo.R
import com.yangsooplus.snapkarlo.data.model.Koni
import com.yangsooplus.snapkarlo.data.model.Prompt
import com.yangsooplus.snapkarlo.data.model.Response
import com.yangsooplus.snapkarlo.data.remote.NetworkObject
import com.yangsooplus.snapkarlo.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val navHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.fcv_main) as NavHostFragment
    }
    private val navController by lazy {
        navHostFragment.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bnvMain.setupWithNavController(navController)

        NetworkObject.karloService.getGeneratedImageByText(Koni(Prompt("test input", 1)))
            .enqueue(object : Callback<Response> {
                override fun onResponse(
                    call: Call<Response>,
                    response: retrofit2.Response<Response>,
                ) {
                    if (response.isSuccessful) {
                        Log.d("network", response.body()?.id.toString())
                    } else {
                        Log.d("network", response.message())
                        Log.d("network", response.code().toString())
                    }
                }

                override fun onFailure(call: Call<Response>, t: Throwable) {
                    Log.d("network", "fail")
                }
            })
    }
}
