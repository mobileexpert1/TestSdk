package com.exisdk_container

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.exisdk.core.MainClass
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainActivity : Activity(), CoroutineScope {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var jwtRsaTest=JwtRsaTest()
        jwtRsaTest.testJWTWithRsa()

        /*GlobalScope.launch {*/
            /*val result =*/ MainClass.sdkConfiguration("1234",this@MainActivity)
          //  Log.e("result@@@",result.body()?.results?.get(0).toString())
            /*if(result==null){
                Log.e("connection","established")
            }*/
        /*}*/
    }

    override val coroutineContext: CoroutineContext
        get() = TODO("Not yet implemented")
}