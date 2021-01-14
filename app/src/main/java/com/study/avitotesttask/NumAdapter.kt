package com.study.avitotesttask

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class NumAdapter(private val visibleElements : TreeSet<Int>, private val onClickDeleteListener: OnClickDeleteListener) : RecyclerView.Adapter<NumAdapter.NumViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumViewHolder {
        return NumViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item,parent, false))
    }

    override fun onBindViewHolder(holder: NumViewHolder, position: Int) {
        holder.numTextView.text = visibleElements.elementAt(position).toString()
        holder.deleteImageButton.setOnClickListener {onClickDeleteListener.deleteNum(holder.adapterPosition)}
    }

    override fun getItemCount(): Int {
        return visibleElements.size
    }

    class NumViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView ){
        val numTextView: TextView = itemView.findViewById(R.id.numTextView)
        val deleteImageButton : ImageButton = itemView.findViewById(R.id.deleteButton)
    }

    interface OnClickDeleteListener{
        fun deleteNum(position: Int)
    }
}