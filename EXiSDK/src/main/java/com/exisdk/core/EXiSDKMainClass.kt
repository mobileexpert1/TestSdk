package com.exisdk.core

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.exisdk.apis.ApiInterface
import com.exisdk.apis.RetrofitHelper
import com.exisdk.models.CheckCredentialsResponse
import com.exisdk.utils.CheckInternet
import com.exisdk.utils.Constants
import com.exisdk.utils.DialogAlert
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object EXiSDKMainClass /*: Application() */{

    fun sdkConfiguration(uniqueKey: String,context:Context,jwsToken:String) : Boolean {
        var isConfigured : Boolean
        if (uniqueKey == Constants.EXi_UNIQUE_KEY) {

            callApi(context,jwsToken,{
                Log.d("Response@#@#", it.toString())
            },{
                Log.d("ERROR@#@#", it)
            })
            isConfigured=true
            DialogAlert.showAlert(context,true)
        }
         else {

            isConfigured=false
            DialogAlert.showAlert(context,false)
        }
            return isConfigured
    }


fun callApi(c: Context,jwsToken: String, successHandler: (CheckCredentialsResponse) -> Unit, failureHandler: (String) -> Unit)/*: Response<QuoteList>*/ {
    val checkInternet = CheckInternet()
    val checkSdkApi = RetrofitHelper.getInstance(jwsToken).create(ApiInterface::class.java)
    if (checkInternet.isNetworkAvailable(c)) {
        checkSdkApi.checkCredentials().enqueue(object: Callback<CheckCredentialsResponse>{
            override fun onResponse(call: Call<CheckCredentialsResponse>, response: Response<CheckCredentialsResponse>) {
                successHandler(response.body()!!)
            }
            override fun onFailure(call: Call<CheckCredentialsResponse>, t: Throwable) {
                failureHandler(t.message.toString())
            }
        })
    } else {
       // failureHandler("Error")
       // Log.e("No internet found", "No internet found")
        Toast.makeText(c,"No Internet Found",Toast.LENGTH_LONG).show()
    }
}


}
