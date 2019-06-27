package com.example.recyclerview.adapter

import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.recyclerview.R
import com.example.recyclerview.model.DisplaySong

class SongAdapter(
    private val displaySongs: ArrayList<DisplaySong>
) : RecyclerView.Adapter<SongAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val songName = displaySongs[position].songName.trim()
        holder.tvSongName.text = songName
        holder.tvSinger.text = displaySongs[position].singer
        when(position){
            0 -> {
                holder.showCharacter(displaySongs[position].group,View.VISIBLE)
            }
            else -> {
                val previous = displaySongs[position-1].group
                val current = displaySongs[position].group
                if(current != previous){
                    holder.showCharacter(displaySongs[position].group,View.VISIBLE)
                }else{
                    holder.showCharacter(displaySongs[position].group,View.GONE)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = displaySongs.size


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvCharacter: AppCompatTextView = itemView.findViewById(R.id.tvCharacter)
        val tvSongName: AppCompatTextView = itemView.findViewById(R.id.tvSongName)
        val tvSinger: AppCompatTextView = itemView.findViewById(R.id.tvSinger)

        fun showCharacter(character: String, visibility: Int){
            tvCharacter.text = character
            tvCharacter.visibility = visibility
        }
    }
}