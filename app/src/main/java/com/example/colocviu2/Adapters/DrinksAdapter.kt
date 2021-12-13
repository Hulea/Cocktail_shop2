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
import com.example.colocviu2.Models.Drink
import com.example.colocviu2.Models.Drinks
import com.example.colocviu2.R
import com.squareup.picasso.Picasso

class DrinksAdapter(
    val context: Context,
    val drinksObj: Drinks,
    private var mListener: onItemClickListener
) :
    RecyclerView.Adapter<DrinksAdapter.DrinksViewHolder>() {


    interface onItemClickListener{
        fun onItemClick(position: Int)
        fun onDeleteClick(position: Int)
    }

    fun getItem(p0 : Int): Any = drinksObj.drinks[p0]

    fun removeItem(p0 : Int)  {
        val asd : MutableList<Drink> = drinksObj.drinks as MutableList<Drink>
        asd.removeAt(p0)
        drinksObj.drinks = asd
        notifyDataSetChanged()
    }


    class DrinksViewHolder(
        drinksView: View,
        listener: onItemClickListener)
        : RecyclerView.ViewHolder(drinksView) {

        var drinkTitle: TextView
        var drinkDesc: TextView
        var drinkImg: ImageView
        //var deleteElementFromDrinks : ImageButton

        init{

            drinksView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }

            drinkTitle = drinksView.findViewById<TextView>(R.id.drink_title)
            drinkDesc = drinksView.findViewById<TextView>(R.id.description_drink)
            drinkImg = drinksView.findViewById<ImageView>(R.id.image_drink)

            /*deleteElementFromDrinks = drinksView.
            findViewById<ImageButton>(R.id.delete_button_menu)
            deleteElementFromDrinks.setOnClickListener {
                listener.onDeleteClick(adapterPosition)
            }*/
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinksViewHolder {

        var drinksView = LayoutInflater.from(context)
            .inflate(R.layout.drink_item, parent, false)
        return DrinksViewHolder(drinksView, mListener)

    }

    override fun onBindViewHolder(holder: DrinksViewHolder, position: Int) {
        holder.drinkTitle.text = drinksObj.drinks[position].strDrink
        holder.drinkDesc.text = drinksObj.drinks[position].strAlcoholic +
                " " + drinksObj.drinks[position].strCategory +
                "\n" + drinksObj.drinks[position].strGlass
        holder.drinkImg.setImageURI(Uri.parse(drinksObj.drinks[position].strDrinkThumb))


        Picasso.with(context)
            .load(drinksObj.drinks[position].strDrinkThumb)
            .into(holder.drinkImg)

    }

    override fun getItemCount(): Int {
        return drinksObj.drinks.size
    }
}

