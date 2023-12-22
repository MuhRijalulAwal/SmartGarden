package com.example.smartgarden.ui.community

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.smartgarden.R
import com.example.smartgarden.dummy.Community

class ListCommunityAdapter(private val listCommunity: ArrayList<Community>,
                           private var originalList: ArrayList<Community> = listCommunity.toMutableList() as ArrayList<Community>,
                           private var filteredList: ArrayList<Community> = originalList.toMutableList() as ArrayList<Community>,
                           val listener: (Community) -> Unit): RecyclerView.Adapter<ListCommunityAdapter.ListViewHolder>() {
    class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvUser: TextView = itemView.findViewById(R.id.tv_user_community)
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_photo_community)
        val tvTitle: TextView = itemView.findViewById(R.id.tv_title_community)
        val tvDesc: TextView = itemView.findViewById(R.id.tv_desc_community)

        fun bindView(Community: Community, listener: (Community) -> Unit){
            tvUser.text = Community.user
            imgPhoto.setImageResource(Community.photo)
            tvTitle.text = Community.title
            tvDesc.text = Community.desc
            itemView.setOnClickListener{
                listener(Community)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.community_item, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listCommunity.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bindView(listCommunity[position],listener)

        val (user,title, desc, photo) = listCommunity[position]
        holder.tvUser.text = user
        holder.imgPhoto.setImageResource(photo)
        holder.tvTitle.text = title
        holder.tvDesc.text = desc
        itemCount
    }

    fun filter(query: String) {
        filteredList.clear()
        filteredList.addAll(originalList.filter { it.title.contains(query, ignoreCase = true) })
        notifyDataSetChanged()
    }


}