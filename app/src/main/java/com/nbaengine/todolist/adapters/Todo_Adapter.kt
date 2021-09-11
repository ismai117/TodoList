package com.nbaengine.todolist.adapters

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nbaengine.todolist.R
import com.nbaengine.todolist.model.Todo
import kotlinx.android.synthetic.main.item_layout.view.*

class Todo_Adapter (
    private val todos: MutableList<Todo>
        ) : RecyclerView.Adapter<Todo_Adapter.TodoViewHolder>(){

    class TodoViewHolder (itemview: View) : RecyclerView.ViewHolder(itemview)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout,
                parent,
                false
            )
        )
    }

    fun addTodo(todo: Todo){
        todos.add(todo)
        notifyItemChanged(todos.size - 1)
    }

    fun doneTodo(){
        todos.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(notes_value: TextView, checkbox: Boolean){
        if (checkbox){
            notes_value.paintFlags = notes_value.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else{
            notes_value.paintFlags = notes_value.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }


    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currentTodo = todos[position]
        holder.itemView.apply {
            notes_value.text = currentTodo.title
            checkBox.isChecked = currentTodo.isChecked
            toggleStrikeThrough(notes_value, checkBox.isChecked)

            checkBox.setOnCheckedChangeListener { _ , isChecked ->
                toggleStrikeThrough(notes_value, isChecked)
                currentTodo.isChecked = !currentTodo.isChecked
            }
        }
    }

    override fun getItemCount(): Int {
       return todos.size
    }

}