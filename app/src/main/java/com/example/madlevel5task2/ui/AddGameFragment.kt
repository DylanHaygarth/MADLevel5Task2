package com.example.madlevel5task2.ui

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.madlevel5task2.R
import com.example.madlevel5task2.model.Game
import kotlinx.android.synthetic.main.fragment_add_game.*
import java.util.*

class AddGameFragment : Fragment() {
    private val viewModel: GameViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
        initViews()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    private fun initViews() {
        fabSaveGame.setOnClickListener{
            onAddGame()
        }
    }

    // adds a game to the database
    private fun onAddGame() {
        val gameTitle = etGameTitle.text.toString()
        val gamePlatform = etPlatform.text.toString()
        val releaseDate = Date(etYear.text.toString().toInt(), etMonth.text.toString().toInt(), etDay.text.toString().toInt())

        val game = Game(gameTitle, gamePlatform, releaseDate)
        viewModel.insertGame(game)
    }
}