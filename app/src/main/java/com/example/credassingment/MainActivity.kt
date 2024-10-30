package com.example.credassingment

import android.os.Bundle
import android.view.View
import android.widget.ImageView

import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

import com.example.credassingment.databinding.ActivityMainBinding




class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set initial states
        toggleVisibility(binding.button1, binding.dropdownIcon1, isVisible = true)
        toggleVisibility(binding.button2, binding.dropdownIcon2, isVisible = false)
        toggleVisibility(binding.button3, binding.dropdownIcon3, isVisible = false)

        // Set up expand/collapse listeners with exclusive visibility for each item
        binding.dropdownIcon1.setOnClickListener { expandItem(1) }
        binding.dropdownIcon2.setOnClickListener { expandItem(2) }
        binding.dropdownIcon3.setOnClickListener { expandItem(3) }
    }

    private fun expandItem(item: Int) {
        when (item) {
            1 -> {
                toggleVisibility(binding.button1, binding.dropdownIcon1, isVisible = true)
                toggleVisibility(binding.button2, binding.dropdownIcon2, isVisible = false)
                toggleVisibility(binding.button3, binding.dropdownIcon3, isVisible = false)
            }
            2 -> {
                toggleVisibility(binding.button1, binding.dropdownIcon1, isVisible = false)
                toggleVisibility(binding.button2, binding.dropdownIcon2, isVisible = true)
                toggleVisibility(binding.button3, binding.dropdownIcon3, isVisible = false)
            }
            3 -> {
                toggleVisibility(binding.button1, binding.dropdownIcon1, isVisible = false)
                toggleVisibility(binding.button2, binding.dropdownIcon2, isVisible = false)
                toggleVisibility(binding.button3, binding.dropdownIcon3, isVisible = true)
            }
        }
    }

    private fun toggleVisibility(button: View, icon: ImageView, isVisible: Boolean) {
        button.visibility = if (isVisible) View.VISIBLE else View.GONE
        icon.setImageResource(
            if (isVisible) R.drawable.baseline_keyboard_arrow_down_24 else R.drawable.baseline_keyboard_arrow_up_24  // Use appropriate icons
        )
    }
}


