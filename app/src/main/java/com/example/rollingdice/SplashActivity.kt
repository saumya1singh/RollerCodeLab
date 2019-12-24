package com.example.rollingdice


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo

class SplashActivity : AppCompatActivity() {

    private var handler: Handler? =null
    private val splashlength:Long=2000

    lateinit var diceRollerTextView: TextView
    lateinit var diceImageView: ImageView


    private val handlerRunnable:Runnable= Runnable {
        if(!isFinishing){
            startActivity(Intent(baseContext, BottomActivity::class.java))
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        diceImageView = findViewById(R.id.imageView)
        diceRollerTextView = findViewById(R.id.textView)



        handler=Handler()
        handler!!.postDelayed(handlerRunnable,splashlength)


        YoYo.with(Techniques.ZoomIn).duration(1000).repeat(0).playOn(diceImageView)


    }

    override fun onDestroy() {
        if (handler!=null){
            handler!!.removeCallbacks(handlerRunnable)
        }
        super.onDestroy()
    }


}
