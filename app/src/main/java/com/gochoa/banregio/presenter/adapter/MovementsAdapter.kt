package com.gochoa.banregio.presenter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gochoa.banregio.R
import com.gochoa.banregio.data.remote.response.MovementsResponseItem

class MovementsAdapter (private var movementsList: List<MovementsResponseItem> = emptyList(),

                        ) : RecyclerView.Adapter<MovementsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovementsViewHolder {
        return MovementsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movements, parent, false)
        )
    }

    override fun getItemCount(): Int = movementsList.size

    override fun onBindViewHolder(holder: MovementsViewHolder, position: Int) {
        holder.render(movementsList[position])
    }
}