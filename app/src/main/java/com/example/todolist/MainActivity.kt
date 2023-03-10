package com.example.todolist

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
        adapter = ToDoListAdapter(toDoList, this@MainActivity)
        mainBinding.recyclerView.adapter = adapter

        sharedPreferences = this.getSharedPreferences("saveData", MODE_PRIVATE)
        if(sharedPreferences.all.isNotEmpty()) {
            for (i in 0..sharedPreferences.all.size - 1) {
                toDoList.add(sharedPreferences.getString("item $i", null)!!)
            }
        }

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

    override fun onDestroy() {
        super.onDestroy()
        sharedPreferences = this.getSharedPreferences("saveData", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear().apply()


        if(toDoList.size>0){
            for(i in toDoList.indices ){
                editor.putString("item " + i, toDoList.get(i))
            }
        }
        editor.apply()
        toDoList.clear()
    }

}