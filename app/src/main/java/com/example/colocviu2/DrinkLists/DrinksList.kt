package com.example.colocviu2.DrinkLists

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.colocviu2.Adapters.DrinksAdapter
import com.example.colocviu2.Details.DrinkDetailsActivity
import com.example.colocviu2.MainActivity
import com.example.colocviu2.Models.Drink
import com.example.colocviu2.Models.Drinks
import com.example.colocviu2.Models.FavoritesList
import com.example.colocviu2.R
import com.example.colocviu2.Requests.DrinksApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DrinksList : AppCompatActivity() {

    lateinit var drinksAdapter: DrinksAdapter
    lateinit var linearLayoutManager: LinearLayoutManager
    var requestAppend : String = ""

    var searchMethodIndex = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drinks_list)

        val recyclerViewUsers = findViewById<RecyclerView>(R.id.recyclerview_drinks)
        recyclerViewUsers.setHasFixedSize(true)
        recyclerViewUsers.layoutManager = LinearLayoutManager(this)


        getDrinksFromApi()
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)

        val menuItem = menu?.findItem(R.id.search)
        val searchView = menuItem?.actionView as SearchView
        searchView.queryHint = "Search here"

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                searchView.clearFocus()

                if (query != null) {
                    requestAppend = query
                    getDrinksFromApi()
                }

                drinksAdapter.notifyDataSetChanged()

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    requestAppend = newText
                    getDrinksFromApi()
                }
                drinksAdapter.notifyDataSetChanged()
                return true
            }

        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.sign_out ->{


                val signOutBox = AlertDialog.Builder(this)
                signOutBox.setTitle("Are you sure you want to sign out?")
                    .setPositiveButton("Sign out") { _, _ ->
                        finish()
                    }
                    .setNegativeButton("Cancel", null)
                    .create()
                    .show()
            }
            R.id.favorites_list ->{
                val intent = Intent(this, FavoritesListActivity::class.java)
                startActivity(intent)
            }
            R.id.clear_favorites -> {

                val signOutBox = AlertDialog.Builder(this)
                signOutBox.setTitle("Are you sure you want to clear the favorites list?")
                    .setPositiveButton("Confirm") { _, _ ->
                        FavoritesList.drinks.clear()
                    }
                    .setNegativeButton("Cancel", null)
                    .create()
                    .show()
            }

            R.id.search_by_name ->{
                searchMethodIndex = 1
                getDrinksFromApi()
                drinksAdapter.notifyDataSetChanged()
            }

            R.id.search_by_first_letter ->{
                searchMethodIndex = 2
                getDrinksFromApi()
                drinksAdapter.notifyDataSetChanged()
            }

            R.id.search_random ->{
                searchMethodIndex = 3
                getDrinksFromApi()
                drinksAdapter.notifyDataSetChanged()
            }
        }

        return super.onOptionsItemSelected(item)
    }


    private fun getDrinksFromApi() {

        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://www.thecocktaildb.com/")
            .build()
            .create(DrinksApi::class.java)

        val retrofitData : Call<Drinks>
        if(searchMethodIndex == 1)
            retrofitData = retrofitBuilder.searchCocktailByName(requestAppend)
        else if(searchMethodIndex == 2)
             retrofitData = retrofitBuilder.searchCocktailByFirstLetter(requestAppend)
        else
             retrofitData = retrofitBuilder.searchCocktailRandom()

        retrofitData.enqueue(object : Callback<Drinks?> {

            override fun onResponse(call: Call<Drinks?>, response: Response<Drinks?>) {
                val responseBody = response.body()!!


                drinksAdapter = DrinksAdapter(
                    baseContext,
                    responseBody,
                    object : DrinksAdapter.onItemClickListener {

                        override fun onItemClick(position: Int) {

                            val drink = drinksAdapter.getItem(position) as Drink

                            val intent = Intent(this@DrinksList,
                                DrinkDetailsActivity::class.java)
                            intent.putExtra("drink_position", position)
                            intent.putExtra("drink_title", drink.strDrink)
                            intent.putExtra("drink_image", drink.strDrinkThumb)

                            var ingredientsList: String = drink.strInstructions + "\n" +
                                   "\n" + "Ingredients:" + "\n"
                            if (drink.strIngredient1 != null )
                                ingredientsList += drink.strIngredient1 + " : " +
                                        drink.strMeasure1 + "\n"
                            if (drink.strIngredient2 != null )
                                ingredientsList += drink.strIngredient2 +
                                        " : " + drink.strMeasure2 + "\n"
                            if (drink.strIngredient3 != null )
                                ingredientsList += drink.strIngredient3 +
                                        " : " + drink.strMeasure3 + "\n"
                            if (drink.strIngredient4 != null )
                                ingredientsList += drink.strIngredient4 +
                                        " : " + drink.strMeasure4 + "\n"
                            if (drink.strIngredient5 != null )
                                ingredientsList += drink.strIngredient5 +
                                        " : " + drink.strMeasure5 + "\n"
                            if (drink.strIngredient6 != null )
                                ingredientsList += drink.strIngredient6 +
                                        " : " + drink.strMeasure6 + "\n"
                            if (drink.strIngredient7 != null )
                                ingredientsList += drink.strIngredient7 +
                                        " : " + drink.strMeasure7 + "\n"
                            if (drink.strIngredient8 != null )
                                ingredientsList += drink.strIngredient8 +
                                        " : " + drink.strMeasure8 + "\n"
                            if (drink.strIngredient9 != null )
                                ingredientsList += drink.strIngredient9 +
                                        " : " + drink.strMeasure9 + "\n"
                            if (drink.strIngredient10 != null )
                                ingredientsList += drink.strIngredient10 +
                                        " : " + drink.strMeasure10 + "\n"
                            if (drink.strIngredient11 != null )
                                ingredientsList += drink.strIngredient11 +
                                        " : " + drink.strMeasure11 + "\n"
                            if (drink.strIngredient12 != null )
                                ingredientsList += drink.strIngredient12 +
                                        " : " + drink.strMeasure12 + "\n"
                            if (drink.strIngredient13 != null )
                                ingredientsList += drink.strIngredient13 +
                                        " : " + drink.strMeasure13 + "\n"
                            if (drink.strIngredient14 != null )
                                ingredientsList += drink.strIngredient14 +
                                        " : " + drink.strMeasure14 + "\n"
                            if (drink.strIngredient15 != null )
                                ingredientsList += drink.strIngredient15 +
                                        " : " + drink.strMeasure15 + "\n"

                            intent.putExtra("drink_ingredients", ingredientsList)

                            startActivity(intent)


                        }

                        override fun onDeleteClick(position: Int) {
                            drinksAdapter.removeItem(position)
                        }

                    })
                drinksAdapter.notifyDataSetChanged()


                val recyclerDrinks = findViewById<RecyclerView>(R.id.recyclerview_drinks)
                recyclerDrinks.adapter = drinksAdapter

            }

            override fun onFailure(call: Call<Drinks?>, t: Throwable) {
                Log.d("ERAORE LA GET", "onFailure: $t")
            }
        })
    }
}




