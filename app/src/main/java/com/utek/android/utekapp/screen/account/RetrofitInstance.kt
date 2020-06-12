package com.utek.android.utekapp.screen.account

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitInstance {
    companion object {
        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://www.androidride.com/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        }
    }
}
