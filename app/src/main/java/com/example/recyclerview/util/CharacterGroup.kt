package com.example.recyclerview.util

class CharacterGroup {
    companion object {
        fun getGroup(firstCharacter: String): String {
            val char = firstCharacter.toLowerCase()
            return when {
                "a" <= char && char <= "z" -> char.toUpperCase()
                "0" <= char && char <= "9" -> "#"
                else -> "&"
            }
        }
    }
}