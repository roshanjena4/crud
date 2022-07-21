package com.example.myapplicationcrud

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.myapplicationcrud.database.DatabaseHelper
import com.example.myapplicationcrud.model.TaskListModel

class MainActivity2 : AppCompatActivity() {

    lateinit var  btn_save : Button
    lateinit var btn_del : Button
    lateinit var et_name : EditText
    lateinit var et_details : EditText
    var isEditMode : Boolean = false

    var dbHandler : DatabaseHelper ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        btn_save = findViewById(R.id.btn_save)
        btn_del = findViewById(R.id.btn_del)
        et_name = findViewById(R.id.et_name)
        et_details = findViewById(R.id.et_details)
        dbHandler = DatabaseHelper(this)

        if(intent != null && intent.getStringExtra("Mode") == "E"){
            // update data
            isEditMode = true
            btn_save.text = "update data"
            btn_del.visibility = View.VISIBLE

            val tasks : TaskListModel = dbHandler!!.getTasks(intent.getIntExtra("ID",0))
            et_name.setText(tasks.name)
            et_details.setText(tasks.details)
        }else{
            //insert new data
            isEditMode = false
            btn_save.text = "save data"
            btn_del.visibility = View.GONE
        }
        btn_save.setOnClickListener {
            var success : Boolean = false
            val tasks : TaskListModel = TaskListModel()
            if (isEditMode){
                // update

                tasks.id = intent.getIntExtra("ID",0)
                tasks.name = et_name.text.toString()
                tasks.details = et_details.text.toString()

                success = dbHandler?.updateTasks(tasks) as Boolean

            }else{
                // insert
                tasks.name = et_name.text.toString()
                tasks.details = et_details.text.toString()

                success = dbHandler?.addTask(tasks) as Boolean
            }
            if (success){
                val i = Intent(applicationContext,MainActivity::class.java)
                startActivity(i)
                finish()

            }else{
                Toast.makeText(applicationContext,"something went worng!!",Toast.LENGTH_LONG).show()
            }
        }

        btn_del.setOnClickListener {

            val dialog = AlertDialog.Builder(this).setTitle("INFO").setMessage("CLICK YES IF YOU WANT TO DELETE THE TASKS").setPositiveButton("YES", {dialog, i ->
                val success = dbHandler?.deleteTasks(intent.getIntExtra("ID",0)) as Boolean
                if (success)
                    finish()
                dialog.dismiss()
            })
                .setNegativeButton("NO",{dialog , i ->
                    dialog.dismiss()
                })
            dialog.show()
        }
    }
}