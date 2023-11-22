package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.roomDB.Todo
import com.example.myapplication.roomDB.TodoDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private  lateinit var db : TodoDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main) //데이터바인딩 연결 view바인딩과 동일
        binding.myData = this
    //xml 파일의 myData로 연결된 데이터 변화를 감지 할 수 없다.

     /*  //db인스턴스 만들기
        db =Room.databaseBuilder(
            applicationContext,
            TodoDatabase::class.java, "todo"
        ).build()

*/
        db = TodoDatabase.getInstance(applicationContext)!!

    }


    fun showData(){

        var todoList: String = binding.inputText.text.toString()

        CoroutineScope(Dispatchers.IO).launch {
          db.todoDao().insert(Todo(0,todoList))
        }

        CoroutineScope(Dispatchers.IO).launch {
            db.todoDao().getAll()
        }
    }
}


