package com.example.madlevel5task2.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.madlevel5task2.model.Game
import com.example.madlevel5task2.repository.GamesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GameViewModel(application: Application) : AndroidViewModel(application) {
    private val ioScope = CoroutineScope(Dispatchers.IO)
    private val gamesRepository = GamesRepository(application.applicationContext)

    val games: LiveData<List<Game>> = gamesRepository.getAllGames()

    fun insertGame(game: Game) {
        ioScope.launch {
            gamesRepository.insertGame(game)
        }
    }

    fun deleteGame(game: Game) {
        ioScope.launch {
            gamesRepository.deleteGame(game)
        }
    }

    fun deleteAllGames() {
        ioScope.launch {
            gamesRepository.deleteAllGames()
        }
    }
}