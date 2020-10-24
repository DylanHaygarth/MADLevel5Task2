package com.example.madlevel5task2.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.madlevel5task2.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        navController = findNavController(R.id.nav_host_fragment)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        menuToggler(menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                navController.navigate(R.id.gameBacklogFragment) // return to previous screen when back button is pressed
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // Changes title in toolbar and removes/adds toolbar icons
    private fun menuToggler(menu: Menu) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id in arrayOf(R.id.addGameFragment)) {
                supportActionBar?.title = "Add Game"
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                menu.findItem(R.id.btnDelete).isVisible = false
            } else {
                supportActionBar?.title = "Game Backlog"
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
                menu.findItem(R.id.btnDelete).isVisible = true
            }
        }
    }
}