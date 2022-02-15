package org.hyperskill.stopwatch

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class MainActivity : AppCompatActivity(), MyDialog.MyDialogListener {
    private lateinit var mHandler: Handler
    private lateinit var mRunnable: Runnable
    var time: Time = Time()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startBtn: Button = findViewById(R.id.startButton)
        val resetBtn: Button = findViewById(R.id.resetButton)
        val timeTv: TextView = findViewById(R.id.textView)
        val progressBar: ProgressBar = findViewById(R.id.progressBar)
        val settingsBtn: Button = findViewById(R.id.settingsButton)
        var isTimerRunning: Boolean = false
        val mHandler = Handler()

        timeTv.text = time.getTimeMinsSecondsString()

        startBtn.setOnClickListener {
            Toast.makeText(this, "START button clicked", Toast.LENGTH_SHORT).show()
            progressBar.visibility = View.VISIBLE

            mRunnable = Runnable {
                // Do something here
                time.countTime()
//                Toast.makeText(this, "TICK", Toast.LENGTH_SHORT).show()
//                timeTv.text = time.getTimeMinsSecondsString()
                if (time.getTimeLimit() == null) {
                    timeTv.text = time.getTimeMinsSecondsString()
                    timeTv.setTextColor(Color.BLACK)
                } else {
                    if (time.getSeconds() >= time.getTimeLimit()!!) {
                        timeTv.text = time.getTimeMinsSecondsString()
                        timeTv.setTextColor(Color.RED)
                    } else {
                        timeTv.text = time.getTimeMinsSecondsString()
                        timeTv.setTextColor(Color.BLACK)
                    }
                }


                progressBar.indeterminateTintList = ColorStateList.valueOf(randomColour())

                // Schedule the task to repeat after 1 second
                mHandler.postDelayed(
                    mRunnable, // Runnable
                    1000 // Delay in milliseconds
                )
            }

            // Schedule the task to repeat after 1 second
            if (!isTimerRunning) {
                settingsBtn.isEnabled = false
                settingsBtn.isClickable = false

                mHandler.postDelayed(
                    mRunnable, // Runnable
                    1000 // Delay in milliseconds
                )
                isTimerRunning = true
            }
        }

        resetBtn.setOnClickListener {
            Toast.makeText(this, "Reset button clicked", Toast.LENGTH_SHORT).show()
            progressBar.visibility = View.GONE

            if (isTimerRunning) {
                mHandler.removeCallbacks(mRunnable)
                time.resetTime()
                time.resetTimeLimit()
                timeTv.text = time.getTimeMinsSecondsString()
                timeTv.setTextColor(Color.BLACK)
                settingsBtn.isEnabled = true
                settingsBtn.isClickable = true
                isTimerRunning = false
            }
        }

        settingsBtn.setOnClickListener {
            openDialog("Set upper limit in seconds")
        }

    }

    private fun openDialog(title: String) {
        val dialog = MyDialog(title)
        dialog.show(supportFragmentManager, null)
    }

    private fun randomColour(): Int {
        return Random().nextInt(16777216) * -1
    }

    override fun applyTexts(userDefinedLimit: String?) {
        Toast.makeText(this, "GOT: $userDefinedLimit", Toast.LENGTH_LONG).show()
        time.setTimeLimit(userDefinedLimit?.toLong())
    }


}

