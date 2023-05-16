package com.app.project.helper

import androidx.fragment.app.FragmentManager
import com.app.project.interfaces.Stackable
import java.util.*

class StackManager {
    private val stack: Stack<Stackable> = Stack()

    fun add(stackable: Stackable) {
        stack.push(stackable)
    }

    fun remove(stackable: Stackable) {
        if (stack.contains(stackable)) {
            stack.remove(stackable)
        }
    }



    fun removeAll() {
        stack.clear()
    }

    fun collapseAll() {
        for (stackable in stack) {
            stackable.collapse()
        }
    }

    fun expand(stackable: Stackable) {
        stackable.getFragmentManager()?.let { fragmentManager ->
            if (!stackable.isExpanded()) {
                (fragmentManager.findFragmentByTag(stackable.javaClass.name) as? Stackable)?.collapse()
                stackable.expand()
            }
        }
    }

    fun collapse(stackable: Stackable) {
        stackable.collapse()
    }

    fun onBackPressed(): Boolean {
        if (stack.isNotEmpty()) {
            val currentStackable = stack.peek()
            if (currentStackable.isExpanded()) {
                currentStackable.collapse()
                return true
            }
            return false
        }
        return true
    }
}





