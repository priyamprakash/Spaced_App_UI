package com.app.project.ui.bottomsheets

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import com.app.project.ui.activity.MainActivity
import com.app.project.R
import com.app.project.helper.StackManager
import com.app.project.interfaces.Stackable
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MyBottomSheetDialogThirdFragment : BottomSheetDialogFragment(), Stackable {

    private var isExpanded: Boolean = false


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_bottom_sheet_third_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val stackManager = StackManager()
        val button2 = view.findViewById<Button>(R.id.button_done)

        button2.setOnClickListener {
            openThirdSheet(stackManager)
        }

        val openUp = view.findViewById<ConstraintLayout>(R.id.open_up)

        openUp.setOnClickListener {
            openThirdSheet(stackManager)
        }

    }

    private fun openThirdSheet(stackManager: StackManager) {
        stackManager.removeAll()
        stackManager.collapseAll()

        dismiss() // Dismiss the third sheet

        val intent = Intent(requireContext(), MainActivity::class.java)
        startActivity(intent)
        requireActivity().finish()


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



