package com.example.rollingdice.Fragment


import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation

import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rollingdice.MyAdapter
import com.example.rollingdice.MyModel
import com.example.rollingdice.R

import androidx.interpolator.view.animation.LinearOutSlowInInterpolator

import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt
import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetSequence


class OneRollerFragemnt : Fragment() {
 
 
 private val arrayList = arrayListOf<MyModel>()
 private lateinit var rv: RecyclerView
 lateinit var myAdapter: MyAdapter
 var handler = Handler()
 var delay = 2000 //milliseconds
 
 override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
  val view = inflater.inflate(R.layout.fragment_one_roller_fragemnt, container, false)
  
  rv = view.findViewById(R.id.rv)
  
  val roll = view.findViewById<TextView>(R.id.btnroll)
  
  val imageDice = view.findViewById<ImageView>(R.id.imagedice)
  
  var num1: Int
   var rotate: Animation = android.view.animation.AnimationUtils.loadAnimation(activity, R.anim.roatate)
   
   
   MaterialTapTargetSequence()
   .addPrompt(MaterialTapTargetPrompt.Builder(requireActivity())
     .setTarget(activity?.findViewById<TextView>(R.id.btnroll))
     .setPrimaryText("Step 1")
     .setSecondaryText("This will show for 4 seconds")
     .create(), 4000 )
     .addPrompt(MaterialTapTargetPrompt.Builder( requireActivity())
     .setTarget(
      activity?.findViewById<TextView>(
       R.id.btnroll) )
     .setPrimaryText("Step 2")
     .setSecondaryText("This will show till you press it")
     .setAnimationInterpolator(LinearOutSlowInInterpolator()))
     .show()
     
  
  roll.setOnClickListener {
   imageDice?.startAnimation(rotate)
   num1 = getRandomImage()
   imageDice?.setImageResource(num1)
   
  }
  
  
  
  return view
 }
 
 
 private fun getRandomImage(): Int {
  
  val randomInt = java.util.Random().nextInt(6) + 1
  Log.e("TAG", "$randomInt")
  
  val ob = MyModel(randomInt)
  arrayList.add(ob)
  
  myAdapter = MyAdapter(requireContext(), arrayList)
  
  rv.layoutManager = LinearLayoutManager(requireContext())
  rv.adapter = myAdapter
  handler.postDelayed(Runnable {
   Log.e("Check", "check")
   arrayList.clear()
   myAdapter.notifyDataSetChanged()
  }, 4000)
  
  return when (randomInt) {
   
   1 -> R.drawable.dice_1
   2 -> R.drawable.dice_2
   3 -> R.drawable.dice_3
   4 -> R.drawable.dice_4
   5 -> R.drawable.dice_5
   else -> R.drawable.dice_6
   
  }
  
 }
 
}
