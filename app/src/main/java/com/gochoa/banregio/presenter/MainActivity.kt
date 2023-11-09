package com.gochoa.banregio.presenter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.gochoa.banregio.data.remote.ApiResponseStatus
import com.gochoa.banregio.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: CardViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        lifecycleScope.launch {
            viewModel.getCardInfo()
            viewModel.card.observe(this@MainActivity) {
                when (it) {
                    is ApiResponseStatus.Error -> {
                        Toast.makeText(this@MainActivity, it.messageID, Toast.LENGTH_SHORT).show()
                    }

                    is ApiResponseStatus.Loading -> {}
                    is ApiResponseStatus.Success -> Toast.makeText(
                        this@MainActivity,
                        it.data.nombreBanco,
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }

            }
        }
    }
}