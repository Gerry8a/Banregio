package com.gochoa.banregio.presenter.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.gochoa.banregio.data.remote.response.MovementsResponseItem
import com.gochoa.banregio.databinding.ItemMovementsBinding

class MovementsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemMovementsBinding.bind(view)

    fun render(movementsResponseItem: MovementsResponseItem) {
        binding.apply {
            tvDescription.text = movementsResponseItem.Descripcion
            tvDate.text = movementsResponseItem.Fecha
            tvTotal.text = movementsResponseItem.Monto
        }
    }
}