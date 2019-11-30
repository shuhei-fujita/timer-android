package com.example.five_second_timer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val handler = Handler()
    private var time_value = 0
    private val runnable = object : Runnable {
        override fun run() {
            time_value++

            timeToText(time_value)?.let {
                time_view.text = it
            }

            handler.postDelayed(this, 1000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        start_button.setOnClickListener {
            handler.post(runnable)
        }

        stop_button.setOnClickListener {
            handler.removeCallbacks(runnable)
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
