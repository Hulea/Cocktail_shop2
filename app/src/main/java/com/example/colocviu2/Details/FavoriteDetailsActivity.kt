package com.example.colocviu2.Details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.colocviu2.R
import com.google.android.material.shape.RoundedCornerTreatment
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation

class FavoriteDetailsActivity : AppCompatActivity() {

    private lateinit var title : String
    private lateinit var image : String
    private lateinit var ingredients : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_details)

        setDetailsContent()
        configureDeleteButton()
        configureFavoriteButton()



    }


    private fun configureFavoriteButton() {
    }

    private fun configureDeleteButton() {

    }

    private fun setDetailsContent() {
        title = intent.getStringExtra("drink_title").toString()
        image = intent.getStringExtra("drink_image").toString()
        ingredients = intent.getStringExtra("drink_ingredients").toString()

        val actionBar = supportActionBar
        actionBar!!.title = title
        actionBar.setDisplayHomeAsUpEnabled(true)


        val xmlTitle = findViewById<TextView>(R.id.favorite_details_title)
        xmlTitle.text = title


        val xmlImage = findViewById<ImageView>(R.id.favorite_details_image)
        Picasso.with(this)
            .load(image)
            .into(xmlImage)


        val xmlIngredients = findViewById<TextView>(R.id.favorite_details_description)
        xmlIngredients.text = ingredients
    }
}