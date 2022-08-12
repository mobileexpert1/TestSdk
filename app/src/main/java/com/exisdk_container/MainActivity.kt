package com.exisdk_container

import android.app.Activity
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.exisdk.core.EXiSDKMainClass

class MainActivity : Activity(){
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var jwtRsaTest=JwtRsaTest()
        val token=jwtRsaTest.testJWTWithRsa()
        println("token@#@# "+token)
        EXiSDKMainClass.sdkConfiguration("1234",this@MainActivity,"")
    }
}