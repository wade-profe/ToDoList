package com.example.todolist

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class ToDoListAdapter(
    var todoList:ArrayList<String>,
    val context: Context
): RecyclerView.Adapter<ToDoListAdapter.ListItemViewHolder>()
{

    class ListItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var cardView: CardView = itemView.findViewById(R.id.cardView)
        var listItemText: TextView = itemView.findViewById(R.id.listItemText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ListItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        holder.listItemText.text = todoList.get(position)

        holder.cardView.setOnLongClickListener{
            createDeleteDialog(position)
            true
        }
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    fun createDeleteDialog(position: Int){
        var deleteDialog =AlertDialog.Builder(context)
        deleteDialog.setTitle("Do you want to delete this item?")
        deleteDialog.setMessage("Press OK to delete")
        deleteDialog.setIcon(R.drawable.app_icon)
        deleteDialog.setCancelable(true).setPositiveButton("OK", DialogInterface.OnClickListener{ dialogInterface, which ->
            todoList.removeAt(position)
            notifyItemRangeChanged(0, todoList.size+1)
        })
        deleteDialog.create().show()
    }
}