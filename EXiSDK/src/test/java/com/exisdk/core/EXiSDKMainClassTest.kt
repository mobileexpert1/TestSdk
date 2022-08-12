package com.exisdk.core

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.exisdk.utils.Constants
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class EXiSDKMainClassTest : TestCase() {
    lateinit var context:Context
    lateinit var mainclass: EXiSDKMainClass
   // val context1 = mock(Context::class.java)
//   val activityRule =  EXiSDKMainClass::class.java


    @get:Rule
    val activityRule = EXiSDKMainClass::class.java

    @Before
    public override fun setUp() {
        super.setUp()
        mainclass = EXiSDKMainClass
//        context=mainclass.applicationContext
    }

    @Test
    fun testSdkConfiguration() {
        val context=ApplicationProvider.getApplicationContext<Context>()
        val result = mainclass.sdkConfiguration(Constants.EXi_UNIQUE_KEY,activityRule.newInstance().applicationContext)
        assert(result).equals(true)

    }

    @Test
    fun isUniqueKeyEmpty(){
      //  val expected = Constants.EXi_UNIQUE_KEY
    }

}