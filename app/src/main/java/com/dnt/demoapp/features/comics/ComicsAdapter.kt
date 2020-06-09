package com.dnt.demoapp.features.comics

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dnt.demoapp.R
import com.dnt.demoapp.data.models.Comic
import com.dnt.demoapp.utils.DiffCallback
import kotlinx.android.synthetic.main.item_comic.view.*

class ComicsAdapter : RecyclerView.Adapter<ComicsAdapter.ViewHolder>() {
    private val items: MutableList<Comic> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_comic, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setItems(comics: List<Comic>) {
        val diffResult = DiffUtil
            .calculateDiff(DiffCallback(this.items, comics))
        this.items.clear()
        this.items.addAll(comics)
        diffResult.dispatchUpdatesTo(this)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(comic: Comic) {
            itemView.name.text = comic.title
        }
    }
}