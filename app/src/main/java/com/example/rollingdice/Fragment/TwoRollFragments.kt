package com.example.rollingdice.Fragment


import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rollingdice.MyAdapter
import com.example.rollingdice.MyModel
import com.example.rollingdice.R
import java.util.*

/**
 * A simple [Fragment] subclass.
 *
 */
class TwoRollFragments : Fragment() {


    private val arrayOne= arrayListOf<MyModel>()
    private val arrayTwo= arrayListOf<MyModel>()

    private lateinit var rv1: RecyclerView
    private lateinit var rv2: RecyclerView
    
    var handler = Handler()
    var delay = 2000 //milliseconds

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_two_roll_fragments, container, false)

        rv1 = view.findViewById(R.id.rv1)
        rv2 = view.findViewById(R.id.rv2)

        var first :Int
        var second :Int

        val btnrolls=view.findViewById<TextView>(R.id.btnrolls)

        val one=view.findViewById<ImageView>(R.id.one)
        val two=view.findViewById<ImageView>(R.id.two)
        var rotate : Animation = android.view.animation.AnimationUtils.loadAnimation(activity, R.anim.roatate)
        var rotate2 : Animation = android.view.animation.AnimationUtils.loadAnimation(activity, R.anim.rotate)
        btnrolls?.setOnClickListener {
            one?.startAnimation(rotate)
            two?.startAnimation(rotate2)
            first=getRandom()
            second=RandomValue()
            one?.setImageResource(first)
            two?.setImageResource(second)
        }
        return view
    }

    private fun RandomValue(): Int {

        val b= Random().nextInt(6)+1

        val ob2= MyModel(b)
        arrayTwo.add(ob2)

        val adapterb= MyAdapter(requireContext(), arrayTwo)

        rv2.adapter=adapterb
        rv2.layoutManager= LinearLayoutManager(requireContext())
        handler.postDelayed(Runnable {
            Log.e("Check", "check")
            arrayOne.clear()
            adapterb.notifyDataSetChanged()
        }, 4000)

        return when (b) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
    }

    private fun getRandom(): Int {

        val a= Random().nextInt(6)+1

        val ob1= MyModel(a)
        arrayOne.add(ob1)

        val adaptera= MyAdapter(requireContext(), arrayOne)

        rv1.adapter=adaptera
        rv1.layoutManager= LinearLayoutManager(requireContext())
        
        handler.postDelayed(Runnable {
            Log.e("Check", "check")
            arrayTwo.clear()
            adaptera.notifyDataSetChanged()
        }, 4000)
        return when (a) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

    }
}
