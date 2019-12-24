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
import kotlinx.android.synthetic.main.activity_bottom.*
import java.util.*

/**
 * A simple [Fragment] subclass.
 *
 */
class ThreeRollFragment : Fragment() {


    private val arrayx= arrayListOf<MyModel>()
    private val arrayy= arrayListOf<MyModel>()
    private val arrayz= arrayListOf<MyModel>()


    private lateinit var rva: RecyclerView
    private lateinit var rvb: RecyclerView
    private lateinit var rvc: RecyclerView
    
    var handler = Handler()
    var delay = 2000 //milliseconds
    
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_three_roll, container, false)

        rva = view.findViewById(R.id.rva)
        rvb = view.findViewById(R.id.rvb)
        rvc = view.findViewById(R.id.rvc)

        var first :Int
        var second :Int
        var three:Int

        val btnthird=view?.findViewById<TextView>(R.id.btnthird)

        val a=view?.findViewById<ImageView>(R.id.a)
        val b=view?.findViewById<ImageView>(R.id.b)
        val c=view?.findViewById<ImageView>(R.id.c)
        var rotate : Animation = android.view.animation.AnimationUtils.loadAnimation(activity, R.anim.roatate)
    
        var rotate2 : Animation = android.view.animation.AnimationUtils.loadAnimation(activity, R.anim.rotate)
    
        btnthird?.setOnClickListener {

            a?.startAnimation(rotate)
            b?.startAnimation(rotate2)
            c?.startAnimation(rotate)

            first=randomValue()
            second=ranValue()
            three=getRandom()

            a?.setImageResource(first)
            b?.setImageResource(second)
            c?.setImageResource(three)
        }

        return view
    }

    private fun randomValue(): Int {

        val m= Random().nextInt(6)+1

        val ob2= MyModel(m)
        arrayx.add(ob2)

        val adapterb= MyAdapter(requireContext(), arrayx)

        rva.adapter=adapterb
        rva.layoutManager= LinearLayoutManager(requireContext())
        handler.postDelayed(Runnable {
            Log.e("Check", "check")
            arrayx.clear()
            adapterb.notifyDataSetChanged()
        }, 4000)
        return when (m) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
    }


    private fun ranValue(): Int {

        val n=Random().nextInt(6)+1

        val ob2= MyModel(n)
        arrayy.add(ob2)

        val adapterb= MyAdapter(requireContext(), arrayy)

        rvb.adapter=adapterb
        rvb.layoutManager=LinearLayoutManager(requireContext())
        handler.postDelayed(Runnable {
            Log.e("Check", "check")
            arrayy.clear()
            adapterb.notifyDataSetChanged()
        }, 4000)
        return when (n) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

    }


    private fun getRandom(): Int {

        val o=Random().nextInt(6)+1

        val ob2= MyModel(o)
        arrayz.add(ob2)

        val adapterb= MyAdapter(requireContext(), arrayz)

        rvc.adapter=adapterb
        rvc.layoutManager=LinearLayoutManager(requireContext())
        handler.postDelayed(Runnable {
            Log.e("Check", "check")
            arrayz.clear()
            adapterb.notifyDataSetChanged()
        }, 4000)
        return when (o) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

    }

}
