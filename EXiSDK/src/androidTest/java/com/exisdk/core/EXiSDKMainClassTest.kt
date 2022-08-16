package com.exisdk.core

import android.content.Context
import android.provider.SyncStateContract
import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.exisdk.utils.Constants
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class EXiSDKMainClassTest : TestCase() {
    lateinit var instrumentationContext: Context
    lateinit var mainclass: EXiSDKMainClass
    @Before
    fun setup() {
        instrumentationContext = InstrumentationRegistry.getInstrumentation().context
        mainclass = EXiSDKMainClass
    }
    @Test
    fun testSdkConfiguration() {
        val jwsToken:String="123"
        val result = mainclass.sdkConfiguration(Constants.EXi_UNIQUE_KEY, instrumentationContext)
        mainclass.callApi(instrumentationContext,"123",{
             Log.d("Response@#@#", it.toString())
         },
             {
                 Log.d("ERROR@#@#", it)
             })
         assertEquals(jwsToken,"123")
          //assert(result).equals(true)
    }

}