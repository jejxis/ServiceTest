package com.example.servicetest

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
/////////////////스타티드 서비스
    fun serviceStart(view: View){//View를 사용하면 클릭리스너 연결이 없어도 레이아웃 파일에서 메서드에 직접 접근할 수 있음.
        val intent = Intent(this, MyService::class.java)//Intent 생성
        intent.action = MyService.ACTION_START//MyService 에 정의해둔 명령을 같이 전달
        startService(intent)
    }

    fun serviceStop(view: View){
        val intent = Intent(this, MyService::class.java)
        stopService(intent)
    }
    /////////////////바운드 서비스
    var myService: MyService? = null
    var isService = false//서비스가 연결되었는지 확인하는 변수
    val connection = object: ServiceConnection{
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {//서비스가 연결되면 호출된다.
            val binder = service as MyService.MyBinder
            myService = binder.getService()
            isService = true//연결됐다.

            Log.d("BoundService", "연결되었습니다.")
        }

        override fun onServiceDisconnected(name: ComponentName?) {//비정상적으로 서비스가 종료되었을 때 호출.
            isService = false//끊어짐.
        }
    }
    fun serviceBind(view: View){
        val intent = Intent(this, MyService::class.java)
        bindService(intent, connection, Context.BIND_AUTO_CREATE)//서비스가 생성되어 있지 않으면 생성 후 바인딩, 생성되어 있으면 바로 바인딩
    }
    fun serviceUnbind(view: View){
        if(isService){
            unbindService(connection)
            isService = false
        }
    }
    ///서비스 메서드 호출하기. 바운드 서비스(스타티드 서비스x)는 액티비티에서 서비스의 메서드를 직접 호출해 사용할 수 있음
    fun callServiceFunction(view: View){
        if(isService){
            val message = myService?.serviceMessage()
            Toast.makeText(this, "message = $message", Toast.LENGTH_SHORT).show()
        } else{
            Toast.makeText(this, "서비스가 연결되지 않았습니다.", Toast.LENGTH_SHORT).show()
        }
    }
}