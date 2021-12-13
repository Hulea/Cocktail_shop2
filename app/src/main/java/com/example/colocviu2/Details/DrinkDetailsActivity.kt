package com.example.colocviu2.Details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.bumptech.glide.Glide
import com.example.colocviu2.Models.Drink
import com.example.colocviu2.Models.DrinkDetails
import com.example.colocviu2.Models.FavoritesList
import com.example.colocviu2.R
import com.squareup.picasso.Picasso

class DrinkDetailsActivity : AppCompatActivity() {

    private lateinit var title : String
    private lateinit var image : String
    private lateinit var ingredients : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drink_details)



        setDetailsContent()

        val favoriteButton = findViewById<ImageButton>(R.id.favorites_button)
        favoriteButton.setOnClickListener{

            var ok = true
            FavoritesList.drinks.forEach{
                if(it.title == title)
                    ok = false
            }

            if(ok == true) {
                FavoritesList.drinks.add(
                    DrinkDetails(title, image, ingredients)
                )

                Toast.makeText(applicationContext, "Drink added to favorites"
                    , Toast.LENGTH_SHORT).show()

            }

        }

    }


    private fun setDetailsContent() {
        title = intent.getStringExtra("drink_title").toString()
        image = intent.getStringExtra("drink_image").toString()
        ingredients = intent.getStringExtra("drink_ingredients").toString()

        val actionBar = supportActionBar
        actionBar!!.title = title
        actionBar.setDisplayHomeAsUpEnabled(true)


        val xmlTitle = findViewById<TextView>(R.id.drink_details_title)
        xmlTitle.text = title

        val xmlImage = findViewById<ImageView>(R.id.drink_details_image)
        Picasso.with(this)
            .load(image)
            .into(xmlImage)


        val xmlIngredients = findViewById<TextView>(R.id.drink_details_description)
        xmlIngredients.text = ingredients
    }

}