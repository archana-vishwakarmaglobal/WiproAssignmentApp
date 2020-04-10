package com.example.wiproassignmentproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.wiproassignmentproject.R
import com.example.wiproassignmentproject.databinding.ListItemBinding

import com.example.wiproassignmentproject.model.Rows
import kotlinx.android.synthetic.main.list_item.view.*

import java.util.*

class DemoListAdapter(
    private val context: Context,
    private val projectArrayList: ArrayList<Rows>
) : RecyclerView.Adapter<DemoListAdapter.MyViewHolder>() {
    private lateinit var binding: ListItemBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        //data binding
        val inflater = LayoutInflater.from(parent.context)
        binding = DataBindingUtil.inflate(inflater, R.layout.list_item, parent, false)
        return MyViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bind(projectArrayList.get(position))
    }

    override fun getItemCount(): Int {
        return projectArrayList.size
    }

    inner class MyViewHolder(itemView: View) : ViewHolder(itemView) {

        fun bind(row: Rows) {
            val imagePath:String? = row.imageHref
            if (imagePath == null) {
                itemView.ivProject.visibility = View.GONE
            }else{
                itemView.ivProject.visibility = View.VISIBLE
            }
            if (imagePath == null && row.title == null && row.description == null) {
                itemView.parentLayout.visibility = View.GONE
            }else{
                itemView.parentLayout.visibility = View.VISIBLE
            }
            itemView.tv_tittle.text = row.title
            itemView.title_des.text = row.description
            Glide.with(context)
                .load(imagePath)
                .into(itemView.ivProject)
        }
    }

}