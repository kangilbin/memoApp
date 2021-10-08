package com.kang.memo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_memo.view.*

class MyAdapter(val context: Context,
                var list : List<MemoEntity>,
                var ondeleteListnere: OndeleteListnere) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    inner class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val memo = itemView.textView_memo
        val root = itemView.root
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_memo, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {
        val memo = list[position]

        holder.memo.text = memo.memo
        holder.root.setOnLongClickListener(object : View.OnLongClickListener{
            override fun onLongClick(p0: View?): Boolean {
                ondeleteListnere.onDeleteListener(memo)
                return true
            }
        })
    }

    override fun getItemCount(): Int {
        return list.size
    }
}