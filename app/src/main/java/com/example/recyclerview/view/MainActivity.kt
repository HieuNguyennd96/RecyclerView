package com.example.recyclerview.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.TranslateAnimation
import android.widget.Toast
import com.example.recyclerview.R
import com.example.recyclerview.adapter.AlphabetAdapter
import com.example.recyclerview.adapter.SongAdapter
import com.example.recyclerview.interfaces.AlphabetListener
import com.example.recyclerview.model.Alphabet
import com.example.recyclerview.model.DisplaySong
import com.example.recyclerview.model.SampleData
import com.example.recyclerview.util.KeyBoardUtil
import com.example.recyclerview.util.RecyclerViewScroll
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AlphabetListener {

    private lateinit var displaySongs: ArrayList<DisplaySong>
    private lateinit var alphabetItems: ArrayList<Alphabet>
    private lateinit var songAdapter: SongAdapter
    private lateinit var alphabetAdapter: AlphabetAdapter
    private lateinit var viewManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initData()
        initUI()
    }

    private fun initData() {
        displaySongs = SampleData.songData
        displaySongs.sortBy { it.songName.toLowerCase() }
        alphabetItems = ArrayList()
        val strAlphabet = ArrayList<String>()

        for (i in 0 until displaySongs.size) {
            val songGroup = displaySongs[i].group

            if (!strAlphabet.contains(songGroup)) {
                strAlphabet.add(songGroup)
                alphabetItems.add(Alphabet(songGroup, i))
            }
        }
    }

    private fun initUI() {
        viewManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = viewManager
        songAdapter = SongAdapter(displaySongs)
        recyclerView.adapter = songAdapter

        alphabetRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        alphabetAdapter = AlphabetAdapter(this, alphabetItems, this)
        alphabetRecyclerView.adapter = alphabetAdapter

        var characters = ""
        for (char in alphabetItems) {
            characters += char.character + char.index
        }
        Log.e("Characters: ", characters)

        listContainer.setOnClickListener {
            if (edtSearch.requestFocus()) {
                edtSearch.clearFocus()
            }
        }

        edtSearch.setOnKeyListener(object : View.OnKeyListener {

            override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
                if (event?.action == KeyEvent.ACTION_DOWN) {
                    when (keyCode) {
                        KeyEvent.KEYCODE_ENTER -> {
                            KeyBoardUtil.hideKeyBoard(this@MainActivity, layoutMain)
                            findSong(edtSearch.text.toString())
                            return true
                        }
                    }
                }
                return false
            }
        })

        edtSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.toString() == "") {
                    songAdapter = SongAdapter(displaySongs)
                    recyclerView.adapter = songAdapter
                } else {
                    findSong(s.toString())
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })

        recyclerView.addOnScrollListener(object : RecyclerViewScroll() {
            override fun show() {
                showSearchBar()
            }

            override fun hide() {
                hideSearchBar()
            }
        })
    }

    private fun findSong(text: String) {
        val result = ArrayList<DisplaySong>()
        for (song in displaySongs) {
            if (song.songName.contains(text, true) || song.singer.contains(text, true)) {
                result.add(song)
            }
        }
        songAdapter = SongAdapter(result)
        recyclerView.adapter = songAdapter
    }

    override fun onCharacterSelected(alphabet: Alphabet) {
        Toast.makeText(this, alphabet.character, Toast.LENGTH_SHORT).show()
        recyclerView.smoothScrollToPosition(alphabet.index)
    }

    fun showSearchBar() {
        val slideInAnimation = TranslateAnimation(0f,0f,0f,100f)
        slideInAnimation.duration = 400
        slideInAnimation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationRepeat(animation: Animation?) { }

            override fun onAnimationEnd(animation: Animation?) {
                searchContainer.visibility = View.VISIBLE
            }

            override fun onAnimationStart(animation: Animation?) { }

        })
        searchContainer.startAnimation(slideInAnimation)

    }

    fun hideSearchBar() {
        val slideOutAnimation = TranslateAnimation(0f,0f,100f,0f)
        slideOutAnimation.duration = 400
        slideOutAnimation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationRepeat(animation: Animation?) { }

            override fun onAnimationEnd(animation: Animation?) {
                searchContainer.visibility = View.GONE
            }

            override fun onAnimationStart(animation: Animation?) { }

        })
        searchContainer.startAnimation(slideOutAnimation)
    }
}
