package com.exisdk.utils

import android.content.Context
import junit.framework.TestCase
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DialogAlertTest : TestCase() {

    @Mock
    private lateinit var mockContext: Context
    public override fun setUp() {
        super.setUp()
    }
    @Test
    fun testShowAlert() {
        DialogAlert.showAlert(mockContext,true)
    }
}