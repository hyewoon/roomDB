package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import androidx.databinding.DataBindingUtil
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main) //데이터바인딩 연결 view바인딩과 동일
        binding.myData = this
    //xml 파일의 myData로 연결된 데이터 변화를 감지 할 수 없다.
    }
    fun showData(){
        binding.textResult.text= binding.inputText.text.toString() //텍스트 값을 가져오는 방법
    }
}
