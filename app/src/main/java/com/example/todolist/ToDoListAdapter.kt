package com.example.todolist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ToDoListAdapter(
    var todoList:ArrayList<String>,
    var context: Context
): RecyclerView.Adapter<ToDoListAdapter.ListItemViewHolder>()
{

    class ListItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var listItemText: TextView = itemView.findViewById(R.id.listItemText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ListItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        holder.listItemText.text = todoList.get(position)

        holder.listItemText.setOnClickListener{
            todoList.removeAt(position)
            notifyItemRangeRemoved(0, itemCount+1)
        }
    }

    override fun getItemCount(): Int {
        return todoList.size
    }
}