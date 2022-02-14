package org.hyperskill.stopwatch

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private lateinit var mHandler: Handler
    private lateinit var mRunnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startBtn: Button = findViewById(R.id.startButton)
        val resetBtn: Button = findViewById(R.id.resetButton)
        val timeTv: TextView = findViewById(R.id.textView)
        var time: Time = Time()
        var isTimerRunning: Boolean = false
        val mHandler = Handler()

        timeTv.text = time.getTimeMinsSecondsString()

        startBtn.setOnClickListener {
            Toast.makeText(this, "START button clicked", Toast.LENGTH_SHORT).show()

            mRunnable = Runnable {
                // Do something here
                time.countTime()
//                Toast.makeText(this, "TICK", Toast.LENGTH_SHORT).show()
                timeTv.text = time.getTimeMinsSecondsString()

                // Schedule the task to repeat after 1 second
                mHandler.postDelayed(
                    mRunnable, // Runnable
                    1000 // Delay in milliseconds
                )
            }

            // Schedule the task to repeat after 1 second
            if (!isTimerRunning) {
                mHandler.postDelayed(
                    mRunnable, // Runnable
                    1000 // Delay in milliseconds
                )
                isTimerRunning = true
            }
        }

        resetBtn.setOnClickListener {
            Toast.makeText(this, "Reset button clicked", Toast.LENGTH_SHORT).show()
            if (isTimerRunning) {
                mHandler.removeCallbacks(mRunnable)
                time.resetTime()
                timeTv.text = time.getTimeMinsSecondsString()
                isTimerRunning = false
            }
        }

    }
}

