package com.example.colocviu2.Requests

import com.example.colocviu2.Models.Drink
import com.example.colocviu2.Models.Drinks
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DrinksApi {

    @GET("api/json/v1/1/search.php")
    fun searchCocktailByName(@Query("s")name : String): Call<Drinks>

    @GET("api/json/v1/1/search.php")
    fun searchCocktailByFirstLetter(@Query("f")letter : String) : Call<Drinks>

    @GET("api/json/v1/1/random.php")
    fun searchCocktailRandom() : Call<Drinks>

}