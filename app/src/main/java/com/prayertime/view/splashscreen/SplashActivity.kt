package com.prayertime.view.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.prayertime.R
import com.prayertime.view.MainActivity

class SplashActivity : AppCompatActivity() {

    private val SPLASH_DELAY: Long = 2000
    private var mDelayHandler: Handler? = null
    private var progressBarStatus = 0
    var dummy:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        mDelayHandler = Handler()
        //Navigate with delay
        mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)
    }

    private fun launchMainActivity() {
        var intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
        this.finish()
        mDelayHandler!!.removeCallbacks(mRunnable)
    }

    private val mRunnable: Runnable = Runnable {

        Thread(Runnable {
            while (progressBarStatus < 100) {
                // performing some dummy operation
                try {
                    dummy = dummy+ 25
                    Thread.sleep(100)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                // tracking progress
                progressBarStatus = dummy
            }

            //splash_screen_progress_bar.setProgress(10)

            launchMainActivity()


        }).start()
    }

    override fun onDestroy() {

        if (mDelayHandler != null) {
            mDelayHandler!!.removeCallbacks(mRunnable)
        }
        super.onDestroy()
    }

}