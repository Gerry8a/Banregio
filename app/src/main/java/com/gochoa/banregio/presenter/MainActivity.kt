package com.gochoa.banregio.presenter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.gochoa.banregio.data.remote.ApiResponseStatus
import com.gochoa.banregio.data.remote.response.CardInfo
import com.gochoa.banregio.data.remote.response.MovementsResponseItem
import com.gochoa.banregio.data.utils.RandomNumber.randomCvv
import com.gochoa.banregio.databinding.ActivityMainBinding
import com.gochoa.banregio.presenter.adapter.MovementsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: CardViewModel by viewModels()
    private lateinit var movementsAdapter: MovementsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
        buildListeners()
    }

    private fun buildListeners() {
        binding.tvCvv.setOnClickListener {
            viewModel.timerFlow(5000)
            lifecycleScope.launch {
                viewModel.timer.collect {
                    binding.tvCvv.text = randomCvv()
                }
            }
        }
    }

    private fun initUI() {
        lifecycleScope.launch {
            viewModel.card.observe(this@MainActivity) {
                when (it) {
                    is ApiResponseStatus.Error -> {
                        Toast.makeText(this@MainActivity, it.messageID, Toast.LENGTH_SHORT).show()
                    }

                    is ApiResponseStatus.Loading -> {}
                    is ApiResponseStatus.Success -> buildCard(it.data)
                }

            }

            viewModel.movements.observe(this@MainActivity) {
                when (it) {
                    is ApiResponseStatus.Error -> Log.i("ggg", it.messageID)
                    is ApiResponseStatus.Loading -> {}
                    is ApiResponseStatus.Success -> {
                        fillList(it.data)
                    }
                }
            }
        }
    }

    private fun fillList(data: List<MovementsResponseItem>) {
        movementsAdapter = MovementsAdapter(data)
        binding.rvList.apply {
            adapter = movementsAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun buildCard(data: CardInfo) {
        binding.apply {
            tvExpiration.text = data.fechaExp
            tvOwner.text = data.titularTarjeta
            tvFirst.text = data.numeroTarjeta
        }
    }
}