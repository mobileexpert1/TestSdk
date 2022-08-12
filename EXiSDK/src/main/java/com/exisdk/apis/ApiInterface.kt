
package com.exisdk.apis

import com.exisdk.models.CheckCredentialsResponse
import retrofit2.Call
import retrofit2.http.GET


interface ApiInterface {
      @GET("checkSdkCredentials")
      fun checkCredentials():Call<CheckCredentialsResponse>
}
