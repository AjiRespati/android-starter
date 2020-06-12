package com.utek.android.utekapp.screen.account

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DataApi
{
@GET("data.php")
fun getData(@Query("index") index : Int): Call<List<Data>>
}