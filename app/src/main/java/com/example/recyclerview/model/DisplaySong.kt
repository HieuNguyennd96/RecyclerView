package com.example.recyclerview.model

import com.example.recyclerview.util.CharacterGroup

data class DisplaySong (var singer: String,
                        var songName:String){
    val group = CharacterGroup.getGroup(songName.trim().substring(0,1))
}