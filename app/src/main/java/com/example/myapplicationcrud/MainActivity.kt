package com.example.myapplicationcrud

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import com.example.myapplicationcrud.adapter.TaskListAdapter
import com.example.myapplicationcrud.database.DatabaseHelper
import com.example.myapplicationcrud.model.TaskListModel

class MainActivity : AppCompatActivity() {
    lateinit var recycler_task : RecyclerView
    lateinit var btn_add : Button
    var taskListAdapter : TaskListAdapter ?= null

    var dbHandler : DatabaseHelper ?= null
    var taskList : List<TaskListModel> = ArrayList<TaskListModel>()
    var linearLayoutManager : LinearLayoutManager ?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_task = findViewById(R.id.rv_list)
        btn_add = findViewById(R.id.bt_add_items)

        dbHandler = DatabaseHelper(this)
        fetchlist()

        btn_add.setOnClickListener {

            val i = Intent(applicationContext,MainActivity2::class.java)
            startActivity(i)
        }

    }

    private  fun fetchlist(){
        taskList = dbHandler!!.getAllTask()
        taskListAdapter = TaskListAdapter(taskList,applicationContext)
        linearLayoutManager = LinearLayoutManager(applicationContext)
        recycler_task.layoutManager = linearLayoutManager
        recycler_task.adapter = taskListAdapter
        taskListAdapter?.notifyDataSetChanged()


    }
}