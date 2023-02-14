package com.example.todolist

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var mainBinding : ActivityMainBinding
    lateinit var sharedPreferences: SharedPreferences
    var toDoList : ArrayList<String> = ArrayList()
    lateinit var adapter: ToDoListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)

        mainBinding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        adapter = ToDoListAdapter(toDoList)
        mainBinding.recyclerView.adapter = adapter

        mainBinding.editTextNewItem.setOnKeyListener(View.OnKeyListener{v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP && mainBinding.editTextNewItem.text.length>0) {
                toDoList.add(mainBinding.editTextNewItem.text.toString())
                mainBinding.editTextNewItem.text.clear()
                adapter.notifyItemInserted(toDoList.size-1)
                return@OnKeyListener true
            }
            false
        })

    }
}