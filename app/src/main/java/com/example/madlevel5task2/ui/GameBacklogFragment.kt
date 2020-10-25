package com.example.madlevel5task2.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel5task2.R
import com.example.madlevel5task2.model.Game
import com.google.android.material.snackbar.Snackbar
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.btnDelete -> {
                viewModel.deleteAllGames() // deletes all game from backlog when button is pressed
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
        initViews()
        observeAddGamesResult()
    }

    private fun initViews() {
        // Initialize the recycler view with a linear layout manager and connect adapter to recycler view
        rvGames.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvGames.adapter = gamesAdapter

        // adds item touch helper to the recycler view
        createItemTouchHelper().attachToRecyclerView(rvGames)

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
            this@GameBacklogFragment.games.sortBy { game -> game.releaseDate } // sorts the games in ascending order (first to be released at top)
            this.gamesAdapter.notifyDataSetChanged()
        })
    }

    // when the user swipes a game to the left it will be deleted
    private fun createItemTouchHelper(): ItemTouchHelper {
        var callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val gameToDelete = games[position]

                viewModel.deleteGame(gameToDelete)
                undoMessage()
            }
        }

        return ItemTouchHelper(callback)
    }

    private fun undoMessage() {
        val undoMessage = Snackbar.make(rvGames, "test", Snackbar.LENGTH_SHORT)
        undoMessage.setAction("Undo", UndoListener())

        undoMessage.show()
    }
}