package com.app.project.interfaces

import androidx.fragment.app.FragmentManager


interface Stackable {
    fun getFragmentManager(): FragmentManager?
    fun isExpanded(): Boolean
    fun expand()
    fun collapse()
}
