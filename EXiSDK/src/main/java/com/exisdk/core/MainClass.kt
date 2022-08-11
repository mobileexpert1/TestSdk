package com.exisdk.core

import android.content.Context
import android.util.Log
import com.exisdk.apis.QuotesApi
import com.exisdk.apis.RetrofitHelper
import com.exisdk.models.QuoteList
import com.exisdk.utils.CheckInternet
import com.exisdk.utils.Constants
import com.exisdk.utils.ShowMessage
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response

object MainClass {
    // Api call
    lateinit var internet: CheckInternet
    lateinit var appContext: Context


    fun sdkConfiguration(uniqueKey: String, context: Context) : Boolean {
        appContext=context
         val isConfigured : Boolean
        if (uniqueKey == Constants.UNIQUE_KEY) {
            isConfigured=true
            ShowMessage.showMessage(context, "Connected", 1)
        }
         else {
            isConfigured=false
            ShowMessage.showMessage(context, "Connection Failed", 1)
        }
            return isConfigured

    }

    suspend fun callApi(c: Context): Response<QuoteList> {
        val checkInternet = CheckInternet()
        val quotesApi = RetrofitHelper.getInstance().create(QuotesApi::class.java)
        if (checkInternet.isNetworkAvailable(c)) {
            var result: Response<QuoteList>
            // launching a new coroutine
            GlobalScope.launch {
                result = quotesApi.getQuotes()
                if (result == null) {
                    Log.d("response@#@#", result.body().toString())
                }
            }
        } else {
            Log.e("No internet found", "No internet found")
        }

        return quotesApi.getQuotes()
    }
}
