package com.example.presentation.ui.movie

import android.os.Bundle
import androidx.navigation.Navigation.findNavController
import com.example.presentation.R
import com.example.presentation.base.BaseActivity

class MovieActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
    }

    override fun onSupportNavigateUp() =
        findNavController(this, R.id.navHostFragment).navigateUp()
}