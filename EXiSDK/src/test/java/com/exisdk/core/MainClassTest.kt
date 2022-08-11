package com.exisdk.core

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.exisdk.utils.Constants
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test

class MainClassTest : TestCase() {
    lateinit var mainclass: MainClass
    //lateinit var context: Context
    lateinit var instrumentationContext: Context
    @Before
    public override fun setUp() {
        super.setUp()
        mainclass = MainClass
//        instrumentationContext = InstrumentationRegistry.getInstrumentation().context
        //context = ApplicationProvider.getApplicationContext<Context>()
    }

    @Test
    fun testSdkConfiguration() {

        val expected = Constants.UNIQUE_KEY
        val result = mainclass.sdkConfiguration("123", mainclass.appContext)
        assertEquals(expected, 12)
    }

    @Test
    fun isUniqueKeyEmpty(){
        val expected = Constants.UNIQUE_KEY


    }


}