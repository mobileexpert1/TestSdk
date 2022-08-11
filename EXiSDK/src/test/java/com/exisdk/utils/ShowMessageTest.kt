package com.exisdk.utils

import junit.framework.TestCase
import org.junit.Before
import org.junit.Test

class ShowMessageTest : TestCase() {
@Before
    public override fun setUp() {
        super.setUp()
    }

    public override fun tearDown() {}

    @Test
    fun testShowMessage() {
        val expected = 1
        assertEquals(expected,2)
    }
}
