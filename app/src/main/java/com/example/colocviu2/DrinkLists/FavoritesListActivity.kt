package com.example.colocviu2.DrinkLists

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.colocviu2.Adapters.FavoritesAdapter
import com.example.colocviu2.Details.FavoriteDetailsActivity
import com.example.colocviu2.Models.Drink
import com.example.colocviu2.Models.DrinkDetails
import com.example.colocviu2.Models.FavoritesList
import com.example.colocviu2.R

class FavoritesListActivity : AppCompatActivity() {

    lateinit var favoritesAdapter: FavoritesAdapter
    lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites_list)


        val recyclerViewUsers = findViewById<RecyclerView>(R.id.recyclerview_favorites)
        recyclerViewUsers.setHasFixedSize(true)
        recyclerViewUsers.layoutManager = LinearLayoutManager(this)

        getFavorites()


    }

    private fun getFavorites() {


        favoritesAdapter = FavoritesAdapter(
            baseContext,
            FavoritesList,
            object : FavoritesAdapter.onItemClickListener {

                override fun onItemClick(position: Int) {

                    val drink = favoritesAdapter.getItem(position) as DrinkDetails

                    val intent = Intent(
                        this@FavoritesListActivity,
                        FavoriteDetailsActivity::class.java
                    )
                    intent.putExtra("drink_position", position)
                    intent.putExtra("drink_title", drink.title)
                    intent.putExtra("drink_image", drink.image)
                    intent.putExtra("drink_ingredients", drink.ingredients)

                    startActivity(intent)
                }

                override fun onDeleteClick(position: Int) {
                    favoritesAdapter.removeItem(position)
                    Toast.makeText(this@FavoritesListActivity,"Drink removed from favorites",Toast.LENGTH_SHORT).show()
                }

            })
        favoritesAdapter.notifyDataSetChanged()

        val favoritesRecycler = findViewById<RecyclerView>(R.id.recyclerview_favorites)
        favoritesRecycler.adapter = favoritesAdapter

    }
}