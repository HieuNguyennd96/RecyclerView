package com.example.recyclerview.interfaces

import com.example.recyclerview.model.Alphabet

interface AlphabetListener {
    fun onCharacterSelected(alphabet: Alphabet)
}