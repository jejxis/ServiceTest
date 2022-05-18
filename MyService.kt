package com.example.servicetest

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyService : Service() {

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
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
}