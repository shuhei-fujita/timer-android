package com.example.five_second_timer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val handler = Handler()
    private var time_value = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val runnable = object : Runnable {
            override fun run() {
                time_value++

                timeToText(time_value)?.let {
                    // timeToText(timeValue)の値がlet内ではitとして使える
                    time_view.text = it
                }

                handler.postDelayed(this, 1000)
            }
        }
        handler.post(runnable);

        start.setOnClickListener {
            handler.post(runnable)
        }

        stop.setOnClickListener {
            handler.removeCallbacks(runnable)
        }
//        reset.setOnClickListener {
//            handler.removeCallbacks(runnable)
//            timeValue = 0
//            // timeToTextの引数はデフォルト値が設定されているので、引数省略できる
//            timeToText()?.let {
//                timeText.text = it
//            }
//        }
    }

    private fun timeToText(time: Int = 0): String? {
        // if式は値を返すため、そのままreturnできる
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
