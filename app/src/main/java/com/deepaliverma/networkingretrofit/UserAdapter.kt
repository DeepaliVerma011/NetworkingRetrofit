package com.deepaliverma.networkingretrofit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.item_row.view.*

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    var data: List<User> = ArrayList()
    var onItemClick: ((id: String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    fun swapData(data: List<User>) {
        this.data = data
        notifyDataSetChanged()
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: User) = with(itemView) {
            textView.text = item.name
            textView1.text = item.login
            Picasso.get().load(item.avatarUrl).into(imageView)
            setOnClickListener {
                onItemClick?.invoke(item.login!!)
            }

        }
    }

}
