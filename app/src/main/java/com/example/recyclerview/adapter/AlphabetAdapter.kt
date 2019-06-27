package com.example.recyclerview.adapter

import android.content.Context
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.recyclerview.R
import com.example.recyclerview.interfaces.AlphabetListener
import com.example.recyclerview.model.Alphabet
import kotlinx.android.synthetic.main.item.view.*

class AlphabetAdapter(private val context: Context,
                      private val characters: ArrayList<Alphabet>,
                      private val listener: AlphabetListener) :
    RecyclerView.Adapter<AlphabetAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.alphabet_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = characters.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(characters[position],listener)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvCharacter:AppCompatTextView = itemView.findViewById(R.id.tvCharacter)

        fun bind(alphabet: Alphabet , listener: AlphabetListener) = with(itemView){
            itemView.tvCharacter.text = alphabet.character
            itemView.setOnClickListener {
                listener.onCharacterSelected(alphabet)
                notifyDataSetChanged()
            }
        }
    }
}