package com.example.get_response_from_api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

const val Key = "2RQ2gugowBwNyPSFQOqO0X9RavlBCO8ZuTuHzceN"

interface GetCurrencyData {
    @GET ("v3/latest?apikey=$Key")
    fun getData(@Query("currencies") passedCurrencies:String):Call <CurrencyData>
}
