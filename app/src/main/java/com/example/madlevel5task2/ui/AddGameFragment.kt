package com.example.madlevel5task2.ui

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
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

    private fun initViews() {
        fabSaveGame.setOnClickListener{
            onAddGame()
        }
    }

    // adds a game to the database
    private fun onAddGame() {
        if (validate()) {
            // different fields making a game
            val gameTitle = etGameTitle.text.toString()
            val gamePlatform = etPlatform.text.toString()
            val releaseDate = Date(etYear.text.toString().toInt() - 1900, etMonth.text.toString().toInt() - 1, etDay.text.toString().toInt())

            // adding game to the database
            val game = Game(gameTitle, gamePlatform, releaseDate)
            viewModel.insertGame(game)
            findNavController().navigate(R.id.gameBacklogFragment) // return to game backlog
        }
    }

    private fun validate() : Boolean{
        var isTitleFilled = false
        var isPlatformFilled = false
        var isDateFilled = false

        if (etGameTitle.text?.isNotEmpty()!!) {
            isTitleFilled = true
        } else {
            tilGameTitle.error = "Please fill in a title"
        }

        if (etPlatform.text?.isNotEmpty()!!) {
            isPlatformFilled = true
        } else {
            tilPlatform.error = "Please fill in a gaming platform"
        }

        if (etDay.text?.isNotEmpty()!! || etMonth.text?.isNotEmpty()!! || etYear.text?.isNotEmpty()!!) {
            isDateFilled = true
        } else {
            Toast.makeText(context, "Please fill in a date", Toast.LENGTH_SHORT)
        }

        return false
    }
}