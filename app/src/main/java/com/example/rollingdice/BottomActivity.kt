package com.example.rollingdice

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rollingdice.Fragment.HomeFragment
import com.example.rollingdice.Fragment.OneRollerFragemnt
import com.example.rollingdice.Fragment.ThreeRollFragment
import com.example.rollingdice.Fragment.TwoRollFragments
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tbuonomo.morphbottomnavigation.MorphBottomNavigationView
import kotlinx.android.synthetic.main.activity_bottom.*

class BottomActivity : AppCompatActivity(),HomeFragment.Communicate {



    lateinit var navView: BottomNavigationView
    private lateinit var fadein : Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom)

        showFragment()
        navView = findViewById(R.id.nav_view)
        navView.visibility=View.GONE
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }


    private fun showFragment() {

        supportFragmentManager.beginTransaction().replace(
            R.id.frameLayout,
            HomeFragment()).commit()

    }


    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {

            R.id.navigation_home -> {

                supportFragmentManager.beginTransaction().replace(
                 R.id.frameLayout,
                 OneRollerFragemnt()).commit()

                return@OnNavigationItemSelectedListener true
            }


            R.id.navigation_dashboard -> {

                supportFragmentManager.beginTransaction().replace(
                 R.id.frameLayout,
                 TwoRollFragments()).commit()

                return@OnNavigationItemSelectedListener true
            }


            R.id.navigation_notifications -> {


                supportFragmentManager.beginTransaction().replace(
                 R.id.frameLayout,
                 ThreeRollFragment()).commit()

                return@OnNavigationItemSelectedListener true
            }
        }

        false
    }
    override fun Visiblitychange() {
        navView.visibility =View.VISIBLE
        fadein = android.view.animation.AnimationUtils.loadAnimation(this, R.anim.fadein)
        navView.startAnimation(fadein)
    }
}
