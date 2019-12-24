package com.example.rollingdice.Fragment


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.ImageView
import androidx.fragment.app.Fragment

import com.example.rollingdice.R
import com.hitomi.cmlibrary.CircleMenu
import com.hitomi.cmlibrary.OnMenuStatusChangeListener
import com.hitomi.cmlibrary.OnMenuSelectedListener
import android.graphics.Color.parseColor

import android.graphics.Color
import androidx.fragment.app.FragmentManager
import java.util.*

import android.os.Handler
import android.R.attr.delay
import android.R.attr.privateImeOptions
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.core.os.HandlerCompat.postDelayed

import android.view.Menu
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator
import com.getkeepsafe.taptargetview.TapTarget
import com.getkeepsafe.taptargetview.TapTargetView
import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt
import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetSequence


class HomeFragment : Fragment() {
 
 private lateinit var imageDice: ImageView
 lateinit var communicate: Communicate
 private var indexitem = -1
 lateinit var circleMenu: CircleMenu
 private var mFabPrompt: MaterialTapTargetPrompt? = null
 
 interface Communicate {
  fun Visiblitychange()
 }
 
 
 override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
  val view = inflater.inflate(R.layout.fragment_home, container, false)
  
  
  MaterialTapTargetSequence()
   .addPrompt(
    MaterialTapTargetPrompt.Builder(requireActivity())
     .setTarget(activity?.findViewById<CircleMenu>(R.id.circle_menu))
     .setPrimaryText("Step 1")
     .setSecondaryText("This will show for 4 seconds")
     
     .create(), 4000
   )
   .addPrompt(
    MaterialTapTargetPrompt.Builder(
     requireActivity()
    )
     .setTarget(activity?.findViewById<CircleMenu>(R.id.circle_menu))
     .setPrimaryText("Step 2")
     .setSecondaryText("This will show till you press it")
     .setAnimationInterpolator(LinearOutSlowInInterpolator())
   
   
   )
   .show()
  circleMenu = view.findViewById(com.example.rollingdice.R.id.circle_menu)
  
  
   TapTargetView.showFor(activity,                 // `this` is an Activity
   TapTarget.forView(view.findViewById(R.id.circle_menu), "Select dice :", "Choose dice to play")
    .cancelable(false)
    .tintTarget(false)
    .outerCircleColor(R.color.colorAccent)
    .targetCircleColor(android.R.color.white)
    .outerCircleAlpha(0.8f)
    .drawShadow(true)
    .textColor(android.R.color.white)
    .targetRadius(60)
    .titleTextSize(30)
    .descriptionTextSize(20))
  
  
 
  
   
  circleMenu.setMainMenu(Color.parseColor("#CDCDCD"), R.drawable.icon_menu, R.drawable.icon_cancel)
   .addSubMenu(Color.parseColor("#258CFF"), R.drawable.single_dice)
   .addSubMenu(Color.parseColor("#30A400"), R.drawable.double_dice)
   .addSubMenu(Color.parseColor("#FF4B32"), R.drawable.triple_dice)
   .setOnMenuSelectedListener(object : OnMenuSelectedListener {
    
    override fun onMenuSelected(index: Int) {
     indexitem = index
    }
   })
   .setOnMenuStatusChangeListener(object : OnMenuStatusChangeListener {
    override fun onMenuOpened() {
     /*animation to show that select one dice , two dice from here */
    }
    
    override fun onMenuClosed() {
     circleMenu.visibility = View.GONE
     when (indexitem) {
      
      0 -> {
       indexitem = -1
       fragmentManager?.beginTransaction()?.replace(
        R.id.parentLayout,
        OneRollerFragemnt()
       )?.commit()
       
      }
      
      1 -> {
       indexitem = -1
       fragmentManager?.beginTransaction()?.replace(
        R.id.parentLayout,
        TwoRollFragments()
       )?.commit()
       
      }
      2 -> {
       indexitem = -1
       fragmentManager?.beginTransaction()?.replace(
        R.id.parentLayout,
        ThreeRollFragment()
       )?.commit()
       
      }    }
    }
   })
  
  return view
 }
 
 
 override fun onAttach(context: Context?) {
  super.onAttach(context)
  
  val activity = context as Activity
  communicate = activity as Communicate
 }
 
}
