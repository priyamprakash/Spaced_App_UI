package com.app.project.ui.bottomsheets

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import com.app.project.R
import com.app.project.helper.StackManager
import com.app.project.interfaces.Stackable
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MyBottomSheetDialogFragment : BottomSheetDialogFragment(), Stackable {

    private var isExpanded: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_bottom_sheet_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val stackManager = StackManager()

        val button2 = view.findViewById<Button>(R.id.button2)
        button2.setOnClickListener {
            openSecondSheet(stackManager)
        }

        val openUp = view.findViewById<ConstraintLayout>(R.id.open_up)
        openUp.setOnClickListener {
            openSecondSheet(stackManager)

        }
    }

    private fun openSecondSheet(stackManager: StackManager) {
        val bottomSheet2 = MyBottomSheetDialogSecondFragment()
        stackManager.add(bottomSheet2)
        Handler(Looper.getMainLooper()).post {
            stackManager.expand(bottomSheet2)
        }
        bottomSheet2.show(parentFragmentManager, bottomSheet2.tag)
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

}



