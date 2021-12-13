package com.example.colocviu2.Adapters

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.colocviu2.Models.FavoritesList
import com.example.colocviu2.R
import com.squareup.picasso.Picasso

class FavoritesAdapter(
    val context: Context,
    var drinksObj: FavoritesList,
    private var fListener: onItemClickListener
) :
    RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {

    interface onItemClickListener{
        fun onItemClick(position: Int)
        fun onDeleteClick(position: Int)
    }

    fun getItem(p0 : Int): Any = drinksObj.drinks[p0]

    fun removeItem(p0 : Int)  {
        drinksObj.drinks.removeAt(p0)
        notifyDataSetChanged()
    }

    class FavoritesViewHolder(
        favoritesView: View,
        listener: onItemClickListener
    ) : RecyclerView.ViewHolder(favoritesView) {

        var favTitle: TextView
        var favDesc: TextView
        var favImg: ImageView
        var deleteFavElementFromDrinks : ImageButton

        init{

            favoritesView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }

            favTitle = favoritesView.findViewById<TextView>(R.id.favorite_title)
            favDesc = favoritesView.findViewById<TextView>(R.id.favorite_description)
            favImg = favoritesView.findViewById<ImageView>(R.id.favorite_image)

            deleteFavElementFromDrinks = favoritesView.
                findViewById<ImageButton>(R.id.favorite_delete_button)
            deleteFavElementFromDrinks.setOnClickListener {
                listener.onDeleteClick(adapterPosition)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {

        var favoritesView = LayoutInflater.from(context)
            .inflate(R.layout.favorite_item,parent,false)
        return FavoritesViewHolder(favoritesView, fListener)
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {

        holder.favTitle.text = drinksObj.drinks[position].title
        holder.favDesc.text = drinksObj.drinks[position].ingredients
        holder.favImg.setImageURI(Uri.parse(drinksObj.drinks[position].image))


        Picasso.with(context)
            .load(drinksObj.drinks[position].image)
            .into(holder.favImg)
    }

    override fun getItemCount(): Int {
        return drinksObj.drinks.size
    }


}