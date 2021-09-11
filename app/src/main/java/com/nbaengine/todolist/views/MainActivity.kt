package com.nbaengine.todolist.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.nbaengine.todolist.R
import com.nbaengine.todolist.adapters.Todo_Adapter
import com.nbaengine.todolist.model.Todo
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_layout.*

class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter: Todo_Adapter;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoAdapter = Todo_Adapter(mutableListOf())

        notes_recycler_view.adapter = todoAdapter
        notes_recycler_view.layoutManager = LinearLayoutManager(this)

        add_note.setOnClickListener {
            val title = notes_input.text.toString()
            if (title.isNotEmpty()){
                val todo = Todo(title)
                todoAdapter.addTodo(todo)
                notes_input.text.clear()
            }
        }

        delete_note.setOnClickListener {
            todoAdapter.doneTodo()
        }

    }
}