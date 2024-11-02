package com.example.credassingment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.credassingment.api.ApiService
import com.example.credassingment.api.ItemViewModelFactory
import com.example.credassingment.api.Repository
import com.example.credassingment.api.RetrofitInstance

import com.example.credassingment.databinding.ActivityMainBinding
import com.example.credassingment.viewmodel.ItemViewModel
import me.tankery.lib.circularseekbar.CircularSeekBar


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize binding


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intiialise()

            val apiService = RetrofitInstance.apiService
            val repository = Repository(apiService)
            val viewModelFactory = ItemViewModelFactory(repository)
            viewModel = ViewModelProvider(this, viewModelFactory).get(ItemViewModel::class.java)


            viewModel.items.observe(this) { items ->

                    if (items.isNotEmpty()) {
                        binding.title1.text = items[0].open_state.body.title
                        binding.header1Txt.text = items[0].open_state.body.card.header
                        binding.pmsgText.text = items[0].open_state.body.card.description
                        binding.pmsg2Text.text = items[0].open_state.body.footer
                        binding.title2.text = items[1].open_state.body.title
                        binding.title3.text = items[2].open_state.body.title
                        binding.description1.text = items[0].open_state.body.subtitle
                        binding.description2.text = items[1].open_state.body.subtitle
                        binding.description3.text = items[2].open_state.body.subtitle

                    } else {
                        Log.d("MainActivity1", "No items available")
                    }

            }

    }






    private fun intiialise() {


        binding.item1.setOnClickListener { expandItem(1) }
        binding.item2.setOnClickListener { expandItem(2) }
        binding.item3.setOnClickListener { expandItem(3) }



        binding.circularSeekBar.setOnSeekBarChangeListener(object : CircularSeekBar.OnCircularSeekBarChangeListener {
            override fun onProgressChanged(seekBar: CircularSeekBar?, progress: Float, fromUser: Boolean) {
                // Update TextView based on SeekBar progress
                binding.progressText.text = "₹"+progress.toInt().toString()
            }

            override fun onStopTrackingTouch(seekBar: CircularSeekBar?) {}
            override fun onStartTrackingTouch(seekBar: CircularSeekBar?) {}
        })
    }


    private fun expandItem(item: Int) {
        when (item) {


            1 -> toggleVisibility(binding.desc1Lyt, binding.dropdownIcon1)
            2 -> toggleVisibility(binding.desc2Lyt, binding.dropdownIcon2)
            3 -> toggleVisibility(binding.desc3Lyt, binding.dropdownIcon3)
        }
    }

    private fun toggleVisibility(button: View, icon: ImageView) {
        val isVisible = button.visibility == View.VISIBLE
        button.visibility = if (isVisible) View.GONE else View.VISIBLE
        icon.setImageResource(
            if (isVisible) R.drawable.baseline_keyboard_arrow_down_24
            else R.drawable.baseline_keyboard_arrow_up_24
        )
    }

}


