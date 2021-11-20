package com.parveztest.rxjava

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(val context: Context) : RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {

    var userList : List<User> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_adapter,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.txtName.text = userList.get(position).name
        holder.txtEmail.text = userList.get(position).email
        holder.txtWebsite.text = userList.get(position).website
    }

    fun setUserListItems(userList: List<User>){
        this.userList = userList;
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

        val txtName: TextView = itemView!!.findViewById(R.id.txtName)
        val txtEmail: TextView = itemView!!.findViewById(R.id.txtEmail)
        val txtWebsite: TextView = itemView!!.findViewById(R.id.txtWebsite)

    }
}