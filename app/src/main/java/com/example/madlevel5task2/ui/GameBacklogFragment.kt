package com.example.madlevel5task2.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel5task2.R
import com.example.madlevel5task2.model.Game
import kotlinx.android.synthetic.main.fragment_game_backlog.*

class GameBacklogFragment : Fragment() {
    private val games = arrayListOf<Game>()
    private val gamesAdapter = GamesAdapter(games)

    private val viewModel: GameViewModel by viewModels()

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
        observeAddGamesResult()
    }

    private fun initViews() {
        // Initialize the recycler view with a linear layout manager and connect adapter to recycler view
        rvGames.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvGames.adapter = gamesAdapter

        fabAddGame.setOnClickListener{
            findNavController().navigate(R.id.addGameFragment)
        }
    }

    // observes the live data from the view model. Whenever the live data changes, the recycler view is updated
    private fun observeAddGamesResult() {
        viewModel.games.observe(viewLifecycleOwner, Observer {
                games ->
            this@GameBacklogFragment.games.clear()
            this@GameBacklogFragment.games.addAll(games)
            this.gamesAdapter.notifyDataSetChanged()
        })
    }
}