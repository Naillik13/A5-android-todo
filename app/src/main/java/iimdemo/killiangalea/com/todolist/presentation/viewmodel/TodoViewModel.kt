package iimdemo.killiangalea.com.todolist.presentation.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import iimdemo.killiangalea.com.todolist.data.db.AppDatabase
import iimdemo.killiangalea.com.todolist.data.model.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TodoViewModel(context: Context) : ViewModel() {

    private val db = AppDatabase.get(context)

    fun insertTaskList(taskList: List<Task>) {
        GlobalScope.launch {
            val taskDao = db?.taskDao()
            taskDao?.insertAll(taskList)
        }

    }

    fun insertTask(task: Task, listener: (Task) -> Unit) {
        GlobalScope.launch {
            val taskDao = db?.taskDao()
            taskDao?.let {
                taskDao.insertTask(task)?.let {
                    task.id = it.toInt()
                    withContext(Dispatchers.Main) {
                        listener(task)
                    }
                }
            }
        }

    }

    fun deleteTask(task: Task) {
        GlobalScope.launch {
            val taskDao = db?.taskDao()
            taskDao?.delete(task)
        }

    }

    fun getTaskList(listener: (List<Task>?) -> Unit) {
        GlobalScope.launch {
            val taskDao = db?.taskDao()
            val taskList = taskDao?.getAll()
            withContext(Dispatchers.Main) {
                listener(taskList)
            }
        }
    }


}

