package com.hashiradev.fancycards.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.hashiradev.fancycards.App
import com.hashiradev.fancycards.databinding.ActivityMainBinding
import com.hashiradev.fancycards.util.Image

class MainActivity : AppCompatActivity() {

    private val binding by lazy {ActivityMainBinding.inflate(layoutInflater)}
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }
    private  val adapter by lazy { FancyCardAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.rvFancyCards.adapter = adapter
        getAllFancyCard()
        insertListners()
    }

    private fun insertListners() {
        binding.fabAdd.setOnClickListener {
            val intent = Intent(this@MainActivity, AddNewFancyCard::class.java)
            startActivity(intent)
        }

        adapter.listenerShare = {
            Image.share(this@MainActivity, it)
        }
    }

    private fun getAllFancyCard() {
        mainViewModel.getAll().observe(this, { fancyCards ->
            adapter.submitList(fancyCards)
        })
    }
}