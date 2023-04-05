package com.example.get_response_from_api

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val BaseURL = "https://api.currencyapi.com/"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val currencies = arrayOf("USD", "EUR", "UAH", "RUB", "CAD", "CNY")
        getCurrencyData(currencies)
    }

    private fun getCurrencyData(currencies:Array<String>) {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BaseURL).build().create(GetCurrencyData::class.java)
        val passedCurrencies = currencies.joinToString (separator = ",")
        val retrofitData = retrofitBuilder.getData(passedCurrencies)

        retrofitData.enqueue(object : Callback<CurrencyData?> {
            override fun onResponse(call: Call<CurrencyData?>, response: Response<CurrencyData?>) {
                Log.d(TAG, "Response: " + response.raw().request().url())
                val responseBody = response.body()!!
                println(responseBody.meta)
                println(responseBody.data.printNonNull())
            }
            override fun onFailure(call: Call<CurrencyData?>, t: Throwable) {
                Log.d(TAG, "onFailure: " + t.message)
            }
        })
    }
}
