package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.roomDB.Todo
import com.example.myapplication.roomDB.TodoDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db : TodoDatabase
    private lateinit var viewModel: TodoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(TodoViewModel::class.java)
        binding.myData = viewModel

        db = TodoDatabase.getInstance(applicationContext)!!




    }
    fun showList() {

            //데이터를 db에 저장
        var input = binding.inputText.text.toString()

        CoroutineScope(Dispatchers.IO).launch {
          db.todoDao().insert(Todo(input))
        }

        //데이터 가져오기
        CoroutineScope(Dispatchers.IO).launch { // 코루틴 사용 비동기로 실행.
            var myList = db!!.todoDao().getAll()
            binding.result.text = myList.list.toString()

        }
    }

    fun delete() {
        CoroutineScope(Dispatchers.IO).launch {
            db.todoDao().deleteAll()
        }
    }


}

