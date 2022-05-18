package com.example.servicetest

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

class MyService : Service() {

    override fun onBind(intent: Intent): IBinder {
        return binder
    }
    //////////스타티드
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val action = intent?.action
        Log.d("StartedService", "action = $action")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Log.d("Service", "서비스가 종료되었습니다.")
        super.onDestroy()
    }
    companion object{//액티비티에서 서비스 호출할 때 사용
        val ACTION_START = "com.example.servicetest.START"//패키지명+명령어
        val ACTION_RUN = "com.example.servicetest.RUN"
        val ACTION_STOP = "com.example.servicetest.STOP"
    }
    /////////////바운드
    inner class MyBinder: Binder(){
        fun getService(): MyService{
            return this@MyService
        }
    }
    val binder = MyBinder()
    ///서비스 메서드 호출하기. 바운드 서비스(스타티드 서비스x)는 액티비티에서 서비스의 메서드를 직접 호출해 사용할 수 있음
    fun serviceMessage(): String{
        return "Hello Activity! I am Service!"
    }
}