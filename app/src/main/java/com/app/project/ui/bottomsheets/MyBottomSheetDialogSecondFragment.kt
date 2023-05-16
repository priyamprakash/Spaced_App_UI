package com.app.project.ui.bottomsheets

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.project.R
import com.app.project.helper.StackManager
import com.app.project.interfaces.Stackable
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MyBottomSheetDialogSecondFragment : BottomSheetDialogFragment(), Stackable {

    private var isExpanded: Boolean = false

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_bottom_sheet_second_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val stackManager = StackManager()
        val button2 = view.findViewById<Button>(R.id.button2)

        button2.setOnClickListener {
            openThirdSheet(stackManager)

        }

        val openUp = view.findViewById<ConstraintLayout>(R.id.open_up)
        openUp.setOnClickListener {
            openThirdSheet(stackManager)

        }


        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = MyAdapter()
        recyclerView.scrollToPosition(1)

    }

    private fun openThirdSheet(stackManager: StackManager) {
        val bottomSheet3 = MyBottomSheetDialogThirdFragment()
        stackManager.add(bottomSheet3)
        Handler(Looper.getMainLooper()).post {
            stackManager.expand(bottomSheet3)
        }
        bottomSheet3.show(parentFragmentManager, bottomSheet3.tag)

    }


    override fun isExpanded(): Boolean {
        return isExpanded
    }


    override fun expand() {
        if (!isAdded) return
        if (!isExpanded) {
            show(parentFragmentManager, tag)
            isExpanded = true
        }
    }


    override fun collapse() {
        if (isExpanded) {
            dismiss()
            isExpanded = false
        }
    }


    private inner class MyAdapter : RecyclerView.Adapter<MyViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val itemView =
                LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
            return MyViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val item = position + 1
            holder.textView.text = item.toString()

            if(position == 2){
                holder.textView.textSize = 108f
                holder.textView.setTextColor(Color.WHITE)
            }else{
                holder.textView.textSize = 88f
                holder.textView.setTextColor(Color.DKGRAY)
            }



        }

        override fun getItemCount(): Int {
            return 8
        }
    }

    private inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textView)
    }

}



