package com.example.servicetest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun serviceStart(view: View){//View를 사용하면 클릭리스너 연결이 없어도 레이아웃 파일에서 메서드에 직접 접근할 수 있음.
        val intent = Intent(this, MyService::class.java)//Intent 생성
        intent.action = MyService.ACTION_START//MyService 에 정의해둔 명령을 같이 전달
        startService(intent)
    }

    fun serviceStop(view: View){
        val intent = Intent(this, MyService::class.java)
        stopService(intent)
    }
}