package com.example.five_second_timer

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.five_second_timer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    private val handler = Handler()
    private var time_value = 0
    private val runnable = object : Runnable {
        override fun run() {
            time_value++

            timeToText(time_value)?.let {
                binding.timeView.text = it
            }

            handler.postDelayed(this, 10)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.startButton.setOnClickListener {
            handler.post(runnable)
        }

        binding.stopButton.setOnClickListener {
            handler.removeCallbacks(runnable)
        }

        binding.resetButton.setOnClickListener {
            handler.removeCallbacks(runnable)
            time_value = 0
            timeToText()?.let {
                binding.timeView.text = it
            }
        }
    }

    private fun timeToText(time: Int = 0): String? {

        return if (time < 0) {
            null
        } else if (time == 0) {
            "00:00:00"
        } else {
            val h = time / 3600
            val m = time % 3600 / 60
            val s = time % 60
            "%1$02d:%2$02d:%3$02d".format(h, m, s)
        }
    }
}
