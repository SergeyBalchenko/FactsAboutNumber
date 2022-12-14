package com.example.factsaboutnumber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.factsaboutnumber.databinding.ActivityMainBinding
import com.example.factsaboutnumber.screen.chooseNumber.ChooseNumberFragment

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .add(R.id.container, ChooseNumberFragment.newInstance(), ChooseNumberFragment.TAG)
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}