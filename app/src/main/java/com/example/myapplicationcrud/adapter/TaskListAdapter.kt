package com.example.myapplicationcrud.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.myapplicationcrud.MainActivity2
import com.example.myapplicationcrud.R
import com.example.myapplicationcrud.model.TaskListModel

class TaskListAdapter (tasksList : List<TaskListModel>, internal  var context: Context)
    :RecyclerView.Adapter<TaskListAdapter.TaskViewHolder>()
{

    internal var tasksList : List<TaskListModel> = ArrayList()
    init {
        this.tasksList = tasksList
    }

        inner class TaskViewHolder(view : View) : RecyclerView.ViewHolder(view){
            var name : TextView = view.findViewById(R.id.txt_name)
            var details : TextView = view.findViewById(R.id.txt_details)
            var btn_edit : Button = view.findViewById(R.id.btn_edit)
        }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TaskViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recycler_task_list,p0,false)
        return  TaskViewHolder(view)
    }

    override fun onBindViewHolder(p0: TaskViewHolder, p1: Int) {
        val tasks = tasksList[p1]
        p0.name.text = tasks.name
        p0.details.text = tasks.details

        p0.btn_edit.setOnClickListener {
            val  i = Intent(context,MainActivity2::class.java)
            i.putExtra("Mode","E")
            i.putExtra("ID",tasks.id)
            context.startActivity(i)
        }

    }

    override fun getItemCount(): Int {
        return tasksList.size
    }
}