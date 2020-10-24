package com.example.madlevel5task2.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.madlevel5task2.R
import com.example.madlevel5task2.model.Game
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_game_backlog.*

class GameBacklogFragment : Fragment() {
    private val games = arrayListOf<Game>()
    private val gamesAdapter = GamesAdapter(games)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_backlog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        fabAddGame.setOnClickListener{
            findNavController().navigate(R.id.addGameFragment)
        }
    }
}